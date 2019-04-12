package cspy.online.dao;

import cspy.online.bean.SCFile;

import java.nio.file.Path;
import java.util.List;

/**
 * @author CSpy
 * @date 2019/4/2 20:20
 */
public interface FileMapper {
    int insertFiles(List<SCFile> fileList);
    int insertFile(SCFile scFile);

    Long getTotalSize(String path);
    List<Long> selectSize(String path);

    List<SCFile> getFileList(Path path);
}
