package com.shanghai.logistics.base.connectors.login;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.LoginEntity;

public interface ChangePwdConnector {
    interface View extends BaseView {
        void changePwd(int msg);

        void sendCodeData(int msg);

        void sendCodeErr(String msg);

        void changePwdErr(String msg);
    }

    interface Presenter extends BasePresenter<ChangePwdConnector.View> {
        void getSendCode(String phone,int type);
        void getChangePwd(String phone, String password, String code);
    }

}
