package cspy.online.controller;

import cspy.online.service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author CSpy
 * @date 2019/4/1 8:17
 */
@Controller
@RequestMapping("/login-register")
public class LoginRegisterController {

    @Autowired
    LoginRegisterService loginRegisterService;


    @RequestMapping(method = RequestMethod.GET)
    public String loginRegister() {
        return "login-register";
    }

    @RequestMapping(value = "/login-by-username", method = RequestMethod.POST)
    @ResponseBody
    public String loginByUserName(@RequestParam("username") String username, @RequestParam("password")String password) {
        System.out.println("username:" + username + " password:" + password);
        if (loginRegisterService.checkUserValidByUserName(username, password)) {
            return "登陆成功";
        } else {
            return "失败";
        }
    }

    @RequestMapping(value = "/login-by-phone-email", method = RequestMethod.POST)
    @ResponseBody
    public String loginByPhoneEmail(@RequestParam("phone-email") String phoneOrEmail, @RequestParam("password")String password) {
        System.out.println("phoneOrEmail:" + phoneOrEmail + " password:" + password);
        Map<String, Boolean> stringBooleanMap = loginRegisterService.checkUserValidByPhoneOrEmail(phoneOrEmail, password);
        if (stringBooleanMap.size() == 0) {
            return "无" + phoneOrEmail + "此用户";
        }
        for (String key : stringBooleanMap.keySet()) {
            System.out.println("登录通过：" + key + " 登录" + (stringBooleanMap.get(key)?"成功":"失败"));
        }
        return "show in console";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam("username") String username, @RequestParam("password") String password) {
        return loginRegisterService.registerUser(username, password)?"注册成功":"注册失败";
    }


    @RequestMapping(value = "/isUserByUserName")
    @ResponseBody
    private boolean isUserByUserName(@RequestParam("username") String username) {
        return loginRegisterService.isUserByUserName(username);
    }

    @RequestMapping(value = "/isUserByPhone")
    @ResponseBody
    private boolean isUserByPhone(@RequestParam("phone") String phone) {
        return loginRegisterService.isUserByPhone(phone);
    }


    @RequestMapping(value = "/isUserByEmail")
    @ResponseBody
    private boolean isUserByEmail(@RequestParam("email") String email) {
        return loginRegisterService.isUserByEmail(email);
    }


}
