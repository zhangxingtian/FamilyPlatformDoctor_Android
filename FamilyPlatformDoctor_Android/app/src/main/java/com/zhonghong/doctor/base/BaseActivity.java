package com.zhonghong.doctor.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.umeng.analytics.MobclickAgent;
import com.zhonghong.doctor.util.SystemUtils;
import com.zhonghong.doctor.util.page.FragmentID;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    private String pageFunction = new String();

    /**
     * 设置页面的功能
     *
     * @param pageFunction 功能的详细说明
     */
    public void setPageFunction(String pageFunction) {
        this.pageFunction = pageFunction;
    }

    /**
     * Activity跳转
     *
     */
    public void navigationToSecondActivity(int fragmentID, Bundle bundle) {
        if (bundle == null || fragmentID == 0)
            return;
        bundle.putInt(FragmentID.ID, fragmentID);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(this, SubActivity.class);
        startActivity(intent);
    }

    /**
     * Activity跳转
     *
     */
    public void navigationToSecondActivity(int fragmentID) {
        Bundle bundle = new Bundle();
        navigationToSecondActivity(fragmentID, bundle);
    }

    /**
     * 跳转activity
     *
     * @param cls    目标activity
     * @param bundle 传入bundle
     */
    public void navigationToActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转activity 目标activity
     *
     */
    public void navigationToActivity(Class cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

//    /**
//     * 网页
//     */
//    public void navigationWebActivity(String strTitle, String strUrl) {
//        Intent intent = new Intent(this, WebViewActivity.class);
//        intent.putExtra(WebViewActivity.TITLE, strTitle);
//        intent.putExtra(WebViewActivity.URL, strUrl);
//        startActivity(intent);
//    }


}
