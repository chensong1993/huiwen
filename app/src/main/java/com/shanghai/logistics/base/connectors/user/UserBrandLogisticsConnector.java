package com.shanghai.logistics.base.connectors.user;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.user.BrandLineEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface UserBrandLogisticsConnector {

    interface View extends BaseView {
        void UserBrandLogistics(List<BrandLineEntity> entities);

        void UserBrandLogisticsErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getBrandLogistics(String startCity, int pageNow);
        void getBrandLogistics(String startCity, int pageNow, String searchContent);
    }
}
