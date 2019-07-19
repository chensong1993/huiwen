package com.shanghai.logistics.presenters.user;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserBrandLineConnector;
import com.shanghai.logistics.base.connectors.user.UserBrandLogisticsConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.user.BrandLineEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 * <p>
 * 品牌物流
 */
public class UserBrandLogisticsPresenter extends RxPresenter<UserBrandLogisticsConnector.View> implements UserBrandLogisticsConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserBrandLogisticsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserBrandLogisticsConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getBrandLogistics(String startCity, int pageNow) {
        addSubscribe(dataManager.getUserBrandLogistics(startCity, pageNow)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<BrandLineEntity>>(mView) {
                    @Override
                    public void onNext(List<BrandLineEntity> entities) {
                        mView.UserBrandLogistics(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserBrandLogisticsErr(e.getMessage());
                    }
                }));
    }

    @Override
    public void getBrandLogistics(String startCity, int pageNow, String searchContent) {
        addSubscribe(dataManager.getUserBrandLogistics(startCity, pageNow, searchContent)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<BrandLineEntity>>(mView) {
                    @Override
                    public void onNext(List<BrandLineEntity> entities) {
                        mView.UserBrandLogistics(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserBrandLogisticsErr(e.getMessage());
                    }
                }));
    }
}
