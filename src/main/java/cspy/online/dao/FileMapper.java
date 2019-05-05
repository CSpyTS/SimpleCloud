package cspy.online.dao;

import cspy.online.bean.SCFile;
import org.apache.ibatis.annotations.Param;

import java.nio.file.Path;
import java.util.List;

/**
 * @author CSpy
 * @date 2019/4/2 20:20
 */
public interface FileMapper {
    int insertFiles(List<SCFile> fileList);
    int insertFile(SCFile scFile);

//    int existPath(String path);

    Long getTotalSize(String path);
    List<Long> selectSize(String path);

    List<SCFile> getFileList(String path);
    List<SCFile> getDirectoryList(String path);

    int selectDirectory(@Param("path") String path, @Param("dirName") String dirName);
    SCFile selectFile(@Param("path") String path, @Param("fileName") String fileName);
    int deleteOneFile(@Param("path") String path, @Param("fileName") String fileName);

    /**
     *
     * @param path  删除路径path下的文件和子文件
     * @return  删除的记录数
     */
    int deletePath(@Param("path") String path);

    /**
     *
     * @param path  删除路径为path， 文件名为fileName的文件夹
     * @param fileName  目录名
     * @return  删除的记录数
     */
    int deleteDirectory(@Param("path") String path, @Param("fileName") String fileName);

    int deleteFiles(@Param("path") String path, @Param("fileName") List<String> fileName);
    SCFile getDirectory(@Param("path") String path, @Param("dirName") String dirName);

    List<SCFile> searchByType(@Param("type") String type,@Param("path") String path);
    List<SCFile> selectChildren(@Param("path") String path);

    Integer duplicateFile(List<SCFile> scFiles);

    int updateSCFiles(List<SCFile> scFiles);


    SCFile selectByFileName(@Param("path") String path, @Param("fileName") String fileName, @Param("isDir") boolean isDir);
}
