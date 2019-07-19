package com.shanghai.logistics.ui.user_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.user.UserCommentConnector;
import com.shanghai.logistics.models.entity.user.CommentEntity;
import com.shanghai.logistics.presenters.user.UserCommentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MoreCommentsActivity extends BaseActivity<UserCommentPresenter> implements UserCommentConnector.View {
    public static final String TAG = "MoreCommentsActivity";
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_more_comments;
    }

    @OnClick({R.id.img_back})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }

    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("用户评论");
    }


    @Override
    public void UserComment(List<CommentEntity> entities) {

    }

    @Override
    public void UserCommentErr(String s) {

    }

    @Override
    public void showErrorMsg(String msg) {
        Log.i(TAG, "showErrorMsg: ");
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


}
