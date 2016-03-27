package com.zhonghong.doctor.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by guobin.zheng on 2015/12/8.
 * fragmentadapter
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<MvcFragment> listFg;

    public FragmentAdapter(FragmentManager fragmentManager, List<MvcFragment> listFg) {
        super(fragmentManager);
        this.listFg = listFg;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return listFg == null ? 0 : listFg.size();
    }

    @Override
    public Fragment getItem(int position) {
        return listFg == null ? null : listFg.get(position);
    }
}

