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
    public ResponseMessage searchPathByType(@RequestParam("type") String type, @RequestParam("path") String path) {
        return fileSearchService.searchPathByType(type, path);
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
        return fileSearchService.getDirectory(path, forbiddenPath, dirNameStr);
    }
}
