package com.shanghai.logistics.models.http;

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
import com.shanghai.logistics.models.services.LogisticsService;
import com.shanghai.logistics.models.services.NewsService;
import com.shanghai.logistics.models.services.UserService;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.internal.operators.single.SingleToFlowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RetrofitHelper implements HttpHelper {

    private NewsService mNewsApiService;
    private UserService mUserService;
    private LogisticsService mLogisticsService;

    @Inject
    public RetrofitHelper(NewsService mNewsApiService, UserService mUserService, LogisticsService mLogisticsService) {
        this.mNewsApiService = mNewsApiService;
        this.mUserService = mUserService;
        this.mLogisticsService = mLogisticsService;
    }


    @Override
    public Flowable<ApiResponse<LoginEntity>> getLogin(String phone, String password) {
        return mNewsApiService.login(phone, password);
    }

    @Override
    public Flowable<ApiResponse<String>> getRegister(String phone, String password, String code, String nickName) {
        return mNewsApiService.register(phone, password, code, nickName);
    }

    @Override
    public Flowable<ApiResponse<String>> getSendCode(String phone, int type) {
        return mNewsApiService.sendCode(phone, type);
    }

    @Override
    public Flowable<ApiResponse<String>> getForgetPassword(String phone, String password, String code) {
        return mNewsApiService.forgetPassword(phone, password, code);
    }

    @Override
    public Flowable<ApiResponse<List<HomeListEntity>>> getUserHomeList(String startCity) {
        return mUserService.userHomeList(startCity);
    }

    @Override
    public Flowable<ApiResponse<UserShopEntity>> getUserShopDetail(String storeId, int type) {
        return mUserService.userShopDetail(storeId, type);
    }

    @Override
    public Flowable<ApiResponse<SpecialEntity>> getUserSpecial(int dedicatedLineId) {
        return mUserService.userSpecial(dedicatedLineId);
    }

    @Override
    public Flowable<ApiResponse<List<CommentEntity>>> getCommentList(int dedicatedLineId, int pageNow) {
        return mUserService.userCommentList(dedicatedLineId, pageNow);
    }

    @Override
    public Flowable<ApiResponse<List<LinePhoneEntity>>> getLinePhoneList(String dedicatedLinePhoneId) {
        return mUserService.userLinePhoneList(dedicatedLinePhoneId);
    }

    @Override
    public Flowable<ApiResponse<List<HomeListEntity>>> getUserBrandLine(String startCity, int pageNow) {
        return mUserService.userBrandLine(startCity, pageNow);
    }

    @Override
    public Flowable<ApiResponse<List<HomeListEntity>>> getUserBrandLine(String startCity, int pageNow, String searchContent) {
        return mUserService.userBrandLine(startCity, pageNow, searchContent);
    }

    @Override
    public Flowable<ApiResponse<List<BrandLineEntity>>> getUserBrandLogistics(String startCity, int pageNow) {
        return mUserService.userBrandLogistics(startCity, pageNow);
    }

    @Override
    public Flowable<ApiResponse<List<BrandLineEntity>>> getUserBrandLogistics(String startCity, int pageNow, String searchContent) {
        return mUserService.userBrandLogistics(startCity, pageNow, searchContent);
    }

    @Override
    public Flowable<ApiResponse<List<AddressListEntity>>> getUserAddressList(String userAccount) {
        return mUserService.userAddressLis(userAccount);
    }


    @Override
    public Single<ApiResponse<Integer>> getUserCertification(Map<String, RequestBody> files) {
        return mUserService.userCertification(files);
    }

    @Override
    public Flowable<ApiResponse<UserInfoEntity>> getUserInfo(String phone) {
        return mUserService.userInfo(phone);
    }

    @Override
    public Flowable<ApiDataResponse<List<CarModelEntity>>> getCarModel() {
        return mUserService.carModel();
    }

    @Override
    public Flowable<ApiDataResponse<List<CarLengthEntity>>> getCarLength() {
        return mUserService.carLength();
    }


    @Override
    public Flowable<ApiResponse<List<FriendEntity>>> getMainFriends(String phone) {
        return mUserService.mainFriends(phone);
    }

    @Override
    public Flowable<ApiResponse<List<NewFriendEntity>>> getMainFriendRequests(String phone) {
        return mUserService.mainFriendRequests(phone);
    }

    @Override
    public Flowable<ApiResponse<List<LOrderEntity>>> getOrderInfo(String storeAccount, int status, int pageNow) {
        return mLogisticsService.orderInfo(storeAccount,status,pageNow);
    }

    @Override
    public Flowable<ApiResponse<LUserInfoEntity>> getLUserInfo(String phone) {
        return mLogisticsService.lUserInfo(phone);
    }

    @Override
    public Flowable<ApiResponse<LStoreInfoEntity>> getLStoreInfo(String storeId, int type) {
        return mUserService.lStoreInfo(storeId,type);
    }

    @Override
    public Flowable<ApiResponse<LOrderEntity>> orderInfoDetail(String orderNo) {
        return mLogisticsService.orderInfoDetail(orderNo);
    }

    @Override
    public Flowable<ApiResponse<PersonalCertification>> userPersonalCertification(String phone) {
        return mUserService.userPersonalCertification(phone);
    }

    @Override
    public Flowable<ApiResponse<LEnterpriseInfo>> enterpriseInfo(String phone) {
        return mLogisticsService.enterpriseInfo(phone);
    }


}
