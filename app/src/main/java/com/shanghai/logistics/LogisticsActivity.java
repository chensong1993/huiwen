package com.shanghai.logistics;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.adapter.main.LogisticsViewPagerAdapter;
import com.shanghai.logistics.ui.adapter.main.UserViewPagerAdapter;
import com.shanghai.logistics.ui.driver_fragment.DriverHomeFragment;
import com.shanghai.logistics.ui.driver_fragment.DriverMeFragment;
import com.shanghai.logistics.ui.driver_fragment.DriverOrderFragment;
import com.shanghai.logistics.ui.driver_fragment.DriverReleaseFragment;
import com.shanghai.logistics.ui.logistics_fragment.LogisticsHomeFragment;
import com.shanghai.logistics.ui.logistics_fragment.LogisticsMeFragment;
import com.shanghai.logistics.ui.user_activity.release.UserReleaseActivity;
import com.shanghai.logistics.ui.user_fragment.UserLinkmanFragment;
import com.shanghai.logistics.ui.user_fragment.UserReleaseFragment;
import com.shanghai.logistics.util.DestroyActivityUtil;
import com.shanghai.logistics.widget.navigation.BottomNavigation;
import com.shanghai.logistics.widget.navigation.BottomNavigationAdapter;
import com.shanghai.logistics.widget.navigation.BottomNavigationViewPager;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 物流端
 * */
public class LogisticsActivity extends SimpleActivity {
    public static final String TAG = "LogisticsActivity";
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
    int fragmentType;
    long mBackTime;

    //private LogisticsViewPagerAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }


    @Override
    protected void initEventAndData() {
        boolean b = hasPermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (!b) {
            requestPermission(Constants.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        //将activity 添加 以便在特定节点关闭
        DestroyActivityUtil.addDestoryActivityToMap(this, "LogisticsActivity");
        //判断当前是否用户类型
        App.kv.encode(Constants.SELECTED_USER_TYPE, Constants.LOGISTICS_CLIENT);

        setSwipeBackEnable(false);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.red)
                .init();

        supportFragmentManager = getSupportFragmentManager();
        setShowFragment(fragmentType);
        Log.i(TAG, "initEventAndData: " + fragmentType);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: " + fragmentType);
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
                LogisticsHomeFragment.startFragment(R.id.frameLayout_activity, LogisticsHomeFragment.class, bundle, supportFragmentManager, 1);
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
                startActivity(new Intent(this, UserReleaseActivity.class));
                overridePendingTransition(R.anim.bottom_in, R.anim.bottom_silent);
                //  DriverReleaseFragment.startFragment(R.id.frameLayout_activity, DriverReleaseFragment.class, bundle, supportFragmentManager, 1);
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
                LogisticsMeFragment.startFragment(R.id.frameLayout_activity, LogisticsMeFragment.class, bundle, supportFragmentManager, 1);
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
                LogisticsHomeFragment.startFragment(R.id.frameLayout_activity, LogisticsHomeFragment.class, bundle, supportFragmentManager, 0);
                break;
            case 1:
                mRbLinkman.setChecked(false);
                mRbOrder.setChecked(true);
                mRbMine.setChecked(false);
                mRvHome.setChecked(false);
                DriverOrderFragment.startFragment(R.id.frameLayout_activity, DriverOrderFragment.class, bundle, supportFragmentManager, 0);
                break;
            case 2://快速发布订单
//                mRbLinkman.setChecked(false);
//                mRbOrder.setChecked(false);
//                mRbMine.setChecked(false);
//                mRvHome.setChecked(false);
                DriverReleaseFragment.startFragment(R.id.frameLayout_activity, DriverReleaseFragment.class, bundle, supportFragmentManager, 0);
                break;
            case 3://联系人
                mRbLinkman.setChecked(true);
                mRbOrder.setChecked(false);
                mRbMine.setChecked(false);
                mRvHome.setChecked(false);
                UserLinkmanFragment.startFragment(R.id.frameLayout_activity, UserLinkmanFragment.class, bundle, supportFragmentManager, 0);
                break;
            case 4://我的
                mRbLinkman.setChecked(false);
                mRbOrder.setChecked(false);
                mRbMine.setChecked(true);
                mRvHome.setChecked(false);
                LogisticsMeFragment.startFragment(R.id.frameLayout_activity, LogisticsMeFragment.class, bundle, supportFragmentManager, 0);
                break;

        }
    }

    /**
     * 申请指定的权限.
     */
    public void requestPermission(int requestCode, String... permissions) {

        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }


    /**
     * 判断是否有指定的权限
     */
    public boolean hasPermission(String... permissions) {

        for (String permisson : permissions) {
            if (ActivityCompat.checkSelfPermission(App.getInstance(), permisson)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        } else {
            ToastUtils.show("您已拒绝使用定位功能");
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
