package com.shanghai.logistics.base.connectors.login;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.LoginEntity;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface LoginConnector {

    interface View extends BaseView {
        void showLogin(LoginEntity entities);
    }

    interface Presenter extends BasePresenter<View> {
        void getShowLogin(String name, String password);
    }
}
