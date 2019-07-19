package com.shanghai.logistics.base.connectors.logistics;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.models.entity.main.FriendEntity;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface LogisticsOrderInfoConnector {

    interface View extends BaseView {
        void LogisticsOrderInfo(List<LOrderEntity> entities);
        void LogisticsOrderInfoErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getLogisticsOrderInfo(String storeAccount, int status, int pageNow);
    }
}
