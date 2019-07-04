package com.example.game.po;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    private String userName;

    private Integer userSex;

    private String userPhone;

    private String userAddr;

    private String userRegistrationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr == null ? null : userAddr.trim();
    }

    public String getUserRegistrationTime() {
        return userRegistrationTime;
    }

    public void setUserRegistrationTime(String userRegistrationTime) {
        this.userRegistrationTime = userRegistrationTime == null ? null : userRegistrationTime.trim();
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", userSex=" + userSex + ", userPhone='" + userPhone + '\'' + ", userAddr='" + userAddr + '\'' + ", userRegistrationTime='" + userRegistrationTime + '\'' + '}';
    }
}