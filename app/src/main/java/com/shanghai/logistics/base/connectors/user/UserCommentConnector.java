package com.shanghai.logistics.base.connectors.user;

import com.shanghai.logistics.base.BasePresenter;
import com.shanghai.logistics.base.BaseView;
import com.shanghai.logistics.models.entity.user.CommentEntity;
import com.shanghai.logistics.models.entity.user.SpecialEntity;

import java.util.List;

/**
 * @author chensong
 * @date 2019/4/17 13:17
 */
public interface UserCommentConnector {

    interface View extends BaseView {
        void UserComment(List<CommentEntity> entities);

        void UserCommentErr(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getUserComment(int dedicatedLineId, int pageNow);
    }
}
