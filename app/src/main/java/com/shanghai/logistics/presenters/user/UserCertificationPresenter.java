package com.shanghai.logistics.presenters.user;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserCertificationConnector;
import com.shanghai.logistics.base.connectors.user.UserSpecialConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.ApiResponse;
import com.shanghai.logistics.models.entity.user.SpecialEntity;
import com.shanghai.logistics.util.RxUtils;
import com.shanghai.logistics.widget.CommonSubscriber;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author chensong
 * @date 2019/4/17 13:15
 * <p>
 * 认证
 */
public class UserCertificationPresenter extends RxPresenter<UserCertificationConnector.View> implements UserCertificationConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserCertificationPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserCertificationConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getUserCertification(Map<String, RequestBody> files) {
//        addSubscribe(dataManager.getUserCertification(files)
//                .compose(RxUtils.handleResultFile())
//                .compose(RxUtils.rxSchedulerHelperSingle())
//                .subscribeWith(new SingleObserver<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Integer integer) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                }));
//        addSubscribe(dataManager.getUserCertification(files)
//                .compose(RxUtils.rxSchedulerHelperSingle())
//                .compose(RxUtils.handleResultFile())
//                .subscribeWith(new CommonSubscriber<List<String>>(mView) {
//                    @Override
//                    public void onNext(List<String> e) {
//                        mView.UserCertification(e.get(0));
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//                        mView.UserCertificationErr(e.getMessage());
//                    }
//                }));
    }
}
