package com.shanghai.logistics.base.connectors.logistics;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.logistics.LEnterpriseInfo;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface LogisticsCertificationConnector {

    interface View extends BaseView {
        void CertificationInfo(LEnterpriseInfo entities);

        void CertificationInfoErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getCertificationInfo(String phone);
    }
}
