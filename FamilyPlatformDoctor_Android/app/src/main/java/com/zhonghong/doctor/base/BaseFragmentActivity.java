package com.zhonghong.doctor.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.umeng.analytics.MobclickAgent;
import com.zhonghong.doctor.util.SystemUtils;
import com.zhonghong.doctor.util.page.FragmentID;

public class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        SystemUtils.getInstance().addActivity(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }


    private String pageFunction = new String();

    public void setPageFunction(String pageFunction) {
        this.pageFunction = pageFunction;
    }

    public void navigationToSecondActivity(int fragmentID, Bundle bundle) {
        if (bundle == null || fragmentID == 0)
            return;
        bundle.putInt(FragmentID.ID, fragmentID);
        Intent intent = new Intent();
        intent.setClass(BaseFragmentActivity.this, SubActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void navigationToSecondActivity(int fragmentID) {
        Bundle bundle = new Bundle();
        navigationToSecondActivity(fragmentID, bundle);
    }


    public void navigationToActivity(Class cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
//        overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
    }

    public void navigationToActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(this, cls);
        startActivity(intent);
//        overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
    }
}
