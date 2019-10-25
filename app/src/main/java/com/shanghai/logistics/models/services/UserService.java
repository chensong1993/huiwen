package com.shanghai.logistics.models.services;

import com.shanghai.logistics.models.entity.ApiDataResponse;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.logistics.LStoreInfoEntity;
import com.shanghai.logistics.models.entity.main.FriendEntity;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;
import com.shanghai.logistics.models.entity.user.AddressListEntity;
import com.shanghai.logistics.models.entity.user.BrandLineEntity;
import com.shanghai.logistics.models.entity.user.CarLengthEntity;
import com.shanghai.logistics.models.entity.user.CarModelEntity;
import com.shanghai.logistics.models.entity.user.CommentEntity;
import com.shanghai.logistics.models.entity.user.HomeListEntity;
import com.shanghai.logistics.models.entity.user.LinePhoneEntity;
import com.shanghai.logistics.models.entity.user.PersonalCertification;
import com.shanghai.logistics.models.entity.user.SpecialEntity;
import com.shanghai.logistics.models.entity.user.UserInfoEntity;
import com.shanghai.logistics.models.entity.user.UserShopEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.internal.operators.single.SingleToFlowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface UserService {

    /*
    * 通讯录
    * */
    //用户首页列表
    @POST("friends/selFriends.do")
    @FormUrlEncoded
    Flowable<ApiResponse<List<FriendEntity>>> mainFriends(@Field("phone") String phone);

    //新好友请求列表
    @POST("friendRequests/selFriendRequests.do")
    @FormUrlEncoded
    Flowable<ApiResponse<List<NewFriendEntity>>> mainFriendRequests(@Field("phone") String phone);
    /*
     * 首页
     * */

    //用户首页列表
    @POST("dedicatedLine/logisticsMall.do")
    @FormUrlEncoded
    Flowable<ApiResponse<List<HomeListEntity>>> userHomeList(@Field("startCity") String startCity);

    //查看店铺信息（主页）接口
    @POST("store/selStoreInfo.do")
    @FormUrlEncoded
    Flowable<ApiResponse<UserShopEntity>> userShopDetail(@Field("storeId") String storeId, @Field("type") int type);

    //品牌物流
    @POST("store/brandLogisticsArea.do")
    @FormUrlEncoded
    Flowable<ApiResponse<List<BrandLineEntity>>> userBrandLogistics(@Field("startCity") String startCity, @Field("pageNow") int pageNow, @Field("searchContent") String searchContent);

    //品牌物流没有搜索
    @POST("store/brandLogisticsArea.do")
    @FormUrlEncoded
    Flowable<ApiResponse<List<BrandLineEntity>>> userBrandLogistics(@Field("startCity") String startCity, @Field("pageNow") int pageNow);

    //专线物流
    @POST("dedicatedLine/brandLine.do")
    @FormUrlEncoded
    Flowable<ApiResponse<List<HomeListEntity>>> userBrandLine(@Field("startCity") String startCity, @Field("pageNow") int pageNow, @Field("searchContent") String searchContent);

    //专线物流没有搜索
    @POST("dedicatedLine/brandLine.do")
    @FormUrlEncoded
    Flowable<ApiResponse<List<HomeListEntity>>> userBrandLine(@Field("startCity") String startCity, @Field("pageNow") int pageNow);


    //专线详情接口
    @POST("dedicatedLine/dedicatedLineDetail.do")
    @FormUrlEncoded
    Flowable<ApiResponse<SpecialEntity>> userSpecial(@Field("dedicatedLineId") int dedicatedLineId);

    //查看店铺信息
    @POST("store/selStoreInfo.do")
    @FormUrlEncoded
    Flowable<ApiResponse<LStoreInfoEntity>> lStoreInfo(@Field("storeId") String storeId, @Field("type") int type);


    //评论列表接口
    @POST("evaluation/selEvaluationByDedicatedLineId.do")
    @FormUrlEncoded
    Flowable<ApiResponse<List<CommentEntity>>> userCommentList(@Field("dedicatedLineId") int dedicatedLineId, @Field("pageNow") int pageNow);


    //专线电话
    @POST("dedicatedLinePhone/selDedicatedLinePhoneById.do")
    @FormUrlEncoded
    Flowable<ApiResponse<List<LinePhoneEntity>>> userLinePhoneList(@Field("dedicatedLinePhoneId") String dedicatedLinePhoneId);

    /*
     * 我的
     * */

    //查看地址簿列表接口
    @POST("address/selAddressByUserId.do")
    @FormUrlEncoded
    Flowable<ApiResponse<List<AddressListEntity>>> userAddressLis(@Field("userAccount") String userAccount);

    //个人认证接口
    @POST("personalCertification/insPersonalCertification.do")
    @Multipart
    Single<ApiResponse<Integer>> userCertification(@PartMap Map<String, RequestBody> files);

    //修改个人实名认证接口
    @POST("personalCertification/updPersonalCertification.do")
    @Multipart
    Single<ApiResponse<Integer>> userUpCertification(@PartMap Map<String, RequestBody> files);

    //个人认证接口查询
    @POST("personalCertification/selPersonalCertificationByPhone.do")
    @FormUrlEncoded
    Flowable<ApiResponse<PersonalCertification>> userPersonalCertification(@Field("phone") String phone);



    //个人中心接口
    @POST("userInfo/selUserInfoByUser.do")
    @FormUrlEncoded
    Flowable<ApiResponse<UserInfoEntity>> userInfo(@Field("phone") String phone);

    /*
     * 发布
     * */

    //车型
    @POST("carModel/selCarModel.do")
    Flowable<ApiDataResponse<List<CarModelEntity>>> carModel();

    //车长
    @POST("carLength/selCarLength.do")
    Flowable<ApiDataResponse<List<CarLengthEntity>>> carLength();

    //派单接口
    @POST("sendOrder/insSendOrder.do")
    @Multipart
    Single<ApiResponse<Integer>> sendOrder(@PartMap Map<String, RequestBody> sendOrder);

    //发货接口
    @POST("orderInfo/insOrderInfo.do")
    @Multipart
    Single<ApiResponse<Integer>> orderInfo(@PartMap Map<String, RequestBody> orderInfo);


}
