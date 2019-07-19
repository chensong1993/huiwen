package com.shanghai.logistics.models.entity.logistics;

import java.util.List;

public class LUserInfoEntity {
    private int evaluation;

    private String realName;

    private int isStore;

    private String headImgUrl;

    private int storeServicePoint;

    private String companyName;

    private List<Store> store ;

    private int isUser;

    public void setEvaluation(int evaluation){
        this.evaluation = evaluation;
    }
    public int getEvaluation(){
        return this.evaluation;
    }
    public void setRealName(String realName){
        this.realName = realName;
    }
    public String getRealName(){
        return this.realName;
    }
    public void setIsStore(int isStore){
        this.isStore = isStore;
    }
    public int getIsStore(){
        return this.isStore;
    }
    public void setHeadImgUrl(String headImgUrl){
        this.headImgUrl = headImgUrl;
    }
    public String getHeadImgUrl(){
        return this.headImgUrl;
    }
    public void setStoreServicePoint(int storeServicePoint){
        this.storeServicePoint = storeServicePoint;
    }
    public int getStoreServicePoint(){
        return this.storeServicePoint;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public String getCompanyName(){
        return this.companyName;
    }
    public void setStore(List<Store> store){
        this.store = store;
    }
    public List<Store> getStore(){
        return this.store;
    }
    public void setIsUser(int isUser){
        this.isUser = isUser;
    }

    public int getIsUser(){
        return this.isUser;
    }
    public class Store {
        private int id;

        private int isPromotion;

        private String storeAddress;

        private String storeImg;

        private String storeName;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setIsPromotion(int isPromotion){
            this.isPromotion = isPromotion;
        }
        public int getIsPromotion(){
            return this.isPromotion;
        }
        public void setStoreAddress(String storeAddress){
            this.storeAddress = storeAddress;
        }
        public String getStoreAddress(){
            return this.storeAddress;
        }
        public void setStoreImg(String storeImg){
            this.storeImg = storeImg;
        }
        public String getStoreImg(){
            return this.storeImg;
        }
        public void setStoreName(String storeName){
            this.storeName = storeName;
        }
        public String getStoreName(){
            return this.storeName;
        }

    }

}
