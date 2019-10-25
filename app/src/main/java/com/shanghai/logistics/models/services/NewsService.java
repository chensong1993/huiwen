package com.shanghai.logistics.models.services;

import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.LoginEntity;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NewsService {

    //登陆
    @POST("userInfo/login.do")
    @FormUrlEncoded
    Flowable<ApiResponse<LoginEntity>> login(@Field("phone") String phone, @Field("password") String password);

    //注册
    @POST("userInfo/register.do")
    @FormUrlEncoded
    Flowable<ApiResponse<String>> register(@Field("phone") String phone, @Field("password") String password, @Field("code") String code, @Field("nickName") String nickName);

    //获取验证码
    @POST("SMSCode/sendCode.do")
    @FormUrlEncoded
    Flowable<ApiResponse<String>> sendCode(@Field("phone") String phone, @Field("type") int type);

    //忘记密码
    @POST("userInfo/forgetPassword.do")
    @FormUrlEncoded
    Flowable<ApiResponse<String>> forgetPassword(@Field("phone") String phone, @Field("password") String password, @Field("code") String code);



}