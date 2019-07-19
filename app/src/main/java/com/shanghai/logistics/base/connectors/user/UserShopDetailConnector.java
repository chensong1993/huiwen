package com.shanghai.logistics.base.connectors.user;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.user.HomeListEntity;
import com.shanghai.logistics.models.entity.user.UserShopEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface UserShopDetailConnector {

    interface View extends BaseView {
        void UserShopDetail(UserShopEntity entities);
        void UserShopDetailErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getUserShopDetail(String storeId,int type);
    }
}
