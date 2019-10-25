package com.shanghai.logistics.presenters.login;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.login.RegisterConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

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
                .subscribe(new Consumer<ApiResponse<String>>() {
                               @Override
                               public void accept(ApiResponse<String> response) {
                                   mView.sendCode(response.getMsg());
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.sendCodeErr(throwable.getMessage());
                               }
                           }
                ));
    }

    @Override
    public void getRegisterData(String phone, String password, String code, String nickName) {
        addSubscribe(mDataManager.getRegister(phone, password, code, nickName)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(new Consumer<ApiResponse<String>>() {
                    @Override
                    public void accept(ApiResponse<String> Response) {
                        mView.registerMsg(Response.getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.registerErr(throwable.getMessage());
                    }
                })

        );
    }
}
