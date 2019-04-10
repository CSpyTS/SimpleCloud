package cspy.online.bean;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

/**
 * @author CSpy
 * @date 2019/3/24 14:32
 */
public class SCFile {
    private int fid;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModify;
    private String path;
    private String filename;
    private String type;
    private long size;

    public SCFile() {
        this.gmtModify = this.gmtCreate = LocalDateTime.now();
    }

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

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(LocalDateTime gmtModify) {
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


}
