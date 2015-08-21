package com.chinagpay.zhpaysdk.config;

import android.app.Application;
import android.content.Context;

/**
 * Created by test on 2015/5/22.
 */
public class ZHPayApp extends Application {

    public static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
    }
}
