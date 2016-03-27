package com.zhonghong.doctor.ui.main.guide;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by guobin.zheng on 2015/9/6.
 *
 * GuidePageAdapter
 */
public class GuidePageAdapter extends PagerAdapter {
    private List<View> views;
    public GuidePageAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    @Override
    public void destroyItem(View view, int arg1, Object object) {
        ((ViewPager) view).removeView(views.get(arg1));
    }

    @Override
    public Object instantiateItem(final View view, final int current) {
        ((ViewPager) view).addView(views.get(current));
        return views.get(current);
    }

}
