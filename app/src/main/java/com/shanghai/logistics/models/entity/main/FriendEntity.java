package com.shanghai.logistics.models.entity.main;

public class FriendEntity {
    private String otherPhone;

    private String phone;

    private String createTime;

    private String otherRongToken;

    private String headUrl;

    private String nameRemark;

    private int id;

    private String phoneRongToken;

    public void setOtherPhone(String otherPhone){
        this.otherPhone = otherPhone;
    }
    public String getOtherPhone(){
        return this.otherPhone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
    public String getCreateTime(){
        return this.createTime;
    }
    public void setOtherRongToken(String otherRongToken){
        this.otherRongToken = otherRongToken;
    }
    public String getOtherRongToken(){
        return this.otherRongToken;
    }
    public void setHeadUrl(String headUrl){
        this.headUrl = headUrl;
    }
    public String getHeadUrl(){
        return this.headUrl;
    }
    public void setNameRemark(String nameRemark){
        this.nameRemark = nameRemark;
    }
    public String getNameRemark(){
        return this.nameRemark;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setPhoneRongToken(String phoneRongToken){
        this.phoneRongToken = phoneRongToken;
    }
    public String getPhoneRongToken(){
        return this.phoneRongToken;
    }

}
