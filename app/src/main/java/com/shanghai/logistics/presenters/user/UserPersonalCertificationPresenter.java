package com.shanghai.logistics.presenters.user;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserInfoConnector;
import com.shanghai.logistics.base.connectors.user.UserPersonalCertificationConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.user.PersonalCertification;
import com.shanghai.logistics.models.entity.user.UserInfoEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author chensong
 * <p>
 * 查询认证信息
 */
public class UserPersonalCertificationPresenter extends RxPresenter<UserPersonalCertificationConnector.View> implements UserPersonalCertificationConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserPersonalCertificationPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserPersonalCertificationConnector.View view) {
        super.attachView(view);

    }

    @Override
    public void getUserInfo(String phone) {
        addSubscribe(dataManager.userPersonalCertification(phone)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<PersonalCertification>(mView) {
                    @Override
                    public void onNext(PersonalCertification user) {
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
