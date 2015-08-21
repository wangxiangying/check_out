package com.chinagpay.zhpaysdk.config;

/**
 * Created by test on 2015/5/25.
 */
public class BuildConfig {

    public static final String release_host = "http://pay.gozap.com/mobilepayment/mobilepay.action";
    public static final String dohko_host = "http://dohko.pay.gozap.com/mobilepayment/mobilepay.action";

    public static final String xiaoming="http://10.10.1.62:8080/";
    public static final String HOST = ENV.isRelease ? release_host
            : xiaoming;
}
