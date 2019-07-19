package com.shanghai.logistics.presenters.logistics;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.logistics.LogisticsOrderDetailConnector;
import com.shanghai.logistics.base.connectors.logistics.LogisticsUserInfoConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 * <p>
 * 物流个人中心
 */
public class LogisticsOrderDetailPresenter extends RxPresenter<LogisticsOrderDetailConnector.View> implements LogisticsOrderDetailConnector.Presenter {

    DataManager dataManager;

    @Inject
    public LogisticsOrderDetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(LogisticsOrderDetailConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getLogisticsOrderDetail(String orderNo) {
        addSubscribe(dataManager.orderInfoDetail(orderNo)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<LOrderEntity>(mView) {
                    @Override
                    public void onNext(LOrderEntity entities) {
                        mView.LogisticsOrderDetail(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.LogisticsOrderDetailErr(e.getMessage());
                    }
                }));
    }
}
