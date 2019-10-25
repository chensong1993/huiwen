package com.shanghai.logistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.ui.activity.login.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {
    public final String TAG="WelcomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if (App.kv.decodeString(Constants.USER_PHONE) != null) {
            Log.i(TAG, App.kv.decodeString(Constants.USER_PHONE));
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.PHONE, App.kv.decodeString(Constants.PHONE));
            intent.putExtra(Constants.LOGIN_USER_INFO, bundle);
            startActivity(intent);
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}
