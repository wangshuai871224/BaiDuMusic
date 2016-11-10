package com.example.dllo.baidumusic.listener;

import com.example.dllo.baidumusic.bean.MusicItem;

public interface OnStateChangeListenr {

    //用来通知播放进度
    void onPlayProgressChange(MusicItem item);
    //用来通知当前处于播放状态
    void onPlay(MusicItem item);
    //用来通知当前处于暂停或停止状态
    void onPause(MusicItem item);
}