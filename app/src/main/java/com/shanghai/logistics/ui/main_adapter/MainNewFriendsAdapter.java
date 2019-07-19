package com.shanghai.logistics.ui.main_adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.models.entity.main.FriendEntity;
import com.shanghai.logistics.models.entity.main.NewFriendEntity;

import java.util.List;

public class MainNewFriendsAdapter extends BaseQuickAdapter<NewFriendEntity, BaseViewHolder> {

    public MainNewFriendsAdapter(@Nullable List<NewFriendEntity> data) {
        super(R.layout.item_new_friend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewFriendEntity item) {
        Glide.with(mContext).load(Constants.MAIN_URL + item.getHeadUrl()).into((ImageView) helper.getView(R.id.img_head));
        helper.setText(R.id.tv_name, item.getNickName());
        switch (item.getStatus()) {
            case 1:
                helper.setText(R.id.tv_accept, "接受");
                break;
            case 2:
                helper.setText(R.id.tv_accept, "已接受");
                break;
            case 3:
                helper.setText(R.id.tv_accept, "已拒绝");
                helper.getView(R.id.tv_accept).setBackgroundColor(ContextCompat.getColor(mContext, R.color.hui4));
                break;
        }


    }
}
