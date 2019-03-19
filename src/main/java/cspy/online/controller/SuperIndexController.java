package cspy.online.controller;

import cspy.online.util.WebLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuperIndexController {



    @RequestMapping("/su-index.do")
    @Autowired
    public String getSuperIndex(WebLogger webLogger, ModelMap modelMap) {
        modelMap.addAttribute("webLogger", webLogger);

        for (int i = 0; i < 10; i++) {
            webLogger.addLog("error", "hello" + i);
        }
        for (int i = 0; i < 20; i++) {
            webLogger.addLog("info", "hello" + i);
        }
        return "su-index";
    }

}
