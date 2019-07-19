package com.shanghai.logistics.ui.main_adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.models.entity.main.FriendEntity;
import com.shanghai.logistics.models.entity.user.LinePhoneEntity;

import java.util.List;

public class MainFriendsAdapter extends BaseQuickAdapter<FriendEntity, BaseViewHolder> {

    public MainFriendsAdapter(@Nullable List<FriendEntity> data) {
        super(R.layout.item_friends, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendEntity item) {
        Glide.with(mContext).load(Constants.MAIN_URL+item.getHeadUrl()).into((ImageView) helper.getView(R.id.img_head));
        helper.setText(R.id.tv_name, item.getNameRemark());

    }
}
