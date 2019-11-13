package com.lottery.app.helperPackages.baseClasses;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDexApplication;

public class LotteryApplication extends MultiDexApplication {
    private static Context mContext;
    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        LotteryApplication.mContext = getApplicationContext();
        mApplication = this;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Application getApplication() {
        return mApplication;
    }
}
