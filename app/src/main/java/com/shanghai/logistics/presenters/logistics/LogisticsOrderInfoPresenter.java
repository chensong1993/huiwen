package com.shanghai.logistics.presenters.logistics;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.logistics.LogisticsOrderInfoConnector;
import com.shanghai.logistics.base.connectors.main.MainFriendConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.logistics.LOrderEntity;
import com.shanghai.logistics.models.entity.main.FriendEntity;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 * <p>
 * 新订单和待开单列表
 */
public class LogisticsOrderInfoPresenter extends RxPresenter<LogisticsOrderInfoConnector.View> implements LogisticsOrderInfoConnector.Presenter {

    DataManager dataManager;

    @Inject
    public LogisticsOrderInfoPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(LogisticsOrderInfoConnector.View view) {
        super.attachView(view);

    }


    /*
    * pageNow=1 新订单列表
    * pageNow=3 待开单列表
    * */

    @Override
    public void getLogisticsOrderInfo(String storeAccount, int status, int pageNow) {
        addSubscribe(dataManager.getOrderInfo(storeAccount, status, pageNow)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<LOrderEntity>>(mView) {
                    @Override
                    public void onNext(List<LOrderEntity> entities) {
                        mView.LogisticsOrderInfo(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.LogisticsOrderInfoErr(e.getMessage());
                    }
                }));
    }
}
