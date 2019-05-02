package cspy.online.controller;

import cspy.online.bean.SCFile;
import cspy.online.service.FileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Path;
import java.nio.file.Paths;
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
    public List<SCFile> searchByType(@RequestParam("type") String type, @RequestParam("path") String path) {
        return fileSearchService.searchByType(type, path);
    }

    @RequestMapping("/getFileList")
    @ResponseBody
    public List<SCFile> getPath(@RequestParam("path") String path) {
        return fileSearchService.getFileList(path);
    }


    @RequestMapping("/getDirectory")
    @ResponseBody
    public List<SCFile> getDirectory(@RequestParam("path") String path, @RequestParam("fpath") String forbiddenPath) {

        System.out.println("path:" + path + " fpath:" + forbiddenPath);

        if (forbiddenPath == null || forbiddenPath.equals("")) {
            return fileSearchService.getDirectoryList(path);
        }

        // 如果被隐藏的路径是被查询路径的开头的话，则跳过查询
        if (path.startsWith(forbiddenPath)) {
            return null;
        } else {
            // 如果被隐藏的路径的根目录是被查询的路径的话，则将被隐藏的路径去除
            List<SCFile> dirList = fileSearchService.getDirectoryList(path);
            Path forbiddenPathObj = Paths.get(forbiddenPath);
            String parentDir = forbiddenPathObj.getParent().toString();
            String fpoName = forbiddenPathObj.getFileName().toString();
            if (path.equals(parentDir)) {
                dirList.removeIf(file -> file.getFilename().equals(fpoName));
            }
            return dirList;
        }
    }
}
