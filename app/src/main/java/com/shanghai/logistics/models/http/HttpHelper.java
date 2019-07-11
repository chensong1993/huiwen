package com.shanghai.logistics.models.http;

import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.LoginEntity;


import io.reactivex.Flowable;

public interface HttpHelper {

    Flowable<ApiResponse<LoginEntity>> getLogin(String phone, String password);

    Flowable<ApiResponse<LoginEntity>> getRegister(String phone, String password, String code, String nickName);

    Flowable<ApiResponse<String>> getSendCode(String phone, int type);

    Flowable<ApiResponse<String>> getForgetPassword(String phone, String password,String code);
}
