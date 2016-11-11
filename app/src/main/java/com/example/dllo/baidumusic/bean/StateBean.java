package com.example.dllo.baidumusic.bean;

/**
 * Created by dllo on 16/11/10.
 */
public class StateBean {
    public static final int PLAY_PICTURE = 0;
    public static final int PAUSE_PICTURE = 1;
    private int state;

    public StateBean(int state) {
        this.state = state;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
