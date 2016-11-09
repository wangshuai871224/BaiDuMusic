package com.example.dllo.baidumusic.music.childfragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.base.Point;
import com.example.dllo.baidumusic.bean.RecommendBean;
import com.example.dllo.baidumusic.music.childfragment.adapter.CarouselAdapter;
import com.example.dllo.baidumusic.tools.GsonRequest;
import com.example.dllo.baidumusic.tools.URLValues;
import com.example.dllo.baidumusic.tools.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/24.
 */
public class RecommendFragment extends BaseFragment{

    private ViewPager carousel;
    private CarouselAdapter carouselAdapter;
    private String url = URLValues.RECOMMEND_URL;
    private String[] pics;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 执行回调的地方
            if (carousel != null && msg.what == 1) {
                carousel.setCurrentItem(carousel.getCurrentItem() + 1);
            }
            mHandler.sendEmptyMessageDelayed(1, 2000);
        }
    };
    private List<Point> points;
    private LinearLayout mLinearLayout;

    @Override
    protected void initData() {
        GsonRequest<RecommendBean> gsonRequest = new GsonRequest<RecommendBean>(RecommendBean.class
                , url, new Response.Listener<RecommendBean>() {
            @Override
            public void onResponse(RecommendBean response) {


                pics = new String[]{response.getResult().getFocus().getResult().get(0).getRandpic(),
                                 response.getResult().getFocus().getResult().get(1).getRandpic(),
                                 response.getResult().getFocus().getResult().get(2).getRandpic(),
                                 response.getResult().getFocus().getResult().get(3).getRandpic(),
                                 response.getResult().getFocus().getResult().get(4).getRandpic()};
                carouselAdapter.setUrls(pics);
                carousel.setAdapter(carouselAdapter);
                initPoints();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);


        carousel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 当先显示的是第几个图
                int currentPage = position%carouselAdapter.getImgCount();

                for (Point point : points) {
                    point.setSelected(false);// 不选中
                }
                points.get(currentPage).setSelected(true);// 当前选中
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void initView() {
        carousel = bindView(R.id.carousel_picture);
        carouselAdapter = new CarouselAdapter();
        mLinearLayout = bindView(R.id.main_point_ll);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 开始轮播   参数1:message.what     参数2:时间
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public void onStop() {
        super.onStop();
        // 取消轮播
        mHandler.removeMessages(1);
    }

    public void initPoints() {
        points = new ArrayList<>();
        for (int i = 0; i < carouselAdapter.getImgCount(); i++) {
            Point point = new Point(getActivity());
            points.add(point);// 加到集合里

            LinearLayout.LayoutParams layoutParams
                    = new LinearLayout.LayoutParams(30, 30);// 宽,高,权重
            mLinearLayout.addView(point, layoutParams);
        }
    }
}
