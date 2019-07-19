package com.shanghai.logistics.base.connectors.user;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.user.SpecialEntity;
import com.shanghai.logistics.models.entity.user.UserShopEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface UserSpecialConnector {

    interface View extends BaseView {
        void UserSpecial(SpecialEntity entities);
        void UserSpecialErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getUserSpecial(int dedicatedLineId);
    }
}
