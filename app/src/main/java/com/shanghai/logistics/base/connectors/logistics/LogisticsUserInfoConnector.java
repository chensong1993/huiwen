package com.shanghai.logistics.base.connectors.logistics;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface LogisticsUserInfoConnector {

    interface View extends BaseView {
        void LogisticsUserInfo(LUserInfoEntity entities);

        void LogisticsUserInfoErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getLogisticsUserInfo(String phone);
    }
}
