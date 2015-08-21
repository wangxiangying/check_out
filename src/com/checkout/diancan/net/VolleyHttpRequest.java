package com.checkout.diancan.net;

import com.checkout.diancan.config.ZHPayApp;
import com.checkout.diancan.volley.AuthFailureError;
import com.checkout.diancan.volley.Request;
import com.checkout.diancan.volley.RequestQueue;
import com.checkout.diancan.volley.Response;
import com.checkout.diancan.volley.toolbox.JsonArrayRequest;
import com.checkout.diancan.volley.toolbox.JsonObjectRequest;
import com.checkout.diancan.volley.toolbox.StringRequest;
import com.checkout.diancan.volley.toolbox.Volley;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by test on 2015/5/22.
 */
public class VolleyHttpRequest {

    //    private static Test test = new Test();
//    public static Test getInstance() {
//        return test;
//    }
    public static RequestQueue mQueue = Volley.newRequestQueue(ZHPayApp.mApplicationContext);

    /**
     * http get请求
     *
     * @param url           请求地址
     * @param listener      成功监听
     * @param errorListener 失败监听
     */
    public static void httpGetRequest(String url, Response.Listener listener, Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(url, listener, errorListener);
        mQueue.add(stringRequest);
    }


    /**
     * http post请求
     *
     * @param url           请求地址
     * @param listener      成功监听
     * @param errorListener 失败监听
     */
    public static void httpPostRequest(String url, final String body, Response.Listener listener, Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return body.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.getBody();
            }
        };
        mQueue.add(stringRequest);
    }


    /**
     * http get请求 请求参数为JSONObject类型
     *
     * @param url           请求地址
     * @param json          请求参数JSONObject类型
     * @param listener      成功监听
     * @param errorListener 失败监听
     */
    public static void httpGetRequestJSONObject(String url, JSONObject json, Response.Listener listener, Response.ErrorListener errorListener) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, json, listener, errorListener);
        mQueue.add(jsonObjectRequest);
    }

    /**
     * http post请求 传递参数为JSONObject类型
     *
     * @param url           请求地址
     * @param json          传递参数JSONObject类型
     * @param listener      成功监听
     * @param errorListener 失败监听
     */
    public static void httpPostRequestJSONObject(String url, JSONObject json, Response.Listener listener, Response.ErrorListener errorListener) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json, listener, errorListener);
        mQueue.add(jsonObjectRequest);
    }

    /**
     * http get请求 请求地址携带json
     *
     * @param url           请求地址
     * @param listener      成功监听
     * @param errorListener 失败监听
     */
    public static void httpGetRequestJSONArray(String url, Response.Listener listener, Response.ErrorListener errorListener) {

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url, listener, errorListener);
        mQueue.add(jsonObjectRequest);
    }
}

