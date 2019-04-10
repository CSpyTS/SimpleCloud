package cspy.online.controller;

import cspy.online.util.WebLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuperIndexController {

    @Autowired
    WebLogger webLogger;





    @RequestMapping("/su-index")
    @Autowired
    public String getSuperIndex(ModelMap modelMap) {
        modelMap.addAttribute("webLogger", webLogger);
        return "su-index";
    }



    @RequestMapping("/su-index/logger")
    @Autowired
    public String getSuIndexLogger(ModelMap modelMap) {
        System.err.println("into");
        webLogger.addLog("error", "hello refresh" + System.currentTimeMillis());
        System.out.println(webLogger);
        modelMap.addAttribute("webLogger", webLogger);
        return "su-index::logger-panel";

    }





}
