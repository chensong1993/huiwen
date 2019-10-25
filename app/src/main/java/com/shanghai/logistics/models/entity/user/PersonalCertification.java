package com.shanghai.logistics.models.entity.user;

public class PersonalCertification {

    String headImgUrl;
    String IDCardImgFront;
    String IDCardImgReverse;
    String IDCardNum;
    String realName;

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getIDCardImgFront() {
        return IDCardImgFront;
    }

    public void setIDCardImgFront(String IDCardImgFront) {
        this.IDCardImgFront = IDCardImgFront;
    }

    public String getIDCardImgReverse() {
        return IDCardImgReverse;
    }

    public void setIDCardImgReverse(String IDCardImgReverse) {
        this.IDCardImgReverse = IDCardImgReverse;
    }

    public String getIDCardNum() {
        return IDCardNum;
    }

    public void setIDCardNum(String IDCardNum) {
        this.IDCardNum = IDCardNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "PersonalCertification{" +
                "headImgUrl='" + headImgUrl + '\'' +
                ", IDCardImgFront='" + IDCardImgFront + '\'' +
                ", IDCardImgReverse='" + IDCardImgReverse + '\'' +
                ", IDCardNum='" + IDCardNum + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
