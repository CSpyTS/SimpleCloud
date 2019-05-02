package cspy.online.serviceImpl;

import cspy.online.bean.SCFile;
import cspy.online.dao.FileMapper;
import cspy.online.service.FileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * @author CSpy
 * @date 2019/4/27 9:23
 */
@Component
public class FileSearchServiceImpl implements FileSearchService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public List<SCFile> searchByType(String type, String path) {
        return fileMapper.searchByType(type, path);
    }

    @Override
    public List<SCFile> getFileList(String path) {
        return fileMapper.getFileList(path);
    }

    @Override
    public List<SCFile> getDirectoryList(String path) {
        return fileMapper.getDirectoryList(path);
    }
}
