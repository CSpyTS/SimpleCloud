package cspy.online.controller;

import cspy.online.bean.SCFile;
import cspy.online.bean.SCUser;
import cspy.online.dao.FileMapper;
import cspy.online.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    List<Path> paths;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainFrame() {
        if (Objects.isNull(paths)) {
            paths = new ArrayList<>();
        }
        if (paths.size() == 0) {
            paths.add(Paths.get("D:/SimpleCloud/users", "CSpy"));
        }
        return "index";
    }

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private UserMapper userMapper;


    @ModelAttribute("fileList")
    public List<SCFile> getFileList() {
        int uid = 1;
        SCUser user = userMapper.selectOneByUid(uid);
        System.out.println("username:" + user.getUsername());
        Path path = Paths.get("D:/SimpleCloud/users", "CSpy");
        System.out.println("path:" + path);
        return fileMapper.getFileList(path);
    }

    @ModelAttribute("filePathList")
    public List<Path> getFilePathList() {
        return paths;

    }

    @RequestMapping("/main/addPath")
    public void addPath(@RequestParam("path") String path) {
        paths.add(Paths.get(path));

    }




}
