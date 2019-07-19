package com.shanghai.logistics.models.entity.user;

public class BrandLineEntity {
    private String position;

    private int isPromotion;

    private String storeAddress;

    private String storeName;

    private int id;

    private String storeImg;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setIsPromotion(int isPromotion) {
        this.isPromotion = isPromotion;
    }

    public int getIsPromotion() {
        return this.isPromotion;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreAddress() {
        return this.storeAddress;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    public String getStoreImg() {
        return this.storeImg;
    }

    @Override
    public String toString() {
        return "BrandLineEntity{" +
                "position='" + position + '\'' +
                ", isPromotion=" + isPromotion +
                ", storeAddress='" + storeAddress + '\'' +
                ", storeName='" + storeName + '\'' +
                ", id=" + id +
                ", storeImg='" + storeImg + '\'' +
                '}';
    }
}
