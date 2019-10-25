package com.shanghai.logistics.models;

import com.shanghai.logistics.models.entity.ApiDataResponse;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.models.entity.logistics.LEnterpriseInfo;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.models.entity.logistics.LStoreInfoEntity;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;
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
import com.shanghai.logistics.models.http.HttpHelper;


import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.internal.operators.single.SingleToFlowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class DataManager implements HttpHelper {

    HttpHelper mHttpHelper;


    public DataManager(HttpHelper httpHelper) {
        mHttpHelper = httpHelper;

    }


    @Override
    public Flowable<ApiResponse<LoginEntity>> getLogin(String phone, String password) {
        return mHttpHelper.getLogin(phone, password);
    }

    @Override
    public Flowable<ApiResponse<String>> getRegister(String phone, String password, String code, String nickName) {
        return mHttpHelper.getRegister(phone, password, code, nickName);
    }

    @Override
    public Flowable<ApiResponse<String>> getSendCode(String phone, int type) {
        return mHttpHelper.getSendCode(phone, type);
    }

    @Override
    public Flowable<ApiResponse<String>> getForgetPassword(String phone, String password, String code) {
        return mHttpHelper.getForgetPassword(phone, password, code);
    }

    //用户首页列表
    @Override
    public Flowable<ApiResponse<List<HomeListEntity>>> getUserHomeList(String startCity) {
        return mHttpHelper.getUserHomeList(startCity);
    }

    //商户详情
    @Override
    public Flowable<ApiResponse<UserShopEntity>> getUserShopDetail(String storeId, int type) {
        return mHttpHelper.getUserShopDetail(storeId, type);
    }

    @Override
    public Flowable<ApiResponse<SpecialEntity>> getUserSpecial(int dedicatedLineId) {
        return mHttpHelper.getUserSpecial(dedicatedLineId);
    }

    @Override
    public Flowable<ApiResponse<List<CommentEntity>>> getCommentList(int dedicatedLineId, int pageNow) {
        return mHttpHelper.getCommentList(dedicatedLineId, pageNow);
    }

    @Override
    public Flowable<ApiResponse<List<LinePhoneEntity>>> getLinePhoneList(String dedicatedLinePhoneId) {
        return mHttpHelper.getLinePhoneList(dedicatedLinePhoneId);
    }

    @Override
    public Flowable<ApiResponse<List<HomeListEntity>>> getUserBrandLine(String startCity, int pageNow) {
        return mHttpHelper.getUserBrandLine(startCity, pageNow);
    }

    @Override
    public Flowable<ApiResponse<List<HomeListEntity>>> getUserBrandLine(String startCity, int pageNow, String searchContent) {
        return mHttpHelper.getUserBrandLine(startCity, pageNow, searchContent);
    }

    @Override
    public Flowable<ApiResponse<List<BrandLineEntity>>> getUserBrandLogistics(String startCity, int pageNow) {
        return mHttpHelper.getUserBrandLogistics(startCity, pageNow);
    }

    @Override
    public Flowable<ApiResponse<List<BrandLineEntity>>> getUserBrandLogistics(String startCity, int pageNow, String searchContent) {
        return mHttpHelper.getUserBrandLogistics(startCity, pageNow, searchContent);
    }

    @Override
    public Flowable<ApiResponse<List<AddressListEntity>>> getUserAddressList(String userAccount) {
        return mHttpHelper.getUserAddressList(userAccount);
    }


    @Override
    public Single<ApiResponse<Integer>> getUserCertification(Map<String, RequestBody> files) {
        return mHttpHelper.getUserCertification(files);
    }

    @Override
    public Flowable<ApiResponse<UserInfoEntity>> getUserInfo(String phone) {
        return mHttpHelper.getUserInfo(phone);
    }

    @Override
    public Flowable<ApiDataResponse<List<CarModelEntity>>> getCarModel() {
        return mHttpHelper.getCarModel();
    }

    @Override
    public Flowable<ApiDataResponse<List<CarLengthEntity>>> getCarLength() {
        return mHttpHelper.getCarLength();
    }

    @Override
    public Flowable<ApiResponse<List<FriendEntity>>> getMainFriends(String phone) {
        return mHttpHelper.getMainFriends(phone);
    }

    @Override
    public Flowable<ApiResponse<List<NewFriendEntity>>> getMainFriendRequests(String phone) {
        return mHttpHelper.getMainFriendRequests(phone);
    }

    @Override
    public Flowable<ApiResponse<List<LOrderEntity>>> getOrderInfo(String storeAccount, int status, int pageNow) {
        return mHttpHelper.getOrderInfo(storeAccount, status, pageNow);
    }

    @Override
    public Flowable<ApiResponse<LUserInfoEntity>> getLUserInfo(String phone) {
        return mHttpHelper.getLUserInfo(phone);
    }

    @Override
    public Flowable<ApiResponse<LStoreInfoEntity>> getLStoreInfo(String storeId, int type) {
        return mHttpHelper.getLStoreInfo(storeId, type);
    }

    @Override
    public Flowable<ApiResponse<LOrderEntity>> orderInfoDetail(String orderNo) {
        return mHttpHelper.orderInfoDetail(orderNo);
    }

    @Override
    public Flowable<ApiResponse<PersonalCertification>> userPersonalCertification(String phone) {
        return mHttpHelper.userPersonalCertification(phone);
    }

    @Override
    public Flowable<ApiResponse<LEnterpriseInfo>> enterpriseInfo(String phone) {
        return mHttpHelper.enterpriseInfo(phone);
    }


}
