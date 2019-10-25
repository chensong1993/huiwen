package com.shanghai.logistics.presenters.logistics;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.logistics.LogisticsCertificationConnector;
import com.shanghai.logistics.base.connectors.logistics.LogisticsUserInfoConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.logistics.LEnterpriseInfo;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 * <p>
 * 查询认证信息
 */
public class LogisticsCertificationPresenter extends RxPresenter<LogisticsCertificationConnector.View> implements LogisticsCertificationConnector.Presenter {

    DataManager dataManager;

    @Inject
    public LogisticsCertificationPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(LogisticsCertificationConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getCertificationInfo(String phone) {
        addSubscribe(dataManager.enterpriseInfo(phone)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<LEnterpriseInfo>(mView) {
                    @Override
                    public void onNext(LEnterpriseInfo entities) {
                        mView.CertificationInfo(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.CertificationInfoErr(e.getMessage());
                    }
                }));
    }
}
