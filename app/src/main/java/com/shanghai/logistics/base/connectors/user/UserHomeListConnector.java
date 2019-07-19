package com.shanghai.logistics.base.connectors.user;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.models.entity.user.HomeListEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface UserHomeListConnector {

    interface View extends BaseView {
        void homeList(List<HomeListEntity> entities);
        void homeListErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getHomeList(String startCity);
    }
}
