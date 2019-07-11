package com.shanghai.logistics.presenters.login;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.login.RegisterConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/18 17:35
 */
public class RegisterPresenter extends RxPresenter<RegisterConnector.View> implements RegisterConnector.Presenter {
    DataManager mDataManager;

    @Inject
    public RegisterPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(RegisterConnector.View view) {
        super.attachView(view);
    }

    @Override
    public void getSendCode(String phone, int type) {
        addSubscribe(mDataManager.getSendCode(phone, type)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.sendCodeData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.sendCodeDataErr(e.getMessage());
                    }
                }));
    }

    @Override
    public void getRegisterData(String phone, String password, String code, String nickName) {
        addSubscribe(mDataManager.getRegister(phone, password, code, nickName)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<LoginEntity>(mView) {
                    @Override
                    public void onNext(LoginEntity registerEntity) {
                        mView.registerUserData(registerEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.registerErr(e.getMessage());
                    }
                }));
    }
}
