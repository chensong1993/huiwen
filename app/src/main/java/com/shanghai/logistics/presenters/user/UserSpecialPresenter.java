package com.shanghai.logistics.presenters.user;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserShopDetailConnector;
import com.shanghai.logistics.base.connectors.user.UserSpecialConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.logistics.LStoreInfoEntity;
import com.shanghai.logistics.models.entity.user.SpecialEntity;
import com.shanghai.logistics.models.entity.user.UserShopEntity;
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
public class UserSpecialPresenter extends RxPresenter<UserSpecialConnector.View> implements UserSpecialConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserSpecialPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserSpecialConnector.View view) {
        super.attachView(view);

    }

    @Override
    public void getUserSpecial(int dedicatedLineId) {
        addSubscribe(dataManager.getUserSpecial(dedicatedLineId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<SpecialEntity>(mView) {
                    @Override
                    public void onNext(SpecialEntity entities) {
                        mView.UserSpecial(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserSpecialErr(e.getMessage());
                    }
                }));
    }

    @Override
    public void getLogisticsSpecial(String storeId, int type) {
        addSubscribe(dataManager.getLStoreInfo(storeId,type)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<LStoreInfoEntity>(mView) {
                    @Override
                    public void onNext(LStoreInfoEntity entities) {
                        mView.LogisticsStoreInfo(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.LogisticsStoreInfoErr(e.getMessage());
                    }
                }));
    }
}
