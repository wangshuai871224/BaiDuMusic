package com.example.dllo.baidumusic.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.baidumusic.R;

/**
 * Created by WangShuai on 16/10/21.
 */
public abstract class BaseFragment extends Fragment{

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayout() == 0) {
            return inflater.inflate(R.layout.null_layout, container, false);
        }
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();// 销毁视图, 广播

    }

    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }

    // 指定在哪个view里的findViewById
    protected <T extends View> T bindView(View view, int id) {
        return (T) view.findViewById(id);
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();
}
