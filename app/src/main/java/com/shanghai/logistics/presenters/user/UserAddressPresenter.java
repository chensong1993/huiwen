package com.shanghai.logistics.presenters.user;

import android.location.Address;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserAddressConnector;
import com.shanghai.logistics.base.connectors.user.UserSpecialConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.user.AddressListEntity;
import com.shanghai.logistics.models.entity.user.SpecialEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 * <p>
 * 专线
 */
public class UserAddressPresenter extends RxPresenter<UserAddressConnector.View> implements UserAddressConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserAddressPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserAddressConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getUserAddress(String userAccount) {
        addSubscribe(dataManager.getUserAddressList(userAccount)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<AddressListEntity>>(mView) {
                    @Override
                    public void onNext(List<AddressListEntity> entities) {
                        mView.UserAddress(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserAddressErr(e.getMessage());
                    }
                }));
    }
}
