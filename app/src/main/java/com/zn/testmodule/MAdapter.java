package com.zn.testmodule;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by zn on 2019-08-16.
 * Describe
 */
public class MAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MAdapter() {
        super(R.layout.brvah_quick_view_load_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
