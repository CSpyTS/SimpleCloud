package cspy.online.bean;

/**
 * @author CSpy
 * @date 2019/3/24 14:32
 */
public class File {
    private int fid;
    private String gmtCreate;
    private String gmtModify;
    private String path;
    private String filename;
    private String type;
    private int size;
    private int uid;

    @Override
    public String toString() {
        return path + "/" + filename;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(String gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
