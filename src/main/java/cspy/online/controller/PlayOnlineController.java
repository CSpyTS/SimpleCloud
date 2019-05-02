package cspy.online.controller;

import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CSpy
 * @date 2019/4/22 15:00
 */
@Controller
public class PlayOnlineController {

    @Autowired
    Tika tika;

    @RequestMapping("/playVideo")
    @ResponseBody
    public Map<String, String> playVideo(@RequestParam("videoPath") String videoPath) {
        Map<String, String> resultMap = new HashMap<>();

        File videoFile = Paths.get(videoPath).toFile();
        try {
            if (tika.detect(videoFile).startsWith("video")) {
                File videoDirectory = Paths.get("D:\\SimpleCloud\\video").toFile();
                if (!videoDirectory.exists()) {
                    videoDirectory.mkdir();
                }
                File targetVideoFile = Paths.get("video", videoFile.getName()).toFile();
                FileUtils.copyFile(videoFile, targetVideoFile);
                resultMap.put("state", "true");
                resultMap.put("msg", "视频加载成功。");
                resultMap.put("path", "video/" + videoFile.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("state", "false");
            resultMap.put("msg", "视频播放错误！");
        }
        return resultMap;
    }
}
