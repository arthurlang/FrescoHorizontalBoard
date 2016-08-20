package com.lj.horizontalrankboard.ui;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Description
 * Created by langjian on 2016/8/20.
 * Version
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
