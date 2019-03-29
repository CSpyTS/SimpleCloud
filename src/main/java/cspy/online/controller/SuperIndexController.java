package cspy.online.controller;

import cspy.online.bean.User;
import cspy.online.dao.UserMapper;
import cspy.online.util.ResponseJSON;
import cspy.online.util.WebLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.List;

@Controller
public class SuperIndexController {

    @Autowired
    WebLogger webLogger;





    @RequestMapping("/su-index.do")
    @Autowired
    public String getSuperIndex(ModelMap modelMap) {
        modelMap.addAttribute("webLogger", webLogger);
        return "su-index";
    }



    @RequestMapping("/su-index/logger.do")
    @Autowired
    public String getSuIndexLogger(ModelMap modelMap) {
        System.err.println("into");
        webLogger.addLog("error", "hello refresh" + System.currentTimeMillis());
        System.out.println(webLogger);
        modelMap.addAttribute("webLogger", webLogger);
        return "su-index::logger-panel";

    }





}
