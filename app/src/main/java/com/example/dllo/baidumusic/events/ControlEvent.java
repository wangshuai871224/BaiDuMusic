package com.example.dllo.baidumusic.events;

/**
 * Created by dllo on 16/11/11.
 */
public class ControlEvent {
    public static final int PLAY_OR_STOP = 0;
    public static final int PLAY_NEXT = 1;
    public static final int PLAY_PRE = 2;
    public static final int CHANGE_MODE = 3;//改变 随机播放,列表循环,单曲循环
//    public static final int STOP = 4;


    private int controlKey;

    public ControlEvent(int controlKey) {
        this.controlKey = controlKey;
    }

    public int getControlKey() {
        return controlKey;
    }

    public void setControlKey(int controlKey) {
        this.controlKey = controlKey;
    }





}
