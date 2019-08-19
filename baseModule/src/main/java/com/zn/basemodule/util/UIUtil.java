package com.zn.basemodule.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class UIUtil {
    private static Toast sToast;
    private static int mGravity;
    private static int mXOffset;
    private static int mYOffset;

    static {
        UIUtil.sToast = null;
        UIUtil.mGravity = 0;
        UIUtil.mXOffset = 0;
        UIUtil.mYOffset = 0;
    }

    public UIUtil() {
        super();
    }

    public static int dip2px(Context context, float dpValue) {
        return ((int)(context.getResources().getDisplayMetrics().density * dpValue + 0.5f));
    }

    public static View findViewById(Activity arg1, int id) {
        View v0 = arg1 != null ? arg1.findViewById(id) : null;
        return v0;
    }

    public static View findViewById(Dialog arg1, int id) {
        View v0 = arg1 != null ? arg1.findViewById(id) : null;
        return v0;
    }

    public static View findViewById(View arg1, int id) {
        View v0 = arg1 != null ? arg1.findViewById(id) : null;
        return v0;
    }

    public static View findViewById(Window arg1, int id) {
        View v0 = arg1 != null ? arg1.findViewById(id) : null;
        return v0;
    }

    public static void finishActivity(Activity activity) {
        if(activity != null) {
            activity.finish();
        }
    }

    public static Intent getActivityIntent(Activity activity) {
        Intent v0 = activity != null ? activity.getIntent() : null;
        return v0;
    }

    public static String getActivityString(Context context, int arg2) {
        String v0 = context != null ? context.getString(arg2) : null;
        return v0;
    }

    public static String getActivityString(Context context, int arg2, Object[] arg3) {
        String v0 = context != null ? context.getString(arg2, arg3) : null;
        return v0;
    }

    public static boolean isActivityFinishing(Activity activity) {
        boolean v0 = activity == null || !activity.isFinishing() ? false : true;
        return v0;
    }

    public static Toast makeToast(Context context, int arg2, int arg3) {
        return UIUtil.makeToast(context, context.getString(arg2), arg3);
    }

    public static Toast makeToast(Context context, String arg5, int arg6) {
        Toast t = UIUtil.getInstance(context.getApplicationContext());
        t.setGravity(UIUtil.mGravity, UIUtil.mXOffset, UIUtil.mYOffset);
        t.setDuration(arg6);
        t.setText(((CharSequence)arg5));
        return t;
    }

    public static int px2dip(Context context, float arg3) {
        return ((int)(arg3 / context.getResources().getDisplayMetrics().density + 0.5f));
    }

    public static void setContentView(Activity activity, int layoutResID) {
        if(activity != null) {
            activity.setContentView(layoutResID);
        }
    }

    public static void setContentView(Dialog dialog, int layoutResID) {
        if(dialog != null) {
            dialog.setContentView(layoutResID);
        }
    }

    public static void showToast(Context context, int text, int duration) {
        UIUtil.showToast(context, context.getString(text), duration);
    }

    public static void showToast(Context context, String text, int duration) {
        UIUtil.makeToast(context, text, duration).show();
    }

    public static void showToast(Context context, int text, int duration, int arg4) {
        UIUtil.showToast(context, context.getString(text), duration, arg4);
    }

    public static void showToast(Context context, String text, int duration, int arg7) {
        Toast v0 = UIUtil.getInstance(context.getApplicationContext());
        v0.setGravity(UIUtil.mGravity, UIUtil.mXOffset, UIUtil.mYOffset);
        v0.setDuration(duration);
        v0.setText(((CharSequence)text));
        v0.show();
    }

    public static void startActivity(Activity activity, Intent intent) {
        if(activity != null) {
            try {
                activity.startActivity(intent);
            }
            catch(Exception e) {
            }
        }
    }

    public static void startActivityForResult(Activity activity, Intent intent, int arg3) {
        if(activity != null) {
            try {
                activity.startActivityForResult(intent, arg3);
            }
            catch(Exception e) {
            }
        }
    }

    private static Toast getInstance(Context context) {
        if(UIUtil.sToast == null) {
            UIUtil.sToast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_LONG);
            UIUtil.mGravity = UIUtil.sToast.getGravity();
            UIUtil.mXOffset = UIUtil.sToast.getXOffset();
            UIUtil.mYOffset = UIUtil.sToast.getYOffset();
        }

        return UIUtil.sToast;
    }
}

