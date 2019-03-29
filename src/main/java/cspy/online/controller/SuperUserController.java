package cspy.online.controller;

import com.github.pagehelper.PageHelper;
import cspy.online.bean.User;
import cspy.online.dao.UserMapper;
import cspy.online.util.WebLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author CSpy
 * @date 2019/3/28 11:58
 */
@Controller

public class SuperUserController {

    @Autowired
    WebLogger webLogger;


    @RequestMapping("/su-users.do")
    @Autowired
    public String getSuperUser(ModelMap modelMap) {
        modelMap.addAttribute("webLogger", webLogger);
        return "su-users";
    }

    @Autowired
    UserMapper userMapper;

    @ModelAttribute("userList")
    public List<List<User>> getAllUsers(ModelMap modelMap) {
        PageHelper.startPage(1, 12);
        List<User> users = userMapper.selectAll();
//        int count = new Random().nextInt(12);
//        return splitList(users.subList(0,count), 4);
        return splitList(users, 4);
    }


    /**
     *
     * @param users user列表
     * @param col 每一行的个数
     * @return 返回按列个数划分的列表
     */
    public List<List<User>> splitList(List<User> users, int col) {
        List<List<User>> userList = new ArrayList<>();
        for (int i = 0; i < Math.ceil(users.size() / (double)col); i++) {
            userList.add(new ArrayList<>());
        }

        for (int i = 0; i < users.size(); i++) {
            int index = i / col;
            userList.get(index).add(users.get(i));
        }
        return userList;
    }
}
