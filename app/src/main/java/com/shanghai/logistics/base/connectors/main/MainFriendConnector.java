package com.shanghai.logistics.base.connectors.main;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.main.FriendEntity;
import com.shanghai.logistics.models.entity.user.AddressListEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface MainFriendConnector {

    interface View extends BaseView {
        void MainFriend(List<FriendEntity> entities);
        void MainFriendErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getMainFriend(String phone);
    }
}
