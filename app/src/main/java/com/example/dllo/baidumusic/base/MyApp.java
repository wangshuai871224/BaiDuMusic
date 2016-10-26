package com.example.dllo.baidumusic.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by WangShuai on 16/10/24.
 * !!!!!!写完MyApp 一定一定一定要注册!!!!
 *
 */
public class MyApp extends Application{
    // 不能new的对象, 就创建一个static对象,通过 对象点(a.)  调用

    // 所有跟界面无关的 都可以用这个全局的Context(数据库...等)   加载布局(layout)不可以
    private  static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getsContext() {
        return sContext;
    }

}
