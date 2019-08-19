package com.zn.basemodule.base;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

import com.blankj.utilcode.util.LogUtils;
import com.trello.rxlifecycle2.components.RxActivity;
import com.zn.basemodule.loadview.RLoadingDialog;
import com.zn.basemodule.manager.ActivityStackManager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * 基类Activity
 * 备注:所有的Activity都继承自此Activity
 * 1.规范团队开发
 * 2.统一处理Activity所需配置,初始化
 */
    public abstract class BaseActivity extends RxActivity implements EasyPermissions.PermissionCallbacks {

    protected Context mContext;
    protected Unbinder unBinder;
    public LifeCycleListener mListener;
    public RLoadingDialog mLoadingDialog;
//
//    private boolean fixOrientation() {
//        try {
//            Field field = Activity.class.getDeclaredField("mActivityInfo");
//            field.setAccessible(true);
//            ActivityInfo o = (ActivityInfo) field.get(this);
//            o.screenOrientation = -1;
//            field.setAccessible(false);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
//            boolean result = fixOrientation();
//        }

        super.onCreate(savedInstanceState);
        if (mListener != null) {
            mListener.onCreate(savedInstanceState);
        }
        mContext = this;


        mLoadingDialog = new RLoadingDialog(this);
        setContentView(getContentViewId());
        unBinder = ButterKnife.bind(this);

        initBundleData();
        initView();


        initData();
    }

//    private boolean isTranslucentOrFloating() {
//        boolean isTranslucentOrFloating = false;
//        try {
//            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
//            final TypedArray ta = obtainStyledAttributes(styleableRes);
//            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
//            m.setAccessible(true);
//            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
//            m.setAccessible(false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return isTranslucentOrFloating;
//
//    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mListener != null) {
            mListener.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mListener != null) {
            mListener.onRestart();
        }
    }

//    @Override
//    public void setRequestedOrientation(int requestedOrientation) {
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
//            return;
//        }
//        super.setRequestedOrientation(requestedOrientation);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onResume();
        }
        LogUtils.e(this.getClass().getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener.onDestroy();
        }
        //移除view绑定
        if (unBinder != null) {
            unBinder.unbind();
        }
        ActivityStackManager.getManager().remove(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
    }

    /**
     * 获取显示view的xml文件ID
     */
    protected abstract int getContentViewId();


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
        mLoadingDialog.showDialog();
    }

    public void dismissDialog() {
        mLoadingDialog.dismissDialog();
    }

    /**
     * 设置生命周期回调函数
     *
     * @param listener
     */
    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }


    public void setTitle(String title) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
