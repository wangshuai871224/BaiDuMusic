package com.example.dllo.baidumusic.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

/**
 * Created by dllo on 16/10/31.
 */
public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {

    private RecyclerView.LayoutManager layoutManager;
    private int currentPage = 0;

    //已经加载的Item数量
    private int totallItemCount;

    //主要用来 存储上一个totallItemCount
    private int previousTotal = 0;

    //在欧屏上可见的item数量
    private int visibleItemCount;

    //在屏幕上可见的额item中的第一个
    private int firstVisibleItem;

    public EndLessOnScrollListener(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    //是否上拉数据
    private boolean loading = true;

    private void getNumbers() {
        totallItemCount = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {

            firstVisibleItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

        } else if (layoutManager instanceof StaggeredGridLayoutManager) {

            int[] firstVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null);
            firstVisibleItem = firstVisibleItemPositions[0];

        }else if(layoutManager instanceof GridLayoutManager){

            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
            firstVisibleItem = firstVisibleItemPosition;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        getNumbers();
        if (loading) {
            if (totallItemCount > previousTotal) {
                //说明加载结束
                loading = false;
                previousTotal = totallItemCount;
            }
        }
        if (!loading && totallItemCount - visibleItemCount <= firstVisibleItem) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }
    protected abstract void onLoadMore(int currentPage);
}
