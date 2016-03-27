package com.zhonghong.doctor.ui;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.zhonghong.doctor.R;
import com.zhonghong.doctor.base.MvcActivity;
import com.zhonghong.doctor.util.SystemUtils;

public class MainActivity extends MvcActivity implements OnClickListener {

    private boolean canFinish = false;

    @Override
    public int getLayoutID() {
        return R.layout.main;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
    @Override
    public void onBackPressed() {
        if (!canFinish) {
            Toast.makeText(this, MainActivity.this.getString(R.string.notice_exit) + MainActivity.this.getString(R.string.app_name), Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(() -> canFinish = false, 2000);
            canFinish = true;
            return;
        }
        SystemUtils.getInstance().exit(this);
    }
}
