package com.zhonghong.doctor.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.umeng.analytics.MobclickAgent;
import com.zhonghong.doctor.util.KeyBoardControllerUtil;
import com.zhonghong.doctor.util.mode.ExitAccountNotice;
import com.zhonghong.doctor.util.mode.observer.Observer;
import com.zhonghong.doctor.util.page.FragmentID;

/**
 * Created by guobin.zheng on 2015/1/23.
 * fragment 基类
 */
public abstract class BaseFragment extends Fragment {

    protected View contentView;
    protected Context mContext;
    protected Bundle mBundle;
    protected String strClassName;
    private Observer exitAccountObserver = id -> getActivity().finish();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO 自动生成的方法存根
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mBundle = getArguments();
        strClassName = ((Object) this).getClass().getSimpleName();
        ExitAccountNotice.getInstance().addObserver(exitAccountObserver);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(strClassName); //统计页面
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(strClassName);
    }

    @Override
    public void onDetach() {
        // TODO 自动生成的方法存根
        ExitAccountNotice.getInstance().removeObserver(exitAccountObserver);
        exitAccountObserver = null;
        contentView = null;
        mContext = null;
        mBundle = null;
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


//    /**
//     * 如果堆栈里面还有fragment就返回上一层fragment，否则调用activity的finish()方法
//     */
//    public void popBack() {
//        if (getFragmentManager().getBackStackEntryCount() == 0) {
//            getActivity().finish();
//            return;
//        }
//        getFragmentManager().popBackStack();
//    }
//
//
//    public void addFragment(Fragment fragment, int contentId) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.add(contentId, fragment);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
//
//    public void replaceFragment(Fragment fragment, int contentId) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(contentId, fragment);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

    /**
     * Activity跳转
     */
    public void toSecondActivity(int fragmentID, Bundle bundle) {
        if (bundle == null || fragmentID == 0)
            return;
        bundle.putInt(FragmentID.ID, fragmentID);
        Intent intent = new Intent();
        intent.setClass(mContext, SubActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void toSecondActivityForRequest(int fragmentID, Bundle bundle, int nRequestCode) {
        if (bundle == null || fragmentID == 0 || nRequestCode == 0)
            return;
        bundle.putInt(FragmentID.ID, fragmentID);
        Intent intent = new Intent();
        intent.setClass(mContext, SubActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, nRequestCode);
    }

    public void toSecondActivityForRequest(Class cls, Bundle bundle, int nRequestCode) {
        if (bundle == null || cls == null || nRequestCode == 0)
            return;
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, nRequestCode);
    }

//    public void toSecondActivity(Class cls) {
//        Intent intent = new Intent();
//        intent.setClass(mContext, cls);
//        startActivity(intent);
//    }

    /**
     * Activity跳转
     */
    public void toSecondActivity(int fragmentID) {
        Bundle bundle = new Bundle();
        toSecondActivity(fragmentID, bundle);
    }

    public void onBackPressed() {
        KeyBoardControllerUtil.Hide(mContext, contentView);
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            getActivity().finish();
            return;
        }
        getFragmentManager().popBackStack();
    }
}
