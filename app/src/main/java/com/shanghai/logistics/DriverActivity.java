package com.shanghai.logistics;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.adapter.main.DriverViewPagerAdapter;
import com.shanghai.logistics.ui.adapter.main.UserViewPagerAdapter;
import com.shanghai.logistics.ui.driver_activity.DriverReleaseActivity;
import com.shanghai.logistics.ui.driver_fragment.DriverHomeFragment;
import com.shanghai.logistics.ui.driver_fragment.DriverMeFragment;
import com.shanghai.logistics.ui.driver_fragment.DriverOrderFragment;
import com.shanghai.logistics.ui.driver_fragment.DriverReleaseFragment;
import com.shanghai.logistics.ui.mian_fragment.AddressListFragment;
import com.shanghai.logistics.ui.mian_fragment.DynamicFragment;
import com.shanghai.logistics.ui.mian_fragment.MainMeFragment;
import com.shanghai.logistics.ui.mian_fragment.MessageFragment;
import com.shanghai.logistics.ui.user_fragment.UserLinkmanFragment;
import com.shanghai.logistics.util.ActivityCollector;
import com.shanghai.logistics.util.DestroyActivityUtil;
import com.shanghai.logistics.widget.navigation.BottomNavigation;
import com.shanghai.logistics.widget.navigation.BottomNavigationAdapter;
import com.shanghai.logistics.widget.navigation.BottomNavigationViewPager;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 司机端
 * */
public class DriverActivity extends SimpleActivity {

    private FragmentManager supportFragmentManager;
    @BindView(R.id.frameLayout_activity)
    FrameLayout frameLayoutUserActivity;
    @BindView(R.id.rb_home)
    RadioButton mRvHome;
    @BindView(R.id.rb_order)
    RadioButton mRbOrder;
    @BindView(R.id.tv_release)
    TextView mTvRelease;
    @BindView(R.id.rb_linkman)
    RadioButton mRbLinkman;
    @BindView(R.id.rb_mine)
    RadioButton mRbMine;
    @BindView(R.id.ll_release)
    LinearLayout mLlRelease;
    Bundle bundle;
    int fragmentType = 0;
    long mBackTime;

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
    }

    @Override
    protected void initEventAndData() {
        supportFragmentManager = getSupportFragmentManager();
        setShowFragment(fragmentType);
        //将activity 添加 以便在特定节点关闭
        DestroyActivityUtil.addDestoryActivityToMap(this, "DriverActivity");
        //判断当前是否用户类型
        App.kv.encode(Constants.SELECTED_USER_TYPE, Constants.DRIVER_CLIENT);
        setSwipeBackEnable(false);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.red)
                .init();


    }

    @OnClick({R.id.rb_home, R.id.rb_linkman, R.id.tv_release, R.id.rb_order, R.id.rb_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home://首页
                mRbLinkman.setChecked(false);
                mRbOrder.setChecked(false);
                mRbMine.setChecked(false);
                mRvHome.setChecked(true);
                fragmentType = 0;
                DriverHomeFragment.startFragment(R.id.frameLayout_activity, DriverHomeFragment.class, bundle, supportFragmentManager, 1);
                break;
            case R.id.rb_order://订单
                mRbLinkman.setChecked(false);
                mRbOrder.setChecked(true);
                mRbMine.setChecked(false);
                mRvHome.setChecked(false);
                fragmentType = 1;
                DriverOrderFragment.startFragment(R.id.frameLayout_activity, DriverOrderFragment.class, bundle, supportFragmentManager, 1);
                break;
            case R.id.tv_release://快速发布
//                mRbLinkman.setChecked(false);
//                mRbOrder.setChecked(false);
//                mRbMine.setChecked(false);
//                mRvHome.setChecked(false);
                fragmentType = 2;
                startActivity(new Intent(this,DriverReleaseActivity.class));
                overridePendingTransition(R.anim.bottom_in,R.anim.bottom_silent);
             //   DriverReleaseFragment.startFragment(R.id.frameLayout_activity, DriverReleaseFragment.class, bundle, supportFragmentManager, 1);
                break;
            case R.id.rb_linkman://联系人
                mRbLinkman.setChecked(true);
                mRbOrder.setChecked(false);
                mRbMine.setChecked(false);
                mRvHome.setChecked(false);
                fragmentType = 3;
                UserLinkmanFragment.startFragment(R.id.frameLayout_activity, UserLinkmanFragment.class, bundle, supportFragmentManager, 1);
                break;
            case R.id.rb_mine://我的
                mRbLinkman.setChecked(false);
                mRbOrder.setChecked(false);
                mRbMine.setChecked(true);
                mRvHome.setChecked(false);
                fragmentType = 4;
                DriverMeFragment.startFragment(R.id.frameLayout_activity, DriverMeFragment.class, bundle, supportFragmentManager, 1);
                break;

        }
        Log.i(TAG, fragmentType + "");
    }

    /**
     * 通过PageType  显示Fragment
     */
    private void setShowFragment(int fragmentType) {
        // if (bundle == null) bundle = new Bundle();
        //   int pageType = intent.getIntExtra("pageType", PageType.HomeFragmentType);
        switch (fragmentType) {
            case 0:
                mRbLinkman.setChecked(false);
                mRbOrder.setChecked(false);
                mRbMine.setChecked(false);
                mRvHome.setChecked(true);
                DriverHomeFragment.startFragment(R.id.frameLayout_activity, DriverHomeFragment.class, bundle, supportFragmentManager, 1);
                break;
            case 1:
                mRbLinkman.setChecked(false);
                mRbOrder.setChecked(true);
                mRbMine.setChecked(false);
                mRvHome.setChecked(false);
                DriverOrderFragment.startFragment(R.id.frameLayout_activity, DriverOrderFragment.class, bundle, supportFragmentManager, 1);
                break;
            case 2://快速发布订单
//                mRbLinkman.setChecked(false);
//                mRbOrder.setChecked(false);
//                mRbMine.setChecked(false);
//                mRvHome.setChecked(false);
                DriverReleaseFragment.startFragment(R.id.frameLayout_activity, DriverReleaseFragment.class, bundle, supportFragmentManager, 1);
                break;
            case 3://联系人
                mRbLinkman.setChecked(true);
                mRbOrder.setChecked(false);
                mRbMine.setChecked(false);
                mRvHome.setChecked(false);
                UserLinkmanFragment.startFragment(R.id.frameLayout_activity, UserLinkmanFragment.class, bundle, supportFragmentManager, 1);
                break;
            case 4://我的
                mRbLinkman.setChecked(false);
                mRbOrder.setChecked(false);
                mRbMine.setChecked(true);
                mRvHome.setChecked(false);
                DriverMeFragment.startFragment(R.id.frameLayout_activity, DriverMeFragment.class, bundle, supportFragmentManager, 1);
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mBackTime > 2000) {
                Toast.makeText(mContext, "再按一次退出", Toast.LENGTH_SHORT).show();
                mBackTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //   App.refWatcher.watch(this);
    }
}