package cspy.online.bean;

/**
 * @author CSpy
 * @date 2019/5/2 13:06
 */
public class SimpleFile {
    String fileName;
    boolean dir;


    public boolean isDir() {
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}
