package com.shanghai.logistics.models.entity.rxbus;

/**
 * @author chensong
 * @date 2019/4/18 16:23
 */
public class LoginEvent {
    boolean login;
    String userName;

    public LoginEvent(boolean login, String userName) {
        this.login = login;
        this.userName = userName;
    }

    public LoginEvent(boolean login) {
        this.login = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
