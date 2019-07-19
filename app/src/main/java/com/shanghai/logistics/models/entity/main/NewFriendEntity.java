package com.shanghai.logistics.models.entity.main;

public class NewFriendEntity {
    private String otherPhone;

    private String nickName;

    private String headUrl;

    private String remark;

    private int id;

    private int status;

    public void setOtherPhone(String otherPhone){
        this.otherPhone = otherPhone;
    }
    public String getOtherPhone(){
        return this.otherPhone;
    }
    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    public String getNickName(){
        return this.nickName;
    }
    public void setHeadUrl(String headUrl){
        this.headUrl = headUrl;
    }
    public String getHeadUrl(){
        return this.headUrl;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }
    public String getRemark(){
        return this.remark;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
}
