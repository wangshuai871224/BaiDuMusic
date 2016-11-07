package com.example.dllo.baidumusic.tools;

import android.os.Handler;
import android.os.Looper;

import com.example.dllo.baidumusic.base.MyApp;
import com.example.dllo.baidumusic.bean.Song;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dllo on 16/11/4.
 */
public class DBTool {
    // 恶汉式单例
    private static DBTool tool = new DBTool();

    private LiteOrm mLiteOrm;

    private Handler mHandler; // 用来做线程切换的

    private Executor mThreadPool; // 线程池

    // 构造方法私有化
    public DBTool() {
        mLiteOrm = LiteOrm.newSingleInstance(MyApp.getsContext(), "baiDuMusic.db");

        // 可以在handler的构造方法里传入Looper.getMainLooper的参数
        // 来保证这个Handler一定属于主线程
        mHandler = new Handler(Looper.getMainLooper());
        // cpu数
        int coreNum = Runtime.getRuntime().availableProcessors();
        // 最大线程= 核心线程   任务队列无限大
        mThreadPool = Executors.newFixedThreadPool(coreNum + 1);

    }

    public static DBTool getInstance() {return tool;}

    // 插入方法(不需要返回值)   匿名对象runnable用外部类对象(person) 外部对象必须变成final
    public void insertSong(final Song song) {
          new Thread(new Runnable() {
              @Override
              public void run() {
                  mLiteOrm.insert(song);
              }
          });
    }

    // 查询方法(需要返回值, void改成返回的类)
    public void querySong(OnQueryListener onQueryListener) {
        mThreadPool.execute(new QueryRunnable(onQueryListener));
    }

    // 返回查询结果用的接口
    public interface OnQueryListener {
        void onQuery(List<Song> persons);
    }

    class QueryRunnable implements Runnable {
        private OnQueryListener mOnQueryListener;

        public QueryRunnable(OnQueryListener onQueryListener) {
            this.mOnQueryListener = onQueryListener;
        }

        @Override
        public void run() {
            ArrayList<Song> query = mLiteOrm.query(Song.class);
            // post发送, 发送到主线程中, runnable内的所有方法都在主线程中运行
            // 通过接口把值传出去
            mHandler.post(new CallBackRunnable(mOnQueryListener, query));
        }
    }


    // Handler使用的 将查询的数据返回到主线程使用的Runnable
    class CallBackRunnable implements Runnable {
        private OnQueryListener mOnQueryListener;
        private List<Song> mSongs;

        public CallBackRunnable(OnQueryListener mOnQueryListener, List<Song> mSongs) {
            this.mOnQueryListener = mOnQueryListener;
            this.mSongs = mSongs;
        }

        @Override
        public void run() {
            mOnQueryListener.onQuery(mSongs);
        }
    }
}
