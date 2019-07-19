package com.shanghai.logistics.models.http;

import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.models.entity.logistics.LStoreInfoEntity;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;
import com.shanghai.logistics.models.entity.main.FriendEntity;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;
import com.shanghai.logistics.models.entity.user.AddressListEntity;
import com.shanghai.logistics.models.entity.user.BrandLineEntity;
import com.shanghai.logistics.models.entity.user.CarModelEntity;
import com.shanghai.logistics.models.entity.user.CommentEntity;
import com.shanghai.logistics.models.entity.user.HomeListEntity;
import com.shanghai.logistics.models.entity.user.LinePhoneEntity;
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
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface HttpHelper {

    Flowable<ApiResponse<LoginEntity>> getLogin(String phone, String password);

    Flowable<ApiResponse<LoginEntity>> getRegister(String phone, String password, String code, String nickName);

    Flowable<ApiResponse<String>> getSendCode(String phone, int type);

    Flowable<ApiResponse<String>> getForgetPassword(String phone, String password, String code);

    Flowable<ApiResponse<List<HomeListEntity>>> getUserHomeList(String startCity);

    Flowable<ApiResponse<UserShopEntity>> getUserShopDetail(String storeId, int type);

    Flowable<ApiResponse<SpecialEntity>> getUserSpecial(int dedicatedLineId);

    Flowable<ApiResponse<List<CommentEntity>>> getCommentList(int dedicatedLineId, int pageNow);

    Flowable<ApiResponse<List<LinePhoneEntity>>> getLinePhoneList(String dedicatedLinePhoneId);

    Flowable<ApiResponse<List<HomeListEntity>>> getUserBrandLine(String startCity, int pageNow);

    Flowable<ApiResponse<List<HomeListEntity>>> getUserBrandLine(String startCity, int pageNow, String searchContent);

    Flowable<ApiResponse<List<BrandLineEntity>>> getUserBrandLogistics(String startCity, int pageNow);

    Flowable<ApiResponse<List<BrandLineEntity>>> getUserBrandLogistics(String startCity, int pageNow, String searchContent);

    Flowable<ApiResponse<List<AddressListEntity>>> getUserAddressList(String userAccount);

    Single<ApiResponse<Integer>> getUserCertification(Map<String, RequestBody> files);

    Flowable<ApiResponse<UserInfoEntity>> getUserInfo(String phone);

    Flowable<ApiResponse<List<CarModelEntity>>> getCarModel();

  //  Flowable<ApiResponse<Integer>> getSendOrder(Map<String, RequestBody> sendOrder);

    Flowable<ApiResponse<List<FriendEntity>>> getMainFriends( String phone);

    Flowable<ApiResponse<List<NewFriendEntity>>> getMainFriendRequests( String phone);

    Flowable<ApiResponse<List<LOrderEntity>>> getOrderInfo(String storeAccount, int status, int pageNow);

    Flowable<ApiResponse<LUserInfoEntity>> getLUserInfo(String phone);

    Flowable<ApiResponse<LStoreInfoEntity>> getLStoreInfo(String storeId,int type);

    Flowable<ApiResponse<LOrderEntity>> orderInfoDetail(String orderNo);

}
