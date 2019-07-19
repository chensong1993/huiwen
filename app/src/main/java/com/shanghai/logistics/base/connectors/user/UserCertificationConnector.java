package com.shanghai.logistics.base.connectors.user;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.user.SpecialEntity;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface UserCertificationConnector {

    interface View extends BaseView {
        void UserCertification(String msg);
        void UserCertificationErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
          void getUserCertification(Map<String, RequestBody> files);

    }
}
