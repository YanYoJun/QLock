package com.plbear.qlock;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ComponentName mAdminName = null;
    private static final String TAG = "plbear";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdminName = new ComponentName(this, AdminManageReceiver.class);
        //获取设备管理器
        DevicePolicyManager mDPM = (DevicePolicyManager) this.getSystemService(Context.DEVICE_POLICY_SERVICE);
        if (!mDPM.isAdminActive(mAdminName)) {
            showAdminManage();
        }

        if (mDPM.isAdminActive(mAdminName)) {
            mDPM.lockNow();
        }

        finish();
    }

    private void showAdminManage() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,mAdminName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,R.string.app_name);
        startActivityForResult(intent,-1);
    }
}
