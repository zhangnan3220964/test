package com.zn.testmodule;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zn.basemodule.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.simple_view)
    SimpleDraweeView simple_view;
    @BindView(R.id.recycle)
    RecyclerView recycle;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        text.setText("测试module");
        simple_view.setImageURI("https://upload-images.jianshu.io/upload_images/625706-793ab2c865916c70.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/803/format/webp");

        MAdapter adapter=new MAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setAdapter(adapter);
        List<String> list=new ArrayList<>();
        for (int i = 0; i <8 ; i++) {
            list.add("iowdi");

        }
        adapter.setNewData(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(MainActivity.this,WebviewActivity.class));
            }
        });

    }

    @Override
    protected void initData() {

        LogUtils.e("测试环境");
    }
}
