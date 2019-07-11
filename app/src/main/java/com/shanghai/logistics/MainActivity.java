package com.shanghai.logistics;

import android.util.Log;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.ui.adapter.main.MainViewPagerAdapter;
import com.shanghai.logistics.widget.navigation.BottomNavigation;
import com.shanghai.logistics.widget.navigation.BottomNavigationAdapter;
import com.shanghai.logistics.widget.navigation.BottomNavigationViewPager;

import butterknife.BindView;
public class MainActivity extends SimpleActivity {

    @BindView(R.id.view_pager)
    BottomNavigationViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigation bottomNavigation;
    @BindView(R.id.ll_dow)
    LinearLayout mLinearLayout;
    long mBackTime;

    private MainViewPagerAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void initEventAndData() {
        setSwipeBackEnable(false);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.blue)
                .init();
        // 隐藏导航栏Items
        BottomNavigationAdapter navigationAdapter = new BottomNavigationAdapter(this, R.menu.menu_main_navigation);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation);
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // 隐藏导航栏标题
        bottomNavigation.setTitleState(BottomNavigation.TitleState.ALWAYS_SHOW);
        // 导航点击事件
        bottomNavigation.setOnTabSelectedListener(new BottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position, false);

                return true;
            }
        });

        viewPager.setOffscreenPageLimit(4);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

//        RongIM.connect("ERiTVYwEQtpLMT+dbGyzm25uo30QF7fp2oUreNq+FRFCdsAjN2kYqQmorwW02yWl432BFjcxmMfpSWGdrZheBl5ioJh9BxROTTN2DBgnKdtO/m3NLMHL2c3MV7z835JMck+FEjL6ZUw=", new RongIMClient.ConnectCallback() {
//
//            /**
//             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
//             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
//             */
//            @Override
//            public void onTokenIncorrect() {
//
//            }
//
//            /**
//             * 连接融云成功
//             * @param userid 当前 token 对应的用户 id
//             */
//            @Override
//            public void onSuccess(String userid) {
//                Log.e("homeFragment", "用户模块融云连接成功targetId = " + userid);
//            }
//
//            /**
//             * 连接融云失败
//             * @param errorCode 错误码，可到官网 查看错误码对应的注释
//             */
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//                Log.e("homeFragment", "用户模块融云连接失败");
//            }
//        });
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
