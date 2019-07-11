package com.shanghai.logistics.presenters.login;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.login.ChangePwdConnector;
import com.shanghai.logistics.base.connectors.login.LoginConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import javax.inject.Inject;

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
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.sendCodeData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.sendCodeErr(e.getMessage());
                    }
                }));
    }


    @Override
    public void getChangePwd(String phone, String password, String code) {
        addSubscribe(mDataManager.getForgetPassword(phone, password, code)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.changePwd();
                        // RxBus.getDefault().post(new LoginEvent(true, entities.getUsername()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.changePwdErr(e.getMessage());
                        //  RxBus.getDefault().post(new LoginEvent(false, null));
                    }
                }));
    }
}
