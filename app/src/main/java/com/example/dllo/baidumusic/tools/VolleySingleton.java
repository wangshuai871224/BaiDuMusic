package com.example.dllo.baidumusic.tools;

import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.MyApp;

/**
 * Created by WangShuai on 16/10/24.
 */
public class VolleySingleton {

    // static 的变量,永远只是初始化new出的那一份
    private static VolleySingleton volleySingleton;
    private RequestQueue mRequestQueue;

    private ImageLoader mImageLoader;  // 用来请求图片的

    // 构造方法私有化,  private
    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApp.getsContext());

        // 初始化ImageLoader
        mImageLoader = new ImageLoader(mRequestQueue, new MemoryCache());
    }

    public static VolleySingleton getInstance () {

        if (volleySingleton == null) {
            // 同步锁,确保只有一个线程进去
                         // 类的对象, JVM启动时只有一次
            synchronized (VolleySingleton.class) {
                if (volleySingleton == null) {
                    volleySingleton = new VolleySingleton();
                }
            }
        }
        return volleySingleton;
    }

    // 请求图片
    public void getImage(String url, ImageView imageView) {

        mImageLoader.get(url, ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
//        mImageLoader.get(url, new AnimImageListener(imageView));
    }

    // 以后项目会用到(百度音乐中歌词部分,点击图片,用该图片作为歌词的背景图片)
//    class AnimImageListener implements  ImageLoader.ImageListener {
//
//        private ImageView mImageView;
//        @Override
//        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
//            Bitmap bitmap = response.getBitmap();
//            if (bitmap == null) {
//                // 图片还在请求中
//                mImageView.setImageResource(R.mipmap.ic_launcher);
//            }else {
//                // 图片请求成功
//                mImageView.setImageBitmap(bitmap);
//            }
//            // 添加动画效果
//            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
//            mImageView.setAnimation(alphaAnimation);
//            alphaAnimation.start();
//        }
//
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            mImageView.setImageResource(R.mipmap.ic_launcher);
//        }
//    }

    // 获得RequestQueue
    public  RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
    public <T> void addRequest(Request<T> request) {
        mRequestQueue.add(request);
    }
}
