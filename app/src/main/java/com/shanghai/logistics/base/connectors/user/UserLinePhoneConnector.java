package com.shanghai.logistics.base.connectors.user;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.user.LinePhoneEntity;
import com.shanghai.logistics.models.entity.user.SpecialEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface UserLinePhoneConnector {

    interface View extends BaseView {
        void UserLinePhoneList(List<LinePhoneEntity> entities);
        void UserLinePhoneLisErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getLinePhoneList(String id);
    }
}
