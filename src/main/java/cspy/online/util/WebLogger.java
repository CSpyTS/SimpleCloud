package cspy.online.util;

import java.util.ArrayList;
import java.util.List;

public class WebLogger {
    private List<String> logs;

    public WebLogger() {
        clearLog();
    }





    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    /**
     *
     * @param type  消息类型
     * @param message   消息内容
     */
    public void addLog(String type, String message) {
        logs.add(0,type + ":" +message);
    }

    public void clearLog() {
        if (logs != null) {
            logs.clear();
        } else {
            logs = new ArrayList<>();
        }
    }

}
