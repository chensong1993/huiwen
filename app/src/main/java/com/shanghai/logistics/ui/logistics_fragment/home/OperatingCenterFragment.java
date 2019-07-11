package com.shanghai.logistics.ui.logistics_fragment.home;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.QRCodesActivity;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.SimpleFragment;
import com.shanghai.logistics.ui.adapter.dynamic.ViewPagerAdapter;
import com.shanghai.logistics.ui.driver_fragment.home.NewOrdersFragment;
import com.shanghai.logistics.ui.logistics_activity.AsternRecordActivity;
import com.shanghai.logistics.ui.logistics_activity.BillingActivity;
import com.shanghai.logistics.ui.logistics_activity.StartRecordingActivity;
import com.shanghai.logistics.ui.logistics_activity.WayBillActivity;
import com.shanghai.logistics.ui.user_fragment.UserHomeFragment;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 运营中心
 */
public class OperatingCenterFragment extends SimpleFragment {
    private static final int PRC_PHOTO_PICKER = 1;

    @BindView(R.id.st_tab)
    SlidingTabLayout mStTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    //扫一扫
    @BindView(R.id.ll_scan)
    LinearLayout mLlScan;
    @BindView(R.id.ll_myQRCodes)
    LinearLayout mLlMyQRCodes;
    //开单
    @BindView(R.id.ll_billing)
    LinearLayout mLlBilling;
    //运单
    @BindView(R.id.ll_waybill)
    LinearLayout mLlWayBill;
    //发车记录
    @BindView(R.id.ll_start_recording)
    LinearLayout mLlStartRecording;
    //倒车记录
    @BindView(R.id.ll_astern_record)
    LinearLayout mLlAsternRecord;

    ViewPagerAdapter mViewPagerAdapter;
    List<Fragment> fragmentList;
    Intent intent;
    String[] TITLE = {"新订单", "我的线路", "找货", "找车"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_operating__center;
    }

    @OnClick({R.id.ll_scan, R.id.ll_myQRCodes, R.id.ll_billing, R.id.ll_waybill, R.id.ll_start_recording, R.id.ll_astern_record})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_scan: //扫一扫
               // choicePhotoWrapper();
                intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, Constants.REQUEST_CODE_SCAN);
                break;
            case R.id.ll_myQRCodes: //我的二维码
                intent = new Intent(getActivity(), QRCodesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ACTIVITY_TYPE, Constants.OPERATING_CENTER_FRAGMENT);
                intent.putExtra(Constants.ACTIVITY_TYPE, bundle);
                startActivity(intent);
                break;
            case R.id.ll_billing: //开单
                startActivity(new Intent(getActivity(), BillingActivity.class));
                break;
            case R.id.ll_waybill: //运单
                startActivity(new Intent(getActivity(), WayBillActivity.class));
                break;
            case R.id.ll_start_recording: //发货记录
                startActivity(new Intent(getActivity(), StartRecordingActivity.class));
                break;
            case R.id.ll_astern_record: //到车记录
                startActivity(new Intent(getActivity(), AsternRecordActivity.class));
                break;
        }
    }

    @Override
    protected void initEventAndData() {
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        UserHomeFragment userHomeFragment = new UserHomeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt(Constants.ACTIVITY_TYPE, Constants.USER_HOME_FRAGMENT);
        //  userHomeFragment.setArguments(bundle);
        fragmentList.add(new LNewOrderFragment());
        fragmentList.add(new NewOrdersFragment());
        fragmentList.add(new FindGoodsFragment());
        fragmentList.add(new FindGoodsFragment());
        mViewPagerAdapter = new ViewPagerAdapter(this.getChildFragmentManager(), fragmentList, TITLE);
        mVp.setOffscreenPageLimit(4);
        mVp.setAdapter(mViewPagerAdapter);
        mStTab.setViewPager(mVp);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == Constants.REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                ToastUtils.show("扫描结果为：" + content);
            }
        }
    }

    @AfterPermissionGranted(PRC_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", PRC_PHOTO_PICKER, perms);
        }
    }
    @Override
    public void initImmersionBar() {

    }
}
