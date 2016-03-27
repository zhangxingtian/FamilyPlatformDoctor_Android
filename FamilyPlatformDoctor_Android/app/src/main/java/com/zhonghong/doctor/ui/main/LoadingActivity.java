package com.zhonghong.doctor.ui.main;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;

import com.zhonghong.doctor.R;
import com.zhonghong.doctor.app.DoctorApp;
import com.zhonghong.doctor.base.BaseActivity;
import com.zhonghong.doctor.base.InitInterface;
import com.zhonghong.doctor.net.MetaUtil;
import com.zhonghong.doctor.ui.MainActivity;
import com.zhonghong.doctor.ui.main.guide.GuideActivity;
import com.zhonghong.doctor.util.SpsUtil;

public class LoadingActivity extends BaseActivity implements InitInterface {

    private RelativeLayout rlLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_activity);
        initViews();
        initData();
    }

    @Override
    public void initViews() {
        rlLoading = (RelativeLayout) findViewById(R.id.rl_loading);
        setListener();
    }

    @Override
    public void setListener() {

    }

    public void initData() {
        DoctorApp.initScreen(this);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
        alphaAnimation.setDuration(MetaUtil.getInstance().getMetaBoolean("DEBUG") ? 100 : 3000);
        alphaAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!SpsUtil.getBoolean(R.string.GUIDE_IS_HAD) || SpsUtil.getInt(R.string.GUIDE_VERSION_CODE) < DoctorApp.getVersionCode()) {
                    navigationToActivity(GuideActivity.class);
                } else navigationToActivity(MainActivity.class);
                finish();
            }
        });
        rlLoading.startAnimation(alphaAnimation);
    }
}
