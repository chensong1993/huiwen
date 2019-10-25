package com.shanghai.logistics.models.entity;

import android.os.Parcelable;

/**
 * @author chensong
 * @date 2019/4/17 11:27
 */
public class LoginEntity  {
    private String createTime;

    private int driverServicePoint;

    private String headUrl;

    private int id;

    private int identity;

    private int isDel;

    private int isDriver;

    private int isStore;

    private int isUser;

    private String nickName;

    private String password;

    private String phone;

    private String rongTargetId;

    private String rongToken;

    private int storeServicePoint;

    private int userServicePoint;

    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
    public String getCreateTime(){
        return this.createTime;
    }
    public void setDriverServicePoint(int driverServicePoint){
        this.driverServicePoint = driverServicePoint;
    }
    public int getDriverServicePoint(){
        return this.driverServicePoint;
    }
    public void setHeadUrl(String headUrl){
        this.headUrl = headUrl;
    }
    public String getHeadUrl(){
        return this.headUrl;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setIdentity(int identity){
        this.identity = identity;
    }
    public int getIdentity(){
        return this.identity;
    }
    public void setIsDel(int isDel){
        this.isDel = isDel;
    }
    public int getIsDel(){
        return this.isDel;
    }
    public void setIsDriver(int isDriver){
        this.isDriver = isDriver;
    }
    public int getIsDriver(){
        return this.isDriver;
    }
    public void setIsStore(int isStore){
        this.isStore = isStore;
    }
    public int getIsStore(){
        return this.isStore;
    }
    public void setIsUser(int isUser){
        this.isUser = isUser;
    }
    public int getIsUser(){
        return this.isUser;
    }
    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    public String getNickName(){
        return this.nickName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setRongTargetId(String rongTargetId){
        this.rongTargetId = rongTargetId;
    }
    public String getRongTargetId(){
        return this.rongTargetId;
    }
    public void setRongToken(String rongToken){
        this.rongToken = rongToken;
    }
    public String getRongToken(){
        return this.rongToken;
    }
    public void setStoreServicePoint(int storeServicePoint){
        this.storeServicePoint = storeServicePoint;
    }
    public int getStoreServicePoint(){
        return this.storeServicePoint;
    }
    public void setUserServicePoint(int userServicePoint){
        this.userServicePoint = userServicePoint;
    }
    public int getUserServicePoint(){
        return this.userServicePoint;
    }

}
