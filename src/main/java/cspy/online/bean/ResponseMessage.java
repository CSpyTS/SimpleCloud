package cspy.online.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CSpy
 * @date 2019/4/23 12:17
 */
public class ResponseMessage {

    boolean state;
    String msg;
    List<SCFile> data;

    public ResponseMessage() {
        data = new ArrayList<>();
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void add(SCFile value) {
        data.add(value);
    }

    public List<SCFile> getData() {
        return data;
    }

    public void setData(List<SCFile> data) {
        this.data = data;
    }
}
