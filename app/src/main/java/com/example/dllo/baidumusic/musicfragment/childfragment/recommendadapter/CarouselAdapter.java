package com.example.dllo.baidumusic.musicfragment.childfragment.recommendadapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/10/31.
 */
public class CarouselAdapter extends PagerAdapter {

    private String[] urls;

    public void setUrls(String[] urls) {
        this.urls = urls;
        notifyDataSetChanged();
    }

    // 获取图片数量
    public int getImgCount() {
        return  urls.length;

    }
    @Override
    public int getCount() {
        return urls == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        Picasso.with(container.getContext())
                .load(urls[position%urls.length])
                .fit()
                .into(imageView);
        container.addView(imageView, ViewPager.LayoutParams.MATCH_PARENT
                , ViewPager.LayoutParams.WRAP_CONTENT);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position) == object) {
            container.removeViewAt(position);
        }
    }
}
