package com.zn.basemodule.loadview;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.blankj.utilcode.util.ScreenUtils;
import com.zn.basemodule.R;
import com.zn.basemodule.util.UIUtil;

/**
 * 自定义Loading弹窗
 */
public class RLoadingDialog {
    private Dialog progressDialog;
    private Context context;
    ImageView spaceshipImage;
    Animation animation;

    public RLoadingDialog(Context context) {
        this.context = context;
        animation= AnimationUtils.loadAnimation(context, R.anim.rotate_animation);
        createDialog();
    }

    private void createDialog() {

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        // main.xml中的ImageView
        spaceshipImage = v.findViewById(R.id.img);



        progressDialog = new Dialog(context, R.style.Transparent);    // 创建自定义样式dialog
        progressDialog.setContentView(v);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);

        Window window = progressDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.width = (int) (UIUtil.dip2px(context, 35) * ScreenUtils.getScreenDensity());
        lp.height = (int) (UIUtil.dip2px(context, 35) * ScreenUtils.getScreenDensity());
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
    }

    public void showDialog() {
        try {
            if (progressDialog != null && !progressDialog.isShowing()) {
                spaceshipImage.startAnimation(animation);
                progressDialog.show();
            }
        } catch (Exception e) {

        }
    }

    public void dismissDialog() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog.cancel();

                    }
                } catch (Exception e) {

                }

            }
        }, 500);


    }


}
