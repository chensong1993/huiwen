package com.shanghai.logistics.presenters.user;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserBrandLineConnector;
import com.shanghai.logistics.base.connectors.user.UserSpecialConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.user.BrandLineEntity;
import com.shanghai.logistics.models.entity.user.HomeListEntity;
import com.shanghai.logistics.models.entity.user.SpecialEntity;
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
public class UserBrandLinePresenter extends RxPresenter<UserBrandLineConnector.View> implements UserBrandLineConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserBrandLinePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserBrandLineConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getBrandLine(String startCity, int pageNow) {
        addSubscribe(dataManager.getUserBrandLine(startCity, pageNow)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<HomeListEntity>>(mView) {
                    @Override
                    public void onNext(List<HomeListEntity> entities) {
                        mView.UserBrandLine(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserBrandLineErr(e.getMessage());
                    }
                }));
    }

    @Override
    public void getBrandLine(String startCity, int pageNow, String searchContent) {
        addSubscribe(dataManager.getUserBrandLine(startCity, pageNow, searchContent)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<HomeListEntity>>(mView) {
                    @Override
                    public void onNext(List<HomeListEntity> entities) {
                        mView.UserBrandLine(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserBrandLineErr(e.getMessage());
                    }
                }));
    }
}
