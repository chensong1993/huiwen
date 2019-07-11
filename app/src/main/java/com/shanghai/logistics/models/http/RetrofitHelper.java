package com.shanghai.logistics.models.http;

import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.models.services.NewsService;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RetrofitHelper implements HttpHelper {

    private NewsService mNewsApiService;

    @Inject
    public RetrofitHelper(NewsService mNewsApiService) {
        this.mNewsApiService = mNewsApiService;
    }


    @Override
    public Flowable<ApiResponse<LoginEntity>> getLogin(String phone, String password) {
        return mNewsApiService.login(phone, password);
    }

    @Override
    public Flowable<ApiResponse<LoginEntity>> getRegister(String phone, String password, String code, String nickName) {
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


}
