package com.shanghai.logistics.base.connectors.user;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.user.BrandLineEntity;
import com.shanghai.logistics.models.entity.user.HomeListEntity;
import com.shanghai.logistics.models.entity.user.SpecialEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface UserBrandLineConnector {

    interface View extends BaseView {
        void UserBrandLine(List<HomeListEntity> entities);

        void UserBrandLineErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getBrandLine(String startCity, int pageNow);
        void getBrandLine(String startCity, int pageNow, String searchContent);
    }
}
