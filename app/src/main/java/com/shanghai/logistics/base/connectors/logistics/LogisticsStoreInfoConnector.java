package com.shanghai.logistics.base.connectors.logistics;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.logistics.LStoreInfoEntity;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface LogisticsStoreInfoConnector {

    interface View extends BaseView {
        void LogisticsStoreInfo(LStoreInfoEntity entities);

        void LogisticsStoreInfoErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getLogisticsStoreInfo(String storeId, int type);
    }
}
