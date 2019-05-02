package cspy.online.bean;

import java.sql.Timestamp;


/**
 * @author CSpy
 * @date 2019/3/24 14:32
 */
public class SCFile {
    private int fid;
    private Timestamp gmtCreate;
    private Timestamp gmtModify;
    private String path;
    private String filename;
    private String type;
    private long size;
    private String icon;
    private String fdfsId;


    @Override
    public String toString() {
        return path + "||" + filename;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFdfsId() {
        return fdfsId;
    }

    public void setFdfsId(String fdfsId) {
        this.fdfsId = fdfsId;
    }


}
