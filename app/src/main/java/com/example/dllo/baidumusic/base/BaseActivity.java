package com.example.dllo.baidumusic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by WangShuai on 16/10/21.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 绑定布局
        setContentView(getLayout());
        // 初始化组件
        initViews();
        // 初始化数据
        initData();
    }

    // 初始化数据 ,拉取网络请求等
    protected abstract void initData();

    // 找findViewById
    protected abstract void initViews();

    protected abstract int getLayout();

    // 简化findViewById
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    protected void setClick(View.OnClickListener clickListener, View ... views ) {
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
    }






}
