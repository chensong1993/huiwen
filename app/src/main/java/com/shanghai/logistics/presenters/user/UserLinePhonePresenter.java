package com.shanghai.logistics.presenters.user;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserLinePhoneConnector;
import com.shanghai.logistics.base.connectors.user.UserSpecialConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.user.LinePhoneEntity;
import com.shanghai.logistics.models.entity.user.SpecialEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 * <p>
 * 专线
 */
public class UserLinePhonePresenter extends RxPresenter<UserLinePhoneConnector.View> implements UserLinePhoneConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserLinePhonePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserLinePhoneConnector.View view) {
        super.attachView(view);

    }

    @Override
    public void getLinePhoneList(String id) {
        addSubscribe(dataManager.getLinePhoneList(id)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<LinePhoneEntity>>(mView) {
                    @Override
                    public void onNext(List<LinePhoneEntity> entities) {
                        mView.UserLinePhoneList(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserLinePhoneLisErr(e.getMessage());
                    }
                }));
    }
}
