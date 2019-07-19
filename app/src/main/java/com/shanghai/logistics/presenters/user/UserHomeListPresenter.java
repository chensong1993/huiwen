package com.shanghai.logistics.presenters.user;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserHomeListConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.user.HomeListEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 */
public class UserHomeListPresenter extends RxPresenter<UserHomeListConnector.View> implements UserHomeListConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserHomeListPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserHomeListConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getHomeList(String startCity) {
        addSubscribe(dataManager.getUserHomeList(startCity)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<HomeListEntity>>(mView) {
                    @Override
                    public void onNext(List<HomeListEntity> entities) {
                        mView.homeList(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.homeListErr(e.getMessage());
                    }
                }));
    }
}
