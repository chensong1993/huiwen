package com.shanghai.logistics.presenters.user;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserInfoConnector;
import com.shanghai.logistics.base.connectors.user.UserSpecialConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.user.SpecialEntity;
import com.shanghai.logistics.models.entity.user.UserInfoEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 * <p>
 * 个人中心
 */
public class UserUserInfoPresenter extends RxPresenter<UserInfoConnector.View> implements UserInfoConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserUserInfoPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserInfoConnector.View view) {
        super.attachView(view);

    }

    @Override
    public void getUserInfo(String phone) {
        addSubscribe(dataManager.getUserInfo(phone)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<UserInfoEntity>(mView) {
                    @Override
                    public void onNext(UserInfoEntity user) {
                        mView.UserInfo(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserInfoErr(e.getMessage());
                    }
                }));
    }
}
