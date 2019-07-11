package com.shanghai.logistics.models;

import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.models.http.HttpHelper;


import io.reactivex.Flowable;


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
    public Flowable<ApiResponse<LoginEntity>> getRegister(String phone, String password, String code, String nickName) {
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


}
