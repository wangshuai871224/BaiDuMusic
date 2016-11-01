package com.example.dllo.baidumusic.dynamic;


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/10/29.
 */
public class DynamicViewPagerAdapter extends PagerAdapter {

    private String[] urlImgs;

    public void setUrlImgs(String[] urlImgs) {
        this.urlImgs = urlImgs;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return urlImgs == null ? 0 : urlImgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public int getImgCount() {
        return  urlImgs.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView image = new ImageView(container.getContext());
        Picasso.with(container.getContext()).load(urlImgs[position%urlImgs.length]).fit().into(image);
        container.addView(image, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
        return image;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position) == object) {
            container.removeViewAt(position);
        }
    }
}
