package com.shanghai.logistics.ui.mian_fragment.dynamic;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.shanghai.logistics.R;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.main_activity.dynamic.DynamicDActivity;
import com.shanghai.logistics.ui.main_activity.main_me.DynamicActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 动态
 */
public class DynamicViewPagerFragment extends SimpleFragment {

    @BindView(R.id.img_go_dy)
    ImageView mImgGoDy;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic_view_pager;
    }


    @OnClick({R.id.img_go_dy})
    void DynamicOnclick(View v) {
        switch (v.getId()) {
            case R.id.img_go_dy:
                startActivity(new Intent(getActivity(), DynamicDActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void initImmersionBar() {

    }
}
