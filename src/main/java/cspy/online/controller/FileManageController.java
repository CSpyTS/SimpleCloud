package cspy.online.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import cspy.online.bean.FileMoveWrapper;
import cspy.online.bean.ResponseMessage;
import cspy.online.bean.SCFile;
import cspy.online.bean.SimpleFile;
import cspy.online.dao.FileMapper;
import cspy.online.service.FileManageService;
import cspy.online.util.FDFSTool;
import cspy.online.util.FileTool;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.tika.Tika;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author CSpy
 * @date 2019/4/21 13:03
 */
@Controller
public class FileManageController {


    @Autowired
    FileManageService fileManageService;


    @RequestMapping("/upload")
    @ResponseBody
    public ResponseMessage updateFile(@RequestParam String path, @RequestParam MultipartFile file) {
        return fileManageService.updateFile(path, file);
    }

    @RequestMapping("/createDirectory")
    @ResponseBody
    public ResponseMessage createDirectory(@RequestParam("rootPath") String rootPath, @RequestParam("dirName") String dirName) {
        return fileManageService.createDirectory(rootPath, dirName);
    }

    @RequestMapping("/removeDirectory")
    @ResponseBody
    public ResponseMessage removeDirectory(@RequestParam("path") String path) {
        return fileManageService.removeDirectory(path);
    }

    @RequestMapping("/removeFile")
    @ResponseBody
    public ResponseMessage removeFile(@RequestParam("path") String path, @RequestParam("fileNameList") String fileNameListStr) {
        return fileManageService.removeFile(path, fileNameListStr);
    }

    @RequestMapping(value = "/moveFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage moveFile(@RequestBody String requestBody) {
        return fileManageService.moveFile(requestBody);
    }


}
