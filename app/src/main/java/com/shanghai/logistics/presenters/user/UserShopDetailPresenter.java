package com.shanghai.logistics.presenters.user;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserHomeListConnector;
import com.shanghai.logistics.base.connectors.user.UserShopDetailConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.user.HomeListEntity;
import com.shanghai.logistics.models.entity.user.UserShopEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 */
public class UserShopDetailPresenter extends RxPresenter<UserShopDetailConnector.View> implements UserShopDetailConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserShopDetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserShopDetailConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getUserShopDetail(String storeId, int type) {
        addSubscribe(dataManager.getUserShopDetail(storeId,type)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<UserShopEntity>(mView) {
                    @Override
                    public void onNext(UserShopEntity entities) {
                        mView.UserShopDetail(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserShopDetailErr(e.getMessage());
                    }
                }));
    }
}
