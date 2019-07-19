package com.shanghai.logistics.base.connectors.user;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.user.CarModelEntity;
import com.shanghai.logistics.models.entity.user.SpecialEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface UserCarModelConnector {

    interface View extends BaseView {
        void UserCarModel(List<CarModelEntity> e);
        void UserCarModelErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getUserCarModel();
    }
}
