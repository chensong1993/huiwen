package com.shanghai.logistics.models.entity.user;

public class UserInfoEntity {
    private String realName;

    private String headImgUrl;

    private int userServicePoint;

    private int isUser;

    public void setRealName(String realName){
        this.realName = realName;
    }
    public String getRealName(){
        return this.realName;
    }
    public void setHeadImgUrl(String headImgUrl){
        this.headImgUrl = headImgUrl;
    }
    public String getHeadImgUrl(){
        return this.headImgUrl;
    }
    public void setUserServicePoint(int userServicePoint){
        this.userServicePoint = userServicePoint;
    }
    public int getUserServicePoint(){
        return this.userServicePoint;
    }
    public void setIsUser(int isUser){
        this.isUser = isUser;
    }
    public int getIsUser(){
        return this.isUser;
    }

}
