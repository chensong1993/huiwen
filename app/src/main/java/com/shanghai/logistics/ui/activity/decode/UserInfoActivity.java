package com.shanghai.logistics.ui.activity.decode;

import com.shanghai.logistics.base.SimpleActivity;

public class UserInfoActivity extends SimpleActivity {

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initEventAndData() {
        // mPresenter.getUserInfo();
    }


//        ApiService.getInstance()
//                .create(, mEtUrl.getText().toString())
//                .userInfo()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new CommonSubscriber<ApiResponse<>>() {
//                    @Override
//                    public void onNext(ApiResponse<> user) {
//                    }
//                });

}
