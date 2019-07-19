package com.shanghai.logistics.presenters.main;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.main.MainFriendConnector;
import com.shanghai.logistics.base.connectors.main.MainNewFriendConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.main.FriendEntity;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;
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
public class MainNewFriendPresenter extends RxPresenter<MainNewFriendConnector.View> implements MainNewFriendConnector.Presenter {

    DataManager dataManager;

    @Inject
    public MainNewFriendPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainNewFriendConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getMainNewFriend(String phone) {
        addSubscribe(dataManager.getMainFriendRequests(phone)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<NewFriendEntity>>(mView) {
                    @Override
                    public void onNext(List<NewFriendEntity> entities) {
                        mView.MainNewFriend(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.MainNewFriendErr(e.getMessage());
                    }
                }));
    }
}
