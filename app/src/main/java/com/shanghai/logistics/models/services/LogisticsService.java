package com.shanghai.logistics.models.services;

import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.models.entity.logistics.LStoreInfoEntity;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface LogisticsService {

    /*
    * 订单
    * */

    //新订单
    @POST("orderInfo/selOrderInfoByStoreNewOrder.do")
    @FormUrlEncoded
    Flowable<ApiResponse<List<LOrderEntity>>> orderInfo(@Field("storeAccount") String storeAccount,@Field("status") int status,@Field("pageNow") int pageNow);

    //企业认证接口
    @POST("enterpriseCertification/updEnterpriseCertification.do")
    @Multipart
    Single<ApiResponse<Integer>> enterpriseCertification(@PartMap Map<String, RequestBody> files);

    //物流中心个人信息
    @POST("userInfo/selUserInfoByStore.do")
    @FormUrlEncoded
    Flowable<ApiResponse<LUserInfoEntity>> lUserInfo(@Field("phone") String phone);


    //查看店铺信息
    @POST("store/selStoreInfo.do")
    @FormUrlEncoded
    Flowable<ApiResponse<LStoreInfoEntity>> lStoreInfo(@Field("storeId") String storeId, @Field("type") int type);


    //新增专线详情
    @POST("dedicatedLine/insDedicatedLine.do")
    @Multipart
    Single<ApiResponse<Integer>> dedicatedLine(@PartMap Map<String, RequestBody> files);


    //新增专线手机号详情
    @POST("dedicatedLinePhone/insDedicatedLinePhone.do")
    @Multipart
    Single<ApiResponse<Integer>> dedicatedLinePhone(@PartMap Map<String, RequestBody> files);


    //开单接口
    @POST("orderInfo/updOrderInfo.do")
    @Multipart
    Single<ApiResponse<Integer>> uploadOrderInfo(@PartMap Map<String, RequestBody> files);


    //开单详情
    @POST("orderInfo/selOrderInfoByStoreNewOrderDetail.do")
    @FormUrlEncoded
    Flowable<ApiResponse<LOrderEntity>> orderInfoDetail(@Field("orderNo") String orderNo);


    //接单与拒接
    @POST("orderInfo/storeDealWithOrder.do")
    @Multipart
    Single<ApiResponse<Integer>> storeDealWithOrder(@PartMap Map<String, RequestBody> files);

    //发车接口
    @POST("sendOrder/insSendOrderByCheCi.do")
    @Multipart
    Single<ApiResponse<Integer>> sendOrder(@PartMap Map<String, RequestBody> files);

    //新增商铺
    @POST("store/insStore.do")
    @Multipart
    Single<ApiResponse<Integer>> AddStore(@PartMap Map<String, RequestBody> files);
}
