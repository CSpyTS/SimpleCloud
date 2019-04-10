package cspy.online.controller;

import cspy.online.bean.SCFile;
import cspy.online.bean.SCUser;
import cspy.online.dao.FileMapper;
import cspy.online.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author CSpy
 * @date 2019/4/3 14:30
 */

@Controller
public class MainFrameController {


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainFrame() {
        return "index";
    }

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private UserMapper userMapper;


//    @ModelAttribute("fileList")
//    public List<SCFile> getFileList() {
//        int uid = 1;
//        SCUser user = userMapper.selectOneByUid(uid);
//        System.out.println("username:" + user.getUsername());
//        Path path = Paths.get("D:/SimpleCloud/users", "CSpy");
//        System.out.println("path:" + path);
//        return fileMapper.getFileList(path);
//    }


    @RequestMapping("/getFileList")
    public String getPath(@RequestParam("path") String path, Model model) {
        List<SCFile> files = fileMapper.getFileList(Paths.get(path));
        model.addAttribute("fileList", files);
        System.out.println("controller path = " + path);
//        for (SCFile scFile : files) {
//            System.out.println(scFile.getFilename());
//        }
        return "index::file-table-body";
    }

//    @ModelAttribute("filePathList")
//    public List<Path> getFilePathList() {
//        return paths;
//
//    }

//    @RequestMapping("/addPath")
//    public String addPath(@RequestParam("path") String path, Model model) {
//        paths.add(Paths.get(path));
//        model.addAttribute("filePathList", path);
//        return "main::file-path-item";
//    }




}
