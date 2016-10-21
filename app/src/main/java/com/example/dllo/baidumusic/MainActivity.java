package com.example.dllo.baidumusic;

import android.view.View;
import android.widget.ImageView;

import com.example.dllo.baidumusic.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ImageView imageView;

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        imageView = bindView(R.id.image);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {

    }
}
