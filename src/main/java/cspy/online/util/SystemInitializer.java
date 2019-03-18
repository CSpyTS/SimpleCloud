package cspy.online.util;

import cspy.online.bean.User;
import cspy.online.dao.UserMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemInitializer {

    private String dataDirectoryString;
    private String basicDirectoryString;
    private String userBasicDirectoryString;

    @Autowired
    Logger logger;

    @Autowired
    UserMapper userMapper;

    private File dataDirectory;
    private Map<String, File> basicDirectory;

    public SystemInitializer(String dataDirectoryString, String basicDirectoryString, String userBasicDirectoryString) {
        this.dataDirectoryString = dataDirectoryString;
        this.basicDirectoryString = basicDirectoryString;
        this.userBasicDirectoryString = userBasicDirectoryString;
    }

    public void initSystem() {
        logger.info("系统初始化中。");
        initDataFileSystem();
        initUserDataDirectory();

    }


    public void initDataFileSystem() {
        logger.info("初始化数据文件系统。");
        dataDirectory = Paths.get(dataDirectoryString).toFile();
        logger.info("项目数据目录为：" + dataDirectory);
        if (!dataDirectory.exists()) {
            logger.warn("项目文件夹不存在？");
            boolean mkdir = dataDirectory.mkdir();
            if (mkdir) {
                logger.info("项目文件夹创建成功。");
            } else {
                logger.error("项目文件夹创建失败!");
            }
        }
        basicDirectory = new HashMap<>();
        String[] basicDirectoryStrings = basicDirectoryString.split(",");
        for (String fileItem : basicDirectoryStrings) {
            File basicFileItem = Paths.get(dataDirectoryString, fileItem).toFile();
            if (!basicFileItem.exists()) {
                logger.warn("-" + fileItem + "文件夹不存在？");
                boolean mkdir = basicFileItem.mkdir();
                if (mkdir) {
                    logger.info("-" + fileItem + "文件夹创建成功。");
                } else {
                    logger.error("-" + fileItem + "文件夹创建失败！");
                }
            } else {
                logger.info("-" + fileItem + "文件夹已存在。");
            }
            basicDirectory.put(fileItem, basicFileItem);
        }
    }

    public void initUserDataDirectory() {
        logger.info("初始化用户数据目录。");
        List<String> users = userMapper.getAllUser();
        if (users.size() == 0) {
            logger.warn("当前数据库中无用户记录!");
        } else {
            logger.info("当前数据库中共有：" + users.size() + "名用户。");
            for (String username : users) {
                initUserDirectory(username);
            }
        }
    }

    public void initUserDirectory(String username) {
        File userDirectory = basicDirectory.get("users");
        if (!userDirectory.exists()) {
            logger.error("用户目录不存在，用户初始化失败！");
            return;
        } else {
            logger.info("用户:" + username + "初始化中。");

            File userHome = Paths.get(userDirectory.getPath(), username).toFile();
            if (!userHome.exists()) {
                logger.warn("用户：" + username + "根目录不存在？");
                if (userHome.mkdir()) {
                    logger.info("用户：" + username + "根目录创建成功。");
                } else {
                    logger.error("用户：" + username + "根目录创建失败！");
                }
            }


            String[] userBasicDirectoryStrings = userBasicDirectoryString.split(",");
            for (String userBasic : userBasicDirectoryStrings) {
                File userBasicFile = Paths.get(userDirectory.getPath(), username, userBasic).toFile();
                if (!userBasicFile.exists()) {
                    boolean mkdir = userBasicFile.mkdir();
                    if (mkdir) {
                        logger.info(userBasic + "创建成功!");
                    } else {
                        logger.error(userBasic + "创建失败!");
                    }
                }
            }

        }

    }


}
