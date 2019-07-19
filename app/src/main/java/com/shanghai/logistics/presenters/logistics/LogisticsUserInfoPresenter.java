package com.shanghai.logistics.presenters.logistics;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.logistics.LogisticsOrderInfoConnector;
import com.shanghai.logistics.base.connectors.logistics.LogisticsUserInfoConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.logistics.LUserInfoEntity;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 * <p>
 * 物流个人中心
 */
public class LogisticsUserInfoPresenter extends RxPresenter<LogisticsUserInfoConnector.View> implements LogisticsUserInfoConnector.Presenter {

    DataManager dataManager;

    @Inject
    public LogisticsUserInfoPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(LogisticsUserInfoConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getLogisticsUserInfo(String phone) {
        addSubscribe(dataManager.getLUserInfo(phone)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<LUserInfoEntity>(mView) {
                    @Override
                    public void onNext(LUserInfoEntity entities) {
                        mView.LogisticsUserInfo(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.LogisticsUserInfoErr(e.getMessage());
                    }
                }));
    }
}
