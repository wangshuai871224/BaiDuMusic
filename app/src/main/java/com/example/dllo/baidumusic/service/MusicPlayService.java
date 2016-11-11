package com.example.dllo.baidumusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dllo.baidumusic.bean.MusicItem;
import com.example.dllo.baidumusic.bean.MusicItemBean;
import com.example.dllo.baidumusic.bean.StateBean;
import com.example.dllo.baidumusic.events.ControlEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/7.
 */
public class MusicPlayService extends Service  {


    public static final int STATE_PLAY = 1;  // 继续
    public static final int STATE_PAUSE = 2;  // 暂停

    private MediaPlayer player;// 播放器对象
    private boolean isPause;   // 播放状态
    private String playUrl;    // 音乐文件路径
    private MusicItemBean bean;
    private List<MusicItemBean> mPlayList;


    @Override
    public void onCreate() {
        super.onCreate();

        bean = new MusicItemBean();
        player = new MediaPlayer();
        // 设置可以重复播放
        player.setLooping(true);

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //TODO 播放完被调用进行操作
                player.stop();
            }
        });
        // 只注册EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }


    @Subscribe
    public void control(ControlEvent controlEvent){
        int controlKey = controlEvent.getControlKey();
        switch (controlKey){
            case ControlEvent.PLAY_OR_STOP:
                playOrPause();
                break;
            case ControlEvent.PLAY_NEXT:
                break;
            case ControlEvent.PLAY_PRE:
                break;
            case ControlEvent.CHANGE_MODE:
                break;
        }

    }

    @Override
    public void onDestroy() {

        if (player != null && player.isPlaying()) {
            player.stop();
        }
        player.release();
        // 取消注册
        EventBus.getDefault().unregister(this);
        // 以上释放完缓存之后, 在调用父类释放内存
        super.onDestroy();
    }

    @Subscribe
    public void getBean(MusicItemBean musicItemBean) {

        if (musicItemBean.getSongUrl() != null) {
            playUrl = musicItemBean.getSongUrl();
            info(playUrl);
            isPause = false;
        }
    }

    public void info(String url) {

        if (url != null) {
            try {
//                if(player.isPlaying()){
//                    player.stop();
//                    player.reset();
//                }
                // 可以清除以前播放器的状态
                player.reset();

                player.setDataSource(MusicPlayService.this, Uri.parse(url));
                player.prepare();
                player.start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // EventBus 传值必须放在一个类里才行
    private void playOrPause(){
        if(player.isPlaying()){
            pause();
            // 发送状态指令, 改变图标   在Activity中进行接收
            StateBean stateBean = new StateBean(StateBean.PLAY_PICTURE);
            EventBus.getDefault().post(stateBean);
        }else {
            play();
            StateBean stateBean = new StateBean(StateBean.PAUSE_PICTURE);
            EventBus.getDefault().post(stateBean);
        }

    }

//
//
////    暂停
    public void pause() {

        if (player != null && player.isPlaying()) {

            player.pause();
            isPause = true;
        }
    }
////    停止音乐
//    public void stop() {
//
//        if (player != null) {
//            player.stop();
//        }
//        try {
//            player.prepare();// 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    // 添加播放列表
//    public void addPlayList(MusicItem item) {
////       //判断列表中是否已经存储过该音乐，如果存储过就不管它
//    if(mPlayList.contains(item)) {
//        return;
//    }
//    //添加到播放列表的第一个位置
//    mPlayList.add(0, item);
//    //将音乐信息保存到数据库中
//    }
//     // 添加多个音乐
//      public void addPlayList(List<MusicItem> items) {
//
//      }
//
//    // 播放
    public void play() {

        player.start();

    }
//
//    // 播放下一首
//    public void playNext() {
//
//    }
//
//    // 播放上一首
//    public void playPre() {
//
//    }
//
//    //将当前音乐播放的进度，拖动到指定的位置；
//    public void seekTo(int pos) {
//
//    }
//
    public boolean isState() {
        return isPause;
    }
//
//    // 获取当前正在播放的音乐的信息；
//    public MusicItem getCurrentItem() {
//        return null;
//    }



}
