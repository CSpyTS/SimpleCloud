package cspy.online.serviceImpl;

import cspy.online.bean.ResponseMessage;
import cspy.online.bean.SCFile;
import cspy.online.dao.FileMapper;
import cspy.online.service.FileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author CSpy
 * @date 2019/4/27 9:23
 */
@Component
public class FileSearchServiceImpl implements FileSearchService {

    @Autowired
    FileMapper fileMapper;

    public List<SCFile> searchByType(String type, String path) {
        return fileMapper.searchByType(type, path);
    }

    @Override
    public List<SCFile> getFileList(String path) {
        return fileMapper.getFileList(path);
    }

    public List<SCFile> getDirectoryList(String path) {
        return fileMapper.getDirectoryList(path);
    }

    @Override
    public ResponseMessage searchPathByType(String type, String path) {
        ResponseMessage message = new ResponseMessage();
        message.setState(true);
        List<SCFile> scFiles = searchByType(type, path);
        message.setMsg("共查询出" + scFiles.size() + "个记录。");
        message.setData(scFiles);
        return message;
    }

    @Override
    public List<SCFile> getDirectory(String path, String forbiddenPath, String dirNameStr) {
        System.out.println("path:" + path + " fpath:" + forbiddenPath + " fdir=" + dirNameStr);

        String[] dirNames = dirNameStr.split(",");
        if (dirNames.length == 0 || forbiddenPath == null || forbiddenPath.equals("")) {
            return getDirectoryList(path);
        }

        List<String> dirNameList = Arrays.asList(dirNames);

        // 当path不合理，直接返回空
        for (String dirName: dirNameList) {
            if (path.startsWith(forbiddenPath + "/" + dirName + "/")) {
                return null;
            }
        }

        List<SCFile> dirList = getDirectoryList(path);
        if (path.equals(forbiddenPath)) {
            // 如果被隐藏的路径的根目录是被查询的路径的话，则将被隐藏的路径去除
            Iterator<SCFile> iterator = dirList.iterator();
            while(iterator.hasNext()) {
                SCFile scFile = iterator.next();
                if (dirNameList.contains(scFile.getFilename())) {
                    iterator.remove();
                }
            }
        }



        return dirList;
    }
}
