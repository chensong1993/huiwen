package com.shanghai.logistics.models.entity.user;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.shanghai.logistics.models.entity.logistics.LStoreInfoEntity;

import java.io.Serializable;
import java.util.List;

@SuppressLint("ParcelCreator")
public class UserShopEntity implements Serializable {


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

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(storeAddress);
//        parcel.writeInt(storeServicePoint);
//        parcel.writeDouble(latitude);
//        parcel.writeList(dedicatedLine);
//        parcel.writeString(storeName);
//        parcel.writeString(position);
//        parcel.writeString(storeImg);
//        parcel.writeString(contactPhone);
//        parcel.writeInt(isPass);
//        parcel.writeString(introduction);
//        parcel.writeDouble(longitude);
//
//    }

//    public static final Parcelable.Creator<UserShopEntity> CREATOR = new Parcelable.Creator<UserShopEntity>() {
//        public UserShopEntity createFromParcel(Parcel in) {
//            return new UserShopEntity(in);
//        }
//
//        public UserShopEntity[] newArray(int size) {
//            return new UserShopEntity[size];
//        }
//    };
//
//    public UserShopEntity(String storeAddress, int storeServicePoint, double latitude, List<DedicatedLine> dedicatedLine, String storeName, String position, String storeImg, String contactPhone, int isPass, String introduction, double longitude) {
//        this.storeAddress = storeAddress;
//        this.storeServicePoint = storeServicePoint;
//        this.latitude = latitude;
//        this.dedicatedLine = dedicatedLine;
//        this.storeName = storeName;
//        this.position = position;
//        this.storeImg = storeImg;
//        this.contactPhone = contactPhone;
//        this.isPass = isPass;
//        this.introduction = introduction;
//        this.longitude = longitude;
//    }
//
//
//    private UserShopEntity(Parcel in) {
//        this.storeAddress = in.readString();
//        this.storeServicePoint = in.readInt();
//        this.latitude = in.readDouble();
//        this.dedicatedLine = in.readList(dedicatedLine,UserShopEntity.class);
//        this.storeName = in.readString();
//        this.position = in.readString();
//        this.storeImg = in.readString();
//        this.contactPhone = in.readString();
//        this.isPass = in.readInt();
//        this.introduction = in.readString();
//        this.longitude = in.readDouble();
//    }



    public class DedicatedLine implements Serializable{

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

    @Override
    public String toString() {
        return "UserShopEntity{" +
                "storeAddress='" + storeAddress + '\'' +
                ", storeServicePoint=" + storeServicePoint +
                ", latitude=" + latitude +
                ", dedicatedLine=" + dedicatedLine +
                ", storeName='" + storeName + '\'' +
                ", position='" + position + '\'' +
                ", storeImg='" + storeImg + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", isPass=" + isPass +
                ", introduction='" + introduction + '\'' +
                ", longitude=" + longitude +
                '}';
    }
}
