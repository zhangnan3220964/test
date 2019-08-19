package com.zn.basemodule.base;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.components.RxDialogFragment;
import com.zn.basemodule.R;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


/**
 * 基类DialogFragment
 * 备注:所有的DialogFragment都继承自此Fragment
 * 1.规范团队开发
 * 2.统一处理DialogFragment所需配置,初始化
 */
public abstract class BaseDialogFragment extends RxDialogFragment implements EasyPermissions.PermissionCallbacks {

    final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    protected Context mContext;
    protected View mView;
    public LifeCycleListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (mListener != null) {
            mListener.onAttach(activity);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mListener != null) {
            mListener.onCreate(savedInstanceState);
        }
        mContext = getActivity();
        if (mContext == null) return;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().getAttributes().windowAnimations = R.style.CustomDialog;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        //getDialog().getWindow().getDecorView().setSystemUiVisibility(flags);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        mView = getContentView();
        initBundleData();
        initView();
        initData();



        if (mListener != null) {
            mListener.onCreateView(inflater, container, savedInstanceState);
        }
        if (mView.getParent() != null) {
            ((ViewGroup) mView.getParent()).removeView(mView);
        }
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mListener != null) {
            mListener.onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mListener != null) {
            mListener.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onStop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mListener != null) {
            mListener.onDestroyView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener.onDestroy();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mListener != null) {
            mListener.onDetach();
        }
    }

    /**
     * 是否已经创建
     *
     * @return
     */
    public boolean isCreated() {
        return mView != null;
    }

    /**
     * 获取显示view
     */
    protected abstract View getContentView();

    /**
     * 获取上一个界面传送过来的数据
     */
    protected abstract void initBundleData();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化Data
     */
    protected abstract void initData();


    public void showDialog() {
    }

    public void dismissDialog() {
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
    }

    /**
     * 设置生命周期回调函数
     *
     * @param listener
     */
    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            //界面可见
        } else {
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }
}
