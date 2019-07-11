package com.shanghai.logistics.ui.activity.login;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.SimpleActivity;
import com.shanghai.logistics.base.connectors.login.ChangePwdConnector;
import com.shanghai.logistics.base.connectors.login.RegisterConnector;
import com.shanghai.logistics.presenters.login.ChangePwdPresenter;
import com.shanghai.logistics.presenters.login.RegisterPresenter;
import com.shanghai.logistics.util.FormatUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity<ChangePwdPresenter> implements ChangePwdConnector.View {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.tv_h_code)
    TextView mTvHCode;
    @BindView(R.id.et_pwd)
    EditText mEtNewPwd;
    @BindView(R.id.see_check)
    CheckBox mCheck;
    @BindView(R.id.tv_change_pwd)
    TextView mTvChangePwd;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    String phone, code, newPwd;

    @Override
    protected int getLayout() {
        return R.layout.activity_change_password;

    }

    @Override
    protected void initEventAndData() {
        mTvTitle.setText("忘记密码");
    }

    @Override
    public void changePwd() {
        ToastUtils.show("修改成功");
    }

    @Override
    public void sendCodeData() {
        ToastUtils.show("验证码获取成功");
    }

    @Override
    public void sendCodeErr(String msg) {
        int msgs = Integer.parseInt(msg);
        switch (msgs) {

            case -2:
                ToastUtils.show("该手机号已经注册过");
                break;
            case -3:
                ToastUtils.show("该手机号未注册过");
                break;
            case -5:
                ToastUtils.show("操作太频繁，发送频率超过限制，稍后重试");
                break;
            default:
                ToastUtils.show("获取验证码失败");
                break;
        }

    }

    @Override
    public void changePwdErr(String msg) {
        ToastUtils.show("修改失败");
    }

    @OnClick({R.id.img_back, R.id.tv_change_pwd, R.id.tv_h_code})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:

                break;
            case R.id.tv_h_code:
                phone = mEtPhone.getText().toString();
                if (phone.isEmpty()) {
                    ToastUtils.show("手机号不能为空");
                    return;
                }
                if (!FormatUtil.isMobileNO(phone)) {
                    ToastUtils.show("手机号格式不正确");
                    return;
                }
                mPresenter.getSendCode(phone, 2);
                break;
            case R.id.tv_change_pwd:
                phone = mEtPhone.getText().toString();
                code = mEtCode.getText().toString();
                newPwd = mEtNewPwd.getText().toString();
                if (phone.isEmpty()) {
                    ToastUtils.show("手机号不能为空");
                    return;
                }
                if (!FormatUtil.isMobileNO(phone)) {
                    ToastUtils.show("手机号格式不正确");
                    return;
                }

                if (code.isEmpty()) {
                    ToastUtils.show("验证码不能为空");
                    return;
                }
                if (newPwd.isEmpty()) {
                    ToastUtils.show("密码不能为空");
                    return;
                }
                mPresenter.getChangePwd(phone, newPwd, code);
                break;

        }

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


}
