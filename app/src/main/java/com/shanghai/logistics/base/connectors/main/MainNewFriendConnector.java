package com.shanghai.logistics.base.connectors.main;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.main.FriendEntity;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface MainNewFriendConnector {

    interface View extends BaseView {
        void MainNewFriend(List<NewFriendEntity> entities);
        void MainNewFriendErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getMainNewFriend(String phone);
    }
}
