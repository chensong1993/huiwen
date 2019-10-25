package com.shanghai.logistics.base.connectors.login;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.LoginEntity;

/**
 * @author chensong
 * @date 2019/4/18 17:37
 */
public interface RegisterConnector {
    interface View extends BaseView {
        /**
         * 注册信息
         */
        void registerMsg(int msg);

        void registerErr(String s);

        void sendCode(int msg);

        void sendCodeErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getSendCode(String phone, int type);

        void getRegisterData(String phone, String password, String code, String nickName);
    }
}
