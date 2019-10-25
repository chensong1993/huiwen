package com.shanghai.logistics.presenters.login;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.login.ChangePwdConnector;
import com.shanghai.logistics.base.connectors.login.LoginConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class ChangePwdPresenter extends RxPresenter<ChangePwdConnector.View> implements ChangePwdConnector.Presenter {

    DataManager mDataManager;

    @Inject
    public ChangePwdPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(ChangePwdConnector.View view) {
        super.attachView(view);

    }

    @Override
    public void getSendCode(String phone, int type) {
        addSubscribe(mDataManager.getSendCode(phone, type)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(new Consumer<ApiResponse<String>>() {
                    @Override
                    public void accept(ApiResponse<String> ApiResponse) {
                        mView.sendCodeData(ApiResponse.getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.sendCodeErr(throwable.getMessage());
                    }
                }));
    }


    @Override
    public void getChangePwd(String phone, String password, String code) {
        addSubscribe(mDataManager.getForgetPassword(phone, password, code)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(new Consumer<ApiResponse<String>>() {
                    @Override
                    public void accept(ApiResponse<String> apiResponse) {
                        mView.changePwd(apiResponse.getMsg());
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mView.changePwdErr(e.getMessage());
                    }
                }));
    }
}
