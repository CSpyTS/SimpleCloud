package cspy.online.bean;

import org.springframework.cglib.core.Local;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

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
//    private Timestamp sevenDaysAgoStamp;

//    DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");


    public SCFile() {
//        gmtModify = gmtCreate = "7天前";
//        sevenDaysAgoStamp = new Timestamp(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
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

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
//        if (gmtCreate != null) {
//            if (gmtCreate.before(sevenDaysAgoStamp)) {
//                this.gmtCreate = "7天前";
//            } else {
//                this.gmtCreate = dateFormat.format(gmtCreate);
//            }
//        }
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
//        if (gmtModify != null) {
//            if (gmtModify.before(sevenDaysAgoStamp)) {
//                this.gmtModify = "7天前";
//            } else {
//                this.gmtModify = dateFormat.format(gmtModify);
//            }
//        }
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
