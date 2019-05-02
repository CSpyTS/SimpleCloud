package cspy.online.controller;

import com.github.pagehelper.PageHelper;
import cspy.online.bean.SCUser;
import cspy.online.dao.UserMapper;
import cspy.online.util.WebLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CSpy
 * @date 2019/3/28 11:58
 */
@Controller
public class SuperUserController {

    @Autowired
    WebLogger webLogger;


    @RequestMapping("/su-users")
    @Autowired
    public String getSuperUser(ModelMap modelMap) {
        modelMap.addAttribute("webLogger", webLogger);
        return "su-users";
    }

    @Autowired
    UserMapper userMapper;

    @ModelAttribute("userList")
    public List<List<SCUser>> getAllUsers(ModelMap modelMap) {
        PageHelper.startPage(1, 12);
        List<SCUser> SCUsers = userMapper.selectAll();
//        int count = new Random().nextInt(12);
//        return splitList(SCUsers.subList(0,count), 4);
        return splitList(SCUsers, 4);
    }


    /**
     *
     * @param SCUsers user列表
     * @param col 每一行的个数
     * @return 返回按列个数划分的列表
     */
    public List<List<SCUser>> splitList(List<SCUser> SCUsers, int col) {
        List<List<SCUser>> userList = new ArrayList<>();
        for (int i = 0; i < Math.ceil(SCUsers.size() / (double)col); i++) {
            userList.add(new ArrayList<>());
        }

        for (int i = 0; i < SCUsers.size(); i++) {
            int index = i / col;
            userList.get(index).add(SCUsers.get(i));
        }
        return userList;
    }
}
