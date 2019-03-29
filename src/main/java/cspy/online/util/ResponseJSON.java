package cspy.online.util;

import java.util.List;

/**
 * @author CSpy
 * @date 2019/3/23 20:00
 */
public class ResponseJSON {
    /**
     * code
     * 200 正常
     * 100 失败
     */
    private int code;
    private String message;
    private List<Object> data;

    public ResponseJSON(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
