package com.shanghai.logistics.models.entity.user;

public class CarModelEntity {
    private String carImg;

    private String carModel;

    private int id;

    public void setCarImg(String carImg) {
        this.carImg = carImg;
    }

    public String getCarImg() {
        return this.carImg;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarModel() {
        return this.carModel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
