package com.android.core;

import android.app.Application;
import android.content.Context;

import com.android.core.control.logcat.Logcat;
public class MainApp extends Application {

    private static MainApp ourInstance = new MainApp();
    private static Context mContext;

    public static MainApp getInstance() {
        return ourInstance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        mContext = getApplicationContext();
        Logcat.init("com.android.core").hideThreadInfo().methodCount(3);
    }
}
