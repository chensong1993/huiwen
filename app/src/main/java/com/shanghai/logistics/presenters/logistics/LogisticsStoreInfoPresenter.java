package com.shanghai.logistics.presenters.logistics;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.logistics.LogisticsStoreInfoConnector;
import com.shanghai.logistics.base.connectors.logistics.LogisticsUserInfoConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.logistics.LStoreInfoEntity;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 * <p>
 * 商店详情
 */
public class LogisticsStoreInfoPresenter extends RxPresenter<LogisticsStoreInfoConnector.View> implements LogisticsStoreInfoConnector.Presenter {

    DataManager dataManager;

    @Inject
    public LogisticsStoreInfoPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(LogisticsStoreInfoConnector.View view) {
        super.attachView(view);

    }



    @Override
    public void getLogisticsStoreInfo(String storeId, int type) {
        addSubscribe(dataManager.getLStoreInfo(storeId,type)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<LStoreInfoEntity>(mView) {
                    @Override
                    public void onNext(LStoreInfoEntity entities) {
                        mView.LogisticsStoreInfo(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.LogisticsStoreInfoErr(e.getMessage());
                    }
                }));
    }
}
