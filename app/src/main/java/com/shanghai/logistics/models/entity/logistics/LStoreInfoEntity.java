package com.shanghai.logistics.models.entity.logistics;

import java.util.List;

public class LStoreInfoEntity {
    private String storeAddress;

    private int storeServicePoint;

    private double latitude;

    private List<DedicatedLine> dedicatedLine;

    private String storeName;

    private String position;

    private String storeImg;

    private String contactPhone;

    private int isPass;

    private String introduction;

    private double longitude;

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreAddress() {
        return this.storeAddress;
    }

    public void setStoreServicePoint(int storeServicePoint) {
        this.storeServicePoint = storeServicePoint;
    }

    public int getStoreServicePoint() {
        return this.storeServicePoint;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setDedicatedLine(List<DedicatedLine> dedicatedLine) {
        this.dedicatedLine = dedicatedLine;
    }

    public List<DedicatedLine> getDedicatedLine() {
        return this.dedicatedLine;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    public String getStoreImg() {
        return this.storeImg;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }

    public int getIsPass() {
        return this.isPass;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public class DedicatedLine {
        private String aging;

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

        private int volume;

        private int weight;

        public void setAging(String aging) {
            this.aging = aging;
        }

        public String getAging() {
            return this.aging;
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

    }
}
