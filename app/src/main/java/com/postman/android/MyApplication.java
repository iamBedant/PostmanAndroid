package com.postman.android;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

/**
 * Created by @iamBedant on 07/11/16.
 */

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();

    private static MyApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        mInstance = this;
    }


    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}