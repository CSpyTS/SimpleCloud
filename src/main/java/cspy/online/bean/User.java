package cspy.online.bean;

public class User {
    private int uid;
    private String username;
    private String password;
    private String gmt_create;
    private String gmt_modify;
    private String email;
    private short email_valid;
    private String phone;
    private short phont_valid;
    private String state;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getGmt_modify() {
        return gmt_modify;
    }

    public void setGmt_modify(String gmt_modify) {
        this.gmt_modify = gmt_modify;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getEmail_valid() {
        return email_valid;
    }

    public void setEmail_valid(short email_valid) {
        this.email_valid = email_valid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public short getPhont_valid() {
        return phont_valid;
    }

    public void setPhont_valid(short phont_valid) {
        this.phont_valid = phont_valid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "用户名：" + username;
    }
}
