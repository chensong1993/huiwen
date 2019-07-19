package com.shanghai.logistics.presenters.user;

import com.shanghai.logistics.base.RxPresenter;
import com.shanghai.logistics.base.connectors.user.UserCommentConnector;
import com.shanghai.logistics.base.connectors.user.UserSpecialConnector;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.entity.user.CommentEntity;
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
public class UserCommentPresenter extends RxPresenter<UserCommentConnector.View> implements UserCommentConnector.Presenter {

    DataManager dataManager;

    @Inject
    public UserCommentPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(UserCommentConnector.View view) {
        super.attachView(view);

    }


    @Override
    public void getUserComment(int dedicatedLineId, int pageNow) {
        addSubscribe(dataManager.getCommentList(dedicatedLineId, pageNow)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResults())
                .subscribeWith(new CommonSubscriber<List<CommentEntity>>(mView) {
                    @Override
                    public void onNext(List<CommentEntity> entities) {
                        mView.UserComment(entities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.UserCommentErr(e.getMessage());
                    }
                }));
    }
}
