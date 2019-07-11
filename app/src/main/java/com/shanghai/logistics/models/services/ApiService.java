package com.shanghai.logistics.models.services;


import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/9/7.
 */

public class ApiService {
    //长缓存有效期为1天
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 2;
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=360";

    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";
    private static ApiService mApiService;
    private static OkHttpClient mOkHttpClient;

    public ApiService() {
        initSetting();
    }


   /* private static class SingFactory {
        private static ApiService instance = new ApiService();
    }


    public static ApiService getInstance() {
        return SingFactory.instance;
    }*/

    public static ApiService getInstance() {
        if (mApiService == null) {
            synchronized (ApiService.class) {
                if (mApiService == null) {
                    mApiService = new ApiService();
                }
            }
        }
        return mApiService;
    }

    /**
     * 创建相应的服务接口
     */
    public <T> T create(Class<T> service, String baseUrl) {
        checkNotNull(service, "service is null");
        checkNotNull(baseUrl, "baseUrl is null");

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                //工厂转换器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(service);
    }

    private <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }

    private void initSetting() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            if (mOkHttpClient == null) {

                // 指定缓存路径,缓存大小100Mb
                Cache cache = new Cache(new File(Constants.PATH_CACHE),
                        1024 * 1024 * 100);

                mOkHttpClient = new OkHttpClient.Builder()
                        .cache(cache)
                        .addInterceptor(mRewriteCacheControlInterceptor)
                        .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                        .addInterceptor(interceptor)
                        .retryOnConnectionFailure(true)
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .build();
            }
        }
    }

    // 云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!SystemUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (SystemUtil.isNetworkConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public," + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma").build();
            }
        }
    };
}
