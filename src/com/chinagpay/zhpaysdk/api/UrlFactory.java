package com.chinagpay.zhpaysdk.api;

import com.chinagpay.zhpaysdk.config.BuildConfig;

/**
 * Created by Administrator on 2015/8/15.
 */
public class UrlFactory {


    public static String lgoin() {

        return BuildConfig.HOST+"user/login";
    }

    public static String get()
    {
        return BuildConfig.HOST+"global/getKey";
    }

    public static String getAddress()
    {
        return  BuildConfig.HOST+"user/get/address";
    }

    public  static  String profile()
    {
        return  BuildConfig.HOST+"user/profile";
    }
}
