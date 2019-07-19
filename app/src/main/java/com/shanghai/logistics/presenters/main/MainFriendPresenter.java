package com.shanghai.logistics.presenters.main;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.main.MainFriendConnector;
import com.shanghai.logistics.base.connectors.user.UserAddressConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.main.FriendEntity;
import com.shanghai.logistics.models.entity.user.AddressListEntity;
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
public class MainFriendPresenter extends RxPresenter<MainFriendConnector.View> implements MainFriendConnector.Presenter {

    DataManager dataManager;

    @Inject
    public MainFriendPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainFriendConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getMainFriend(String phone) {
        addSubscribe(dataManager.getMainFriends(phone)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<FriendEntity>>(mView) {
                    @Override
                    public void onNext(List<FriendEntity> entities) {
                        mView.MainFriend(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.MainFriendErr(e.getMessage());
                    }
                }));
    }
}
