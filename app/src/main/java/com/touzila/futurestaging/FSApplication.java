package com.touzila.futurestaging;

import android.app.Application;
import android.content.Context;

/**
 * Created by liu on 2017/12/19.
 */

public class FSApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
