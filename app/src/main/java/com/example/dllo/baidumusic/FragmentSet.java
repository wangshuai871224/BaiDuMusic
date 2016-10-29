package com.example.dllo.baidumusic;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dllo.baidumusic.base.BaseFragment;


/**
 * Created by dllo on 16/10/29.
 */
public class FragmentSet extends BaseFragment implements View.OnClickListener {

    private LinearLayout back;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        back = bindView(R.id.more_back);
        setClick(this, back);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_set;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
