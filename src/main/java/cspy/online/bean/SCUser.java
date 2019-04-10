package cspy.online.bean;

import cspy.online.util.Formatter;

import java.time.LocalDateTime;

public class SCUser {
    private int uid;
    private String username;
    private String password;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModify;
    private String email;
    private short emailValid;
    private String phone;
    private short phoneValid;
    private String state;


    public SCUser() {
        gmtCreate = gmtModify = LocalDateTime.now();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getEmailValid() {
        return emailValid;
    }

    public void setEmailValid(short emailValid) {
        this.emailValid = emailValid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = Formatter.formatPhoneNumber(phone);
    }

    public short getPhoneValid() {
        return phoneValid;
    }

    public void setPhoneValid(short phoneValid) {
        this.phoneValid = phoneValid;
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
