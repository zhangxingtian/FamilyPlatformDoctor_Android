package com.zhonghong.doctor.base;

import android.os.Bundle;

import com.zhonghong.doctor.R;
import com.zhonghong.doctor.util.page.FragmentID;

public class SubActivity extends BaseFragmentActivity {

    private BaseFragment contentFg;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.sub_activity);
        switchContent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState == null)
            return;
        outState.clear();
        outState.putAll(getIntent().getExtras());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            return;
        getIntent().getExtras().clear();
        getIntent().getExtras().putAll(savedInstanceState);
        switchContent();
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void switchContent() {
        Bundle bundle = getIntent().getExtras();
        int nFragmentId = bundle.getInt(FragmentID.ID);
        BaseFragment fg = null;

        switch (nFragmentId) {

            default:
                break;
        }

        if (fg != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.v_content, fg).commit();
            contentFg = fg;
        }

    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (contentFg != null)
            contentFg.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        contentFg.onDestroy();
        super.onDestroy();
    }

}
