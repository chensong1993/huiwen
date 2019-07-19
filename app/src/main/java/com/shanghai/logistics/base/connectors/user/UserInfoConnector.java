package com.shanghai.logistics.base.connectors.user;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.user.SpecialEntity;
import com.shanghai.logistics.models.entity.user.UserInfoEntity;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface UserInfoConnector {

    interface View extends BaseView {
        void UserInfo(UserInfoEntity userInfo);

        void UserInfoErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getUserInfo(String phone);
    }
}
