package com.chinagpay.diancan.api;

import android.content.Context;

import java.util.HashMap;

public class Api extends BaseApi {

    public Api(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public static String key = "";
    public static String token="";
    public void login(final int requestCode) {
        HashMap<String, String> params = new HashMap<String, String>();
//        user/login.do?phone=15110088376&password=123456
        try {
//            params.put("phone",  RSA_Encrypt.encrypt("15110088376", key));
//            params.put("password", RSA_Encrypt.encrypt("123456", key));
            params.put("phone", "15110088376");
            params.put("password", "123456");


        } catch (Exception e) {
            e.printStackTrace();
        }

        new AllRequestApi().request(requestCode, UrlFactory.lgoin(), getReturnResultListener(), params);
    }

    public void getKey(final int requestCode) {
        HashMap<String, String> params = new HashMap<String, String>();
        new AllRequestApi().request(requestCode, UrlFactory.get(), getReturnResultListener(), params);
//        user/login.do?phone=15110088376&password=123456
    }

    public void profile(final int requestCode)
    {
        HashMap<String, String> params = new HashMap<String, String>();
        new AllRequestApi().request(requestCode, UrlFactory.profile(), getReturnResultListener(), params);

    }

}
