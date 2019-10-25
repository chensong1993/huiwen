package com.shanghai.logistics.ui.activity.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.MainActivity;
import com.shanghai.logistics.R;
import com.shanghai.logistics.UserActivity;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.connectors.login.LoginConnector;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.models.http.cookies.CookiesManager;
import com.shanghai.logistics.presenters.login.LoginPresenter;
import com.shanghai.logistics.util.ActivityCollector;
import com.shanghai.logistics.util.DestroyActivityUtil;
import com.shanghai.logistics.util.FormatUtil;
import com.shanghai.logistics.util.RepeatClickUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @note 登录主界面
 * @anthor Song Chen
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginConnector.View {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_forgetpwd)
    TextView mTvForgerPwd;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.see_check_box_login_fragment)
    CheckBox mCbSeePwd;
    Animation mShakeAnim;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        //将activity 添加 以便在特定节点关闭
        DestroyActivityUtil.addDestoryActivityToMap(this, "LoginActivity");
        mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_view);
        mTvTitle.setText("登录");
        setSwipeBackEnable(false);
        mImgBack.setVisibility(View.GONE);
    }


    @OnClick({R.id.see_check_box_login_fragment, R.id.img_back, R.id.tv_login, R.id.tv_register, R.id.tv_forgetpwd})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                //  finish();
                break;
            case R.id.see_check_box_login_fragment: //查看密码
                boolean checked = mCbSeePwd.isChecked();
                if (checked) {
                    mEtPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mEtPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_login:
                if (RepeatClickUtil.isFastDoubleClick()) {
                    String phone = mEtPhone.getText().toString();
                    String pwd = mEtPwd.getText().toString();
                    if (phone.isEmpty()) {
                        ToastUtils.show("手机号不能为空");
                        return;
                    }
                    if (!FormatUtil.isMobileNO(phone)) {
                        ToastUtils.show("手机号格式不正确");
                        return;
                    }
                    mPresenter.getShowLogin(phone, pwd);
                    Log.i("onLogin", phone + pwd);
                }
                break;
            case R.id.tv_forgetpwd:
                startActivity(new Intent(this, ChangePasswordActivity.class));
                break;
        }

    }


    @Override
    public void showLogin(LoginEntity entities) {
        App.kv.encode(Constants.USER_PHONE, entities.getPhone());
        App.kv.encode(Constants.RONG_TOKEN, entities.getRongToken());
        App.kv.encode(Constants.RONG_TARGETID, entities.getId());
        ToastUtils.show("登录成功");
        if (ActivityCollector.isActivityExist(MainActivity.class)) {
            finish();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.PHONE, mEtPhone.getText().toString());
            intent.putExtra(Constants.LOGIN_USER_INFO, bundle);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void LoginErr(String err) {
        switch (err) {
            case "0":
                ToastUtils.show("账号或密码错误");
                break;
            case "-1":
                ToastUtils.show("看看还有哪项没填哦");
                break;
            default:
                ToastUtils.show("登录失败");
                break;
        }
    }

    @Override
    public void stateError() {
        super.stateError();
        mTvLogin.startAnimation(mShakeAnim);
        CookiesManager.clearAllCookies();
        App.kv.remove(Constants.USER_PHONE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // App.refWatcher.watch(this);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
