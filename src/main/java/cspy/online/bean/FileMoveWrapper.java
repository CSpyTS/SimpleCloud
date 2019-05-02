package cspy.online.bean;

import java.util.List;

/**
 * @author CSpy
 * @date 2019/5/2 23:09
 */
public class FileMoveWrapper {
    String path;
    String destPath;
    List<SimpleFile> fileNameList;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public List<SimpleFile> getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(List<SimpleFile> fileNameList) {
        this.fileNameList = fileNameList;
    }
}
