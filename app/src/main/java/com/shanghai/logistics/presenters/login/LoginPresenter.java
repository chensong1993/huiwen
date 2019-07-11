package com.shanghai.logistics.presenters.login;

import android.util.Log;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.components.RxBus;
import com.shanghai.logistics.base.connectors.login.LoginConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.models.entity.rxbus.LoginEvent;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 */
public class LoginPresenter extends RxPresenter<LoginConnector.View> implements LoginConnector.Presenter {

    DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(LoginConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getShowLogin(String name, String password) {
        addSubscribe(dataManager.getLogin(name, password)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<LoginEntity>(mView) {
                    @Override
                    public void onNext(LoginEntity entities) {
                        mView.showLogin(entities);
                       // RxBus.getDefault().post(new LoginEvent(true, entities.getUsername()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateError();
                      //  RxBus.getDefault().post(new LoginEvent(false, null));
                    }
                }));
    }
}
