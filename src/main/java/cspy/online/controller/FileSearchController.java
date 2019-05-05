package cspy.online.controller;

import cspy.online.bean.ResponseMessage;
import cspy.online.bean.SCFile;
import cspy.online.service.FileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author CSpy
 * @date 2019/4/27 9:17
 */
@Controller
public class FileSearchController {


    @Autowired
    FileSearchService fileSearchService;


    @RequestMapping("/searchByType")
    @ResponseBody
    public ResponseMessage searchByType(@RequestParam("type") String type, @RequestParam("path") String path) {
        ResponseMessage message = new ResponseMessage();
        message.setState(true);
        List<SCFile> scFiles = fileSearchService.searchByType(type, path);
        message.setMsg("共查询出" + scFiles.size() + "个记录。");
        message.setData(scFiles);
        return message;
    }

    @RequestMapping("/getFileList")
    @ResponseBody
    public List<SCFile> getPath(@RequestParam("path") String path) {
        return fileSearchService.getFileList(path);
    }


    /**
     *
     * @param path 待查询的路径
     * @param forbiddenPath 被移动文件所在的文件夹
     * @param dirNameStr 被移动的文件夹名
     * @return 返回符合条件的文件夹列表
     */
    @RequestMapping("/getDirectory")
    @ResponseBody
    public List<SCFile> getDirectory(@RequestParam("path") String path, @RequestParam("fpath") String forbiddenPath,
                                     @RequestParam("fdir") String dirNameStr) {

        System.out.println("path:" + path + " fpath:" + forbiddenPath + " fdir=" + dirNameStr);

        String[] dirNames = dirNameStr.split(",");
        if (dirNames.length == 0 || forbiddenPath == null || forbiddenPath.equals("")) {
            return fileSearchService.getDirectoryList(path);
        }

        List<String> dirNameList = Arrays.asList(dirNames);

        // 当path不合理，直接返回空
        for (String dirName: dirNameList) {
            if (path.startsWith(forbiddenPath + "/" + dirName + "/")) {
                return null;
            }
        }

        List<SCFile> dirList = fileSearchService.getDirectoryList(path);
        if (path.equals(forbiddenPath)) {
            // 如果被隐藏的路径的根目录是被查询的路径的话，则将被隐藏的路径去除
            Iterator<SCFile> iterator = dirList.iterator();
            while(iterator.hasNext()) {
                SCFile scFile = iterator.next();
                if (dirNameList.contains(scFile.getFilename())) {
                    iterator.remove();
                }
            }
        }



        return dirList;
    }
}
