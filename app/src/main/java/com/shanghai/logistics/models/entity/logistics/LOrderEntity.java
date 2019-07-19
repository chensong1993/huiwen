package com.shanghai.logistics.models.entity.logistics;

import java.io.Serializable;

public class LOrderEntity implements Serializable {
    private String orderNo;

    private String loadingTime;

    private String startAddress;

    private String endUserName;

    private String goods;

    private int weight;

    private String remark;

    private int estimatedCost;

    private int startDeliveryType;

    private String startPhone;

    private int volume;

    private int pieces;

    private String realName;

    private String endPhone;

    private String payType;

    private String headImgUrl;

    private int endDeliveryType;

    private String createTime;

    private String startUserName;

    private String endAddress;

    private String carModel;

    private String receipt;

    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }
    public String getOrderNo(){
        return this.orderNo;
    }
    public void setLoadingTime(String loadingTime){
        this.loadingTime = loadingTime;
    }
    public String getLoadingTime(){
        return this.loadingTime;
    }
    public void setStartAddress(String startAddress){
        this.startAddress = startAddress;
    }
    public String getStartAddress(){
        return this.startAddress;
    }
    public void setEndUserName(String endUserName){
        this.endUserName = endUserName;
    }
    public String getEndUserName(){
        return this.endUserName;
    }
    public void setGoods(String goods){
        this.goods = goods;
    }
    public String getGoods(){
        return this.goods;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public int getWeight(){
        return this.weight;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }
    public String getRemark(){
        return this.remark;
    }
    public void setEstimatedCost(int estimatedCost){
        this.estimatedCost = estimatedCost;
    }
    public int getEstimatedCost(){
        return this.estimatedCost;
    }
    public void setStartDeliveryType(int startDeliveryType){
        this.startDeliveryType = startDeliveryType;
    }
    public int getStartDeliveryType(){
        return this.startDeliveryType;
    }
    public void setStartPhone(String startPhone){
        this.startPhone = startPhone;
    }
    public String getStartPhone(){
        return this.startPhone;
    }
    public void setVolume(int volume){
        this.volume = volume;
    }
    public int getVolume(){
        return this.volume;
    }
    public void setPieces(int pieces){
        this.pieces = pieces;
    }
    public int getPieces(){
        return this.pieces;
    }
    public void setRealName(String realName){
        this.realName = realName;
    }
    public String getRealName(){
        return this.realName;
    }
    public void setEndPhone(String endPhone){
        this.endPhone = endPhone;
    }
    public String getEndPhone(){
        return this.endPhone;
    }
    public void setPayType(String payType){
        this.payType = payType;
    }
    public String getPayType(){
        return this.payType;
    }
    public void setHeadImgUrl(String headImgUrl){
        this.headImgUrl = headImgUrl;
    }
    public String getHeadImgUrl(){
        return this.headImgUrl;
    }
    public void setEndDeliveryType(int endDeliveryType){
        this.endDeliveryType = endDeliveryType;
    }
    public int getEndDeliveryType(){
        return this.endDeliveryType;
    }
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
    public String getCreateTime(){
        return this.createTime;
    }
    public void setStartUserName(String startUserName){
        this.startUserName = startUserName;
    }
    public String getStartUserName(){
        return this.startUserName;
    }
    public void setEndAddress(String endAddress){
        this.endAddress = endAddress;
    }
    public String getEndAddress(){
        return this.endAddress;
    }
    public void setCarModel(String carModel){
        this.carModel = carModel;
    }
    public String getCarModel(){
        return this.carModel;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
