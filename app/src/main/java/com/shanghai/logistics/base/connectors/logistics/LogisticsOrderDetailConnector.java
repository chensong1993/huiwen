package com.shanghai.logistics.base.connectors.logistics;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface LogisticsOrderDetailConnector {

    interface View extends BaseView {
        void LogisticsOrderDetail(LOrderEntity entities);

        void LogisticsOrderDetailErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getLogisticsOrderDetail(String orderNo);
    }
}
