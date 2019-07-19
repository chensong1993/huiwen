package com.shanghai.logistics.models.entity.user;

public class SpecialEntity {
    private String aging;

    private String dedicatedLinePhoneId;

    private String endCity;

    private int id;

    private String introduction;

    private int isPromotion;

    private double latitude;

    private String lineName;

    private double longitude;

    private String mainImg;

    private String phone;

    private String serviceAddress;

    private String startCity;

    private int storeId;

    private int totalSales;

    private int volume;

    private int weight;

    public void setAging(String aging) {
        this.aging = aging;
    }

    public String getAging() {
        return this.aging;
    }

    public void setDedicatedLinePhoneId(String dedicatedLinePhoneId) {
        this.dedicatedLinePhoneId = dedicatedLinePhoneId;
    }

    public String getDedicatedLinePhoneId() {
        return this.dedicatedLinePhoneId;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getEndCity() {
        return this.endCity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIsPromotion(int isPromotion) {
        this.isPromotion = isPromotion;
    }

    public int getIsPromotion() {
        return this.isPromotion;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineName() {
        return this.lineName;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getMainImg() {
        return this.mainImg;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getServiceAddress() {
        return this.serviceAddress;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartCity() {
        return this.startCity;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getStoreId() {
        return this.storeId;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public int getTotalSales() {
        return this.totalSales;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return "SpecialEntity{" +
                "aging='" + aging + '\'' +
                ", dedicatedLinePhoneId='" + dedicatedLinePhoneId + '\'' +
                ", endCity='" + endCity + '\'' +
                ", id=" + id +
                ", introduction='" + introduction + '\'' +
                ", isPromotion=" + isPromotion +
                ", latitude=" + latitude +
                ", lineName='" + lineName + '\'' +
                ", longitude=" + longitude +
                ", mainImg='" + mainImg + '\'' +
                ", phone='" + phone + '\'' +
                ", serviceAddress='" + serviceAddress + '\'' +
                ", startCity='" + startCity + '\'' +
                ", storeId=" + storeId +
                ", totalSales=" + totalSales +
                ", volume=" + volume +
                ", weight=" + weight +
                '}';
    }
}
