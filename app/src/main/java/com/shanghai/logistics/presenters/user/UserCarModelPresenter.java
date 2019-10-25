package com.shanghai.logistics.presenters.user;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserCarModelConnector;
import com.shanghai.logistics.base.connectors.user.UserInfoConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.user.CarLengthEntity;
import com.shanghai.logistics.models.entity.user.CarModelEntity;
import com.shanghai.logistics.models.entity.user.UserInfoEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 * <p>
 * 车型 车长
 */
public class UserCarModelPresenter extends RxPresenter<UserCarModelConnector.View> implements UserCarModelConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserCarModelPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserCarModelConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getUserCarModel() {
        addSubscribe(dataManager.getCarModel()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.dataHandleResults())
                .subscribeWith(new CommonSubscriber<List<CarModelEntity>>(mView) {
                    @Override
                    public void onNext(List<CarModelEntity> user) {
                        mView.UserCarModel(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserCarModelErr(e.getMessage());
                    }
                }));
    }

    @Override
    public void getUserCarLength() {
        addSubscribe(dataManager.getCarLength()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.dataHandleResults())
                .subscribeWith(new CommonSubscriber<List<CarLengthEntity>>(mView) {
                    @Override
                    public void onNext(List<CarLengthEntity> user) {
                        mView.UserCarLength(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserCarLengthErr(e.getMessage());
                    }
                }));
    }
}
