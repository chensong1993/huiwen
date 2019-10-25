package com.shanghai.logistics.models.http;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.LogisticsService;
import com.shanghai.logistics.models.services.UserService;
import com.shanghai.logistics.ui.logistics_activity.AddPhoneActivity;
import com.shanghai.logistics.ui.logistics_activity.AddShopActivity;
import com.shanghai.logistics.util.FileUploadUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class Uploading {

    private static final String TAG = "Uploading";

    /**
     * 新增店铺
     */
    @SuppressLint("CheckResult")
    public static void AddStores(List<String> FilePathList, String phone, String storeName, String contactPhone,
                                 String storeAddress, String position, String longitude, String latitude,
                                 String introduction, String contactName, SingleObserver<ApiResponse<Integer>> onRequest) {
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < FilePathList.size(); i++) {
            fileList.add(new File(FilePathList.get(i)));
        }

        Map<String, RequestBody> map = new HashMap<>();

        map.put("phone", FileUploadUtil.requestBody(phone));
        map.put("storeName", FileUploadUtil.requestBody(storeName));
        map.put("contactPhone", FileUploadUtil.requestBody(contactPhone));
        map.put("storeAddress", FileUploadUtil.requestBody(storeAddress));
        map.put("position", FileUploadUtil.requestBody(position));
        map.put("longitude", FileUploadUtil.requestBody(longitude));
        map.put("latitude", FileUploadUtil.requestBody(latitude));
        map.put("introduction", FileUploadUtil.requestBody(introduction));
        map.put("contactName", FileUploadUtil.requestBody(contactName));

        ApiService.getInstance()
                .create(LogisticsService.class, Constants.MAIN_URL)
                .AddStore(FileUploadUtil.uploadInfo("storeImg", map, fileList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(onRequest);
    }

    /**
     * 新增商品
     */

    @SuppressLint("CheckResult")
    public static void AddShop(List<String> ImgFileList, String phone, String phoneId, String receiverId, String lineName, int storeId, String introduction,
                               String weight, String volume, String aging, String startCity, String endCity, int isShelf,
                               int isBusiness, int isPromotion, double longitude, double latitude, String serviceAddress, SingleObserver<ApiResponse<Integer>> disposeDataListener) {
        List<File> fileList = new ArrayList<>();

        String[] fileName = {"mainImg", "detailImg"};
        for (int i = 0; i < ImgFileList.size(); i++) {
            fileList.add(new File(ImgFileList.get(i)));
        }

        Map<String, RequestBody> map = new HashMap<>();

        map.put("phone", FileUploadUtil.requestBody(phone));
        if (!phoneId.isEmpty()) {
            // Log.i(TAG, "uploadInfo: " + phoneId);
            map.put("dedicatedLinePhoneId", FileUploadUtil.requestBody(phoneId));
        }

        map.put("receiverId", FileUploadUtil.requestBody(receiverId));
        map.put("lineName", FileUploadUtil.requestBody(lineName));
        map.put("storeId", FileUploadUtil.requestBody(storeId + ""));
        map.put("introduction", FileUploadUtil.requestBody(introduction));
        map.put("weight", FileUploadUtil.requestBody(weight));
        map.put("volume", FileUploadUtil.requestBody(volume));
        map.put("aging", FileUploadUtil.requestBody(aging));
        map.put("startCity", FileUploadUtil.requestBody(startCity));
        map.put("endCity", FileUploadUtil.requestBody(endCity));
        map.put("isShelf", FileUploadUtil.requestBody(isShelf + ""));
        map.put("isBusiness", FileUploadUtil.requestBody(isBusiness + ""));
        map.put("isPromotion", FileUploadUtil.requestBody(isPromotion + ""));
        map.put("longitude", FileUploadUtil.requestBody(longitude + ""));
        map.put("latitude", FileUploadUtil.requestBody(latitude + ""));
        map.put("serviceAddress", FileUploadUtil.requestBody(serviceAddress));

        ApiService.getInstance()
                .create(LogisticsService.class, Constants.MAIN_URL)
                .dedicatedLine(FileUploadUtil.uploadInfo(fileName, map, fileList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposeDataListener);
    }

    /**
     * 添加手机号
     */
    @SuppressLint("CheckResult")
    public static void AddPhone(String phone, String ps, SingleObserver<ApiResponse<Integer>> onRequest) {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("phone", FileUploadUtil.requestBody(phone));
        map.put("name", FileUploadUtil.requestBody(ps));
        ApiService.getInstance()
                .create(LogisticsService.class, Constants.MAIN_URL)
                .dedicatedLinePhone(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(onRequest);

    }

    /**
     * 物流端认证
     */
    @SuppressLint("CheckResult")
    public static void LogisticsCertification(File file, String phone, String companyName, String address, String addressDetail, SingleObserver<ApiResponse<Integer>> onRequest) {
        Map<String, RequestBody> map = new HashMap<>();

        map.put("phone", FileUploadUtil.requestBody(phone));
        map.put("companyName", FileUploadUtil.requestBody(companyName));
        map.put("address", FileUploadUtil.requestBody(address));
        map.put("addressDetail", FileUploadUtil.requestBody(addressDetail));
        Log.i(TAG, "uploadInfo: " + file.getPath());
        ApiService.getInstance()
                .create(LogisticsService.class, Constants.MAIN_URL)
                .enterpriseCertification(FileUploadUtil.uploadInfo("businessLicenseImg", map, file))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(onRequest);
    }

    /**
     * 物流端修改认证
     */
    @SuppressLint("CheckResult")
    public static void UpLogisticsCertification(File file, String phone, String companyName, String address, String addressDetail, SingleObserver<ApiResponse<Integer>> onRequest) {
        Map<String, RequestBody> map = new HashMap<>();

        map.put("phone", FileUploadUtil.requestBody(phone));
        map.put("companyName", FileUploadUtil.requestBody(companyName));
        map.put("address", FileUploadUtil.requestBody(address));
        map.put("addressDetail", FileUploadUtil.requestBody(addressDetail));
        Log.i(TAG, "uploadInfo: " + file.getPath());
        ApiService.getInstance()
                .create(LogisticsService.class, Constants.MAIN_URL)
                .upEnterpriseCertification(FileUploadUtil.uploadInfo("businessLicenseImg", map, file))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(onRequest);
    }

    /**
     * 用户认证
     */
    @SuppressLint("CheckResult")
    public static void UserCertification(File nativeFile, File ZFile, File HFile, String phone,
                                         String IDCardNum, String realName, SingleObserver<ApiResponse<Integer>> onRequest) {

        File[] files = {nativeFile, ZFile, HFile};
        Map<String, RequestBody> map = new HashMap<>();
        String[] fileName = {"headImgUrl", "IDCardImgFront", "IDCardImgReverse"};
        map.put("phone", FileUploadUtil.requestBody(phone));
        map.put("IDCardNum", FileUploadUtil.requestBody(IDCardNum));
        map.put("realName", FileUploadUtil.requestBody(realName));
        Log.i(TAG, "uploadInfo: " + nativeFile.getPath());


        ApiService.getInstance()
                .create(UserService.class, Constants.MAIN_URL)
                .userCertification(FileUploadUtil.uploadInfo(fileName, map, files))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(onRequest);
    }

    /**
     * 用户修改认证
     */
    @SuppressLint("CheckResult")
    public static void UserUpCertification(File nativeFile, File ZFile, File HFile, String phone,
                                           String IDCardNum, String realName, SingleObserver<ApiResponse<Integer>> onRequest) {

        File[] files = {nativeFile, ZFile, HFile};
        Map<String, RequestBody> map = new HashMap<>();
        String[] fileName = {"headImgUrl", "IDCardImgFront", "IDCardImgReverse"};
        map.put("phone", FileUploadUtil.requestBody(phone));
        map.put("IDCardNum", FileUploadUtil.requestBody(IDCardNum));
        map.put("realName", FileUploadUtil.requestBody(realName));
        Log.i(TAG, "uploadInfo: " + nativeFile.getPath());


        ApiService.getInstance()
                .create(UserService.class, Constants.MAIN_URL)
                .userUpCertification(FileUploadUtil.uploadInfo(fileName, map, files))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(onRequest);
    }

    /**
     * 新订单
     */
    @SuppressLint("CheckResult")
    public static void NewOrderDetail(String orderNo, int type, String rb, SingleObserver<ApiResponse<Integer>> onRequest) {
        Map<String, RequestBody> map = new HashMap<>();
        Log.i(TAG, "uploadInfo: " + orderNo);
        map.put("orderNo", FileUploadUtil.requestBody(orderNo));
        map.put("type", FileUploadUtil.requestBody(type + ""));
        if (type == 2) {
            map.put("refuseRemark", FileUploadUtil.requestBody(rb));
        }

        ApiService.getInstance()
                .create(LogisticsService.class, Constants.MAIN_URL)
                .storeDealWithOrder(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(onRequest);
    }

    /**
     * 物流下单
     */
    @SuppressLint("CheckResult")
    public static void LPlaceAnOrder(String orderNo, int startDeliveryType, String startUserName, String startPhone,
                                     String startAddress, int endDeliveryType, String endUserName, String endPhone,
                                     String endAddress, String goods, String volume, String weight, String pieces,
                                     String loadingTime, String carModel, String payType, String receipt, String estimatedCost,
                                     SingleObserver<ApiResponse<Integer>> onRequest) {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("orderNo", FileUploadUtil.requestBody(orderNo));
        map.put("startDeliveryType", FileUploadUtil.requestBody(startDeliveryType + ""));
        map.put("startUserName", FileUploadUtil.requestBody(startUserName));
        map.put("startPhone", FileUploadUtil.requestBody(startPhone));
        map.put("startAddress", FileUploadUtil.requestBody(startAddress));
        map.put("endDeliveryType", FileUploadUtil.requestBody(endDeliveryType + ""));
        map.put("endUserName", FileUploadUtil.requestBody(endUserName));
        map.put("endPhone", FileUploadUtil.requestBody(endPhone));
        map.put("endAddress", FileUploadUtil.requestBody(endAddress));
        map.put("goods", FileUploadUtil.requestBody(goods));
        map.put("volume", FileUploadUtil.requestBody(volume));
        map.put("weight", FileUploadUtil.requestBody(weight));
        map.put("pieces", FileUploadUtil.requestBody(pieces));
        map.put("loadingTime", FileUploadUtil.requestBody(loadingTime));
        map.put("carModel", FileUploadUtil.requestBody(carModel));
        map.put("payType", FileUploadUtil.requestBody(payType));
        map.put("receipt", FileUploadUtil.requestBody(receipt));
        // map.put("remark", FileUploadUtil.requestBody(mEtRemarks.getText().toString()));
        map.put("estimatedCost", FileUploadUtil.requestBody(estimatedCost));

        ApiService.getInstance()
                .create(LogisticsService.class, Constants.MAIN_URL)
                .uploadOrderInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(onRequest);
    }

    /**
     * 用户下单
     */
    @SuppressLint("CheckResult")
    public static void UserPlaceAnOrder(String userAccount, String storeAccount, int storeId, int userType,
                                        int dedicatedLine, int startDeliveryType, String startUserName, String startPhone,
                                        String startAddress, int endDeliveryType, String endUserName, String endPhone, String endAddress,
                                        String goods, String volume, String weight, String pieces, String loadingTime, String carModel,
                                        String payType, String receipt, String remark, String estimatedCost, int type,
                                        SingleObserver<ApiResponse<Integer>> onRequest) {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("userAccount", FileUploadUtil.requestBody(userAccount));
        map.put("storeAccount", FileUploadUtil.requestBody(storeAccount));
        map.put("storeId", FileUploadUtil.requestBody(storeId + ""));
        map.put("userType", FileUploadUtil.requestBody(userType + ""));
        map.put("dedicatedLine", FileUploadUtil.requestBody(dedicatedLine + ""));
        map.put("startDeliveryType", FileUploadUtil.requestBody(startDeliveryType + ""));
        map.put("startUserName", FileUploadUtil.requestBody(startUserName));
        map.put("startPhone", FileUploadUtil.requestBody(startPhone));
        map.put("startAddress", FileUploadUtil.requestBody(startAddress));
        map.put("endDeliveryType", FileUploadUtil.requestBody(endDeliveryType + ""));
        map.put("endUserName", FileUploadUtil.requestBody(endUserName));
        map.put("endPhone", FileUploadUtil.requestBody(endPhone));
        map.put("endAddress", FileUploadUtil.requestBody(endAddress));
        map.put("goods", FileUploadUtil.requestBody(goods));
        map.put("volume", FileUploadUtil.requestBody(volume));
        map.put("weight", FileUploadUtil.requestBody(weight));
        map.put("pieces", FileUploadUtil.requestBody(pieces));
        map.put("loadingTime", FileUploadUtil.requestBody(loadingTime));
        map.put("carModel", FileUploadUtil.requestBody(carModel));
        map.put("payType", FileUploadUtil.requestBody(payType));
        map.put("receipt", FileUploadUtil.requestBody(receipt));
        map.put("remark", FileUploadUtil.requestBody(remark));
        map.put("estimatedCost", FileUploadUtil.requestBody(estimatedCost));
        map.put("type", FileUploadUtil.requestBody(type + ""));

        ApiService.getInstance()
                .create(UserService.class, Constants.MAIN_URL)
                .orderInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(onRequest);
    }

    /**
     * 快速发货
     */
    @SuppressLint("CheckResult")
    public static void SendOrder(String userAccount, String startAddress, String endAddress, String goods,
                                 String volume, String weight, String pieces, String loadingTime,
                                 String loadMethod, String carModel, String payType, String receipt, String remark,
                                 String estimatedCost, int type, int isDesignation, String driver,
                                 SingleObserver<ApiResponse<Integer>> onRequest) {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("userAccount", FileUploadUtil.requestBody(userAccount));
        map.put("startAddress", FileUploadUtil.requestBody(startAddress));
        map.put("endAddress", FileUploadUtil.requestBody(endAddress));
        map.put("goods", FileUploadUtil.requestBody(goods));
        map.put("volume", FileUploadUtil.requestBody(volume));
        map.put("weight", FileUploadUtil.requestBody(weight));
        map.put("pieces", FileUploadUtil.requestBody(pieces));
        map.put("loadingTime", FileUploadUtil.requestBody(loadingTime));
        map.put("loadMethod", FileUploadUtil.requestBody(loadMethod));
        map.put("carModel", FileUploadUtil.requestBody(carModel));
        map.put("payType", FileUploadUtil.requestBody(payType));
        map.put("receipt", FileUploadUtil.requestBody(receipt));
        map.put("remark", FileUploadUtil.requestBody(remark));
        map.put("estimatedCost", FileUploadUtil.requestBody(estimatedCost));
        map.put("type", FileUploadUtil.requestBody(type+""));
        map.put("isDesignation", FileUploadUtil.requestBody(isDesignation+""));
        map.put("driver", FileUploadUtil.requestBody(driver));


        ApiService.getInstance()
                .create(UserService.class, Constants.MAIN_URL)
                .orderInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(onRequest);
    }

    /**
     * 用户派单
     */
    public static void SendOrder(String phone, String startUserName, String startPhone, String startAddress,
                                 String endUserName, String endPhone, String endAddress, float freight, String loadingTime,
                                 String loadMethod, String agentFee, String carModel, String payType, float totalFee,
                                 float driverDeposit, int receipt, String driverName, String driverPhone, String orderNo) {

    }
}
