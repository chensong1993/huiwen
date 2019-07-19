package com.shanghai.logistics.ui.activity.decode;

import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.models.services.ApiService;
import com.shanghai.logistics.models.services.UserService;
import com.shanghai.logistics.widget.CommonSubscriber;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserInfoActivity extends SimpleActivity {

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initEventAndData() {
        // mPresenter.getUserInfo();
//        ApiService.getInstance()
//                .create(UserService.class, "")
//                .userCertification()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new CommonSubscriber<Integer>() {
//                    @Override
//                    public void onNext(Integer integer) {
//
//                    }
//
//                });
    }




}
