package com.shanghai.logistics.base.connectors.user;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.user.AddressListEntity;
import com.shanghai.logistics.models.entity.user.SpecialEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface UserAddressConnector {

    interface View extends BaseView {
        void UserAddress(List<AddressListEntity> entities);
        void UserAddressErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getUserAddress(String userAccount);
    }
}
