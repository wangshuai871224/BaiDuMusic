package com.example.dllo.baidumusic.tools;

/**
 * Created by dllo on 16/10/25.
 */
public final class URLValues {

    // 写个数组
    public static String[] SONG_MENU_URL = {"http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=huwei&operator=0&method=baidu.ting.ugcdiy.getChanneldiy&param=zAR3AtUclOQ0EXJqn2NjoTsvlF2fNfoJ%2BA328%2Fjq24dX8vwkHVTOtCDtHfbTu6jByVf1Jzg0o3XAKo6r8MdNaEmTVsm4c36AC8f%2FbdwzSl2fJiQQ6m82IIVHdAojOc55&timestamp=1477530674&sign=b1bebdbb76b2088e511b3b16994826d6"
                                            ,"http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=huwei&operator=0&method=baidu.ting.ugcdiy.getChanneldiy&param=%2BfwD%2Bm6kZaiFK%2FC6sMtp88ATDWJgxJg%2FzhI2gF3gIbCL9v6bRIyTgCfU4BsiZkF5iMawTGK8iU1rHQyf16ZH7prB1RTTCk43PcxOmXCmEhwlM4TqkOuZgbKIB9f0IkhQ&timestamp=1478073791&sign=dc99f3eb277c5e78eb76b7def220dcd9"
                                           };
    public static final String MUSIC_PLAYLIST_LIST_FRONT = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedanInfo&from=ios&listid=";
    public static final String MUSIC_PLAYLIST_LIST_BEHIND = "&version=5.2.3&from=ios&channel=appstore";

    public static final String MUSIC_PLAY_FRONT = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";

    public static final String MUSIC_PLAY_BEHIND = "&_=1413017198449";

    public static final String LIVE_MORE_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=huwei&operator=0&method=baidu.ting.show.live&page_no=1&page_size=40";

    public static final String LIST_URL= "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=huwei&operator=0&method=baidu.ting.billboard.billCategory&format=json&kflag=2";

    public static final String RECOMMEND_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1382d&operator=0&method=baidu.ting.plaza.index&cuid=90AE6B089CD064D03DDF18681495AC77";
}
