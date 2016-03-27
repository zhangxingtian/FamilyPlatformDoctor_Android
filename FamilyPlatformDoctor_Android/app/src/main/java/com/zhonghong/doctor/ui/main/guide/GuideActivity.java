package com.zhonghong.doctor.ui.main.guide;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhonghong.doctor.R;
import com.zhonghong.doctor.app.DoctorApp;
import com.zhonghong.doctor.base.BaseActivity;
import com.zhonghong.doctor.base.InitInterface;
import com.zhonghong.doctor.ui.MainActivity;
import com.zhonghong.doctor.util.SpsUtil;
import com.zhonghong.doctor.util.UiUtils;
import com.zhonghong.doctor.view.FlowIndicator;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams;

/**
 * Created by guobin.zheng on 2015/9/6.
 * 导航页面
 */
public class GuideActivity extends BaseActivity implements InitInterface {

    private ViewPager vp;
    private FlowIndicator fi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.guide_activity);

        initViews();
        initData();
        setListener();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViews() {
        fi = (FlowIndicator) findViewById(R.id.fi);
        fi.setCount(3);
        fi.setSelection(0);
        vp = (ViewPager) findViewById(R.id.vp_guide);
        vp.setPageMargin(UiUtils.dpToPx(5f, getResources()));

    }

    @Override
    public void initData() {
        SpsUtil.editBoolean(R.string.GUIDE_IS_HAD, true);
        SpsUtil.editInt(R.string.GUIDE_VERSION_CODE, DoctorApp.getVersionCode());
        List<View> guideList = new ArrayList<>();
        guideList.add(getGuideView(0));
        guideList.add(getGuideView(0));
        guideList.add(getGuideView(0));
        RelativeLayout rl = new RelativeLayout(this);
        rl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        rl.setBackgroundResource(R.color.tran);
        guideList.add(rl);
        GuidePageAdapter vpAdapter = new GuidePageAdapter(guideList);
        vp.setAdapter(vpAdapter);
    }

    @Override
    public void setListener() {
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                fi.setSelection(i);
                if (i == 3)
                    new Handler().postDelayed(() -> {
                        navigationToActivity(MainActivity.class);
                        finish();
                    }, 500);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }

        });
    }


    private View getGuideView(int nDrawableID) {
        ImageView iv = new ImageView(this);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);
        iv.setBackgroundColor(this.getResources().getColor(R.color.gray_40));
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iv.setImageResource(nDrawableID);
        return iv;
    }

    @Override
    protected void onDestroy() {
        vp = null;
        super.onDestroy();
    }
}
