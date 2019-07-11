package com.shanghai.logistics.models.entity.rxbus;

/**
 * @author chensong
 * @date 2019/4/18 18:04
 */
public class UserInfoEvent {
    String userName;
    String passwrod;

    public UserInfoEvent(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }
}
