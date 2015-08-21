package com.chinagpay.diancan.api;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.chinagpay.diancan.config.BuildConfig;
import com.chinagpay.diancan.net.VolleyHttpRequest;
import com.chinagpay.diancan.tools.StringUtils;
import com.chinagpay.diancan.volley.*;
import com.chinagpay.diancan.volley.toolbox.StringRequest;
import com.chinagpay.zhpaysdk.R;
import com.chinagpay.diancan.config.ENV;
import com.chinagpay.diancan.config.ZHPayApp;
import com.chinagpay.diancan.volley.toolbox.Volley;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 所有请求api
 * Created by test on 2015/6/1.
 */
public class AllRequestApi {

    /**
     * 根据请求参数类型不同，访问的请求不同
     *
     * @param body          post请求body内容
     * @param listener      成功监听
     * @param errorListener 失败监听
     */
    public static void request(String body, Response.Listener listener, Response.ErrorListener errorListener) {

        if (StringUtils.isNotNullOrEmpty(body)) {
            VolleyHttpRequest.httpPostRequest(BuildConfig.HOST, body, listener, errorListener);
        } else {
            Toast.makeText(ZHPayApp.mApplicationContext, R.string.wrong_data, Toast.LENGTH_SHORT).show();
        }

    }

    public void request(final int requestCode, String url,
                        final ApiReturnResultListener dataCallback,
                        final List<NameValuePair> params) {
        Map<String, String> thisMap = new HashMap<String, String>();
        if (params != null) {
            for (NameValuePair nv : params) {
                thisMap.put(nv.getName(), nv.getValue());
            }
        }
        request(requestCode, url, dataCallback, thisMap);
    }

    private static final RequestQueue mQueue = Volley
            .newRequestQueue(ZHPayApp.mApplicationContext);
    private static final String TAG = "NetVolley";
    private ApiReturnResultListener dataCallback;
    private String url;

    public void request(final int requestCode, String url,
                        final ApiReturnResultListener dataCallback,
                        final Map<String, String> thisMap) {
        this.dataCallback = dataCallback;
        this.url = url;
        final ApiResult<JSONObject> result = new ApiResult<JSONObject>();
//        final Map<String, String> maps = new HashMap<String, String>();
//        if (thisMap != null) {
//            result.setEntity(thisMap);
//            Iterator iterator = thisMap.keySet().iterator();
//            while (iterator.hasNext()) {
//                String key = (String) iterator.next();
//                String value = thisMap.get(key);
//                try {
//                    if ("access_token".equals(key)) {
//                        maps.put(key, value);
//                    } else {
//                        maps.put(key, Des3.encode(value));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        try {
//            maps.put("t", "" + Des3.encode(System.currentTimeMillis() + ""));
//            if (!(url.equals(UrlFactory.GetLoginUrl()) || url.equals(UrlFactory
//                    .GetRegeditUrl()))) {
//                maps.put("platform", Des3.encode("A"));
//                maps.put("version", Des3.encode(Utils.getVersionInfo()));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Response.Listener<String> listenner = new Response.Listener<String>() {
            int thisRequestCode = requestCode;

            @Override
            public void onResponse(String temp) {
                JSONObject jsonObject = null;
                int code = 0;
                try {
                    jsonObject = new JSONObject(temp);
                    if (jsonObject != null) {
                        code = jsonObject.getInt("code");
                        if (code == 99999) {
                            result.setResultCode(HttpResult.RESULT_OK);
                            result.setJsonEntity(jsonObject);
                        } else {
                            result.setResultCode(HttpResult.RESULT_FAIL);
                            result.setFailCode(jsonObject.getInt("code"));
                            if (!jsonObject.isNull("data")) {
                                result.setJsonEntity(jsonObject);
                            }
                            if (!jsonObject.isNull("message")) {
                                result.setFailMessage(jsonObject
                                        .getString("message"));
                            }
                        }
                        doCallBack(thisRequestCode, result, temp);
                    } else {
                        result.setResultCode(HttpResult.RESULT_FAIL);
                        result.setFailMessage("ParseError");
                        dataCallback.onReturnFailResult(thisRequestCode,
                                result);
                        doCallBack(thisRequestCode, result);
                    }
                } catch (JSONException e) {
                    result.setResultCode(HttpResult.RESULT_FAIL);
                    result.setFailMessage("ParseError");
                    doCallBack(thisRequestCode, result);
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            int thisRequestCode = requestCode;

            @Override
            public void onErrorResponse(VolleyError error) {


                result.setResultCode(ApiResult.RESULT_FAIL);
                result.setFailCode(-9999);
                if (error instanceof ServerError) {
                    result.setFailCode(-9998);
                    result.setFailMessage(ZHPayApp.mApplicationContext.getResources().getString(R.string.ServerError));
                } else if (error instanceof TimeoutError) {
                    result.setFailCode(-9997);
                    result.setFailMessage(ZHPayApp.mApplicationContext.getResources().getString(R.string.TimeoutError));
                } else if (error instanceof ParseError) {
                    result.setFailCode(-9996);
                    result.setFailMessage(ZHPayApp.mApplicationContext.getResources().getString(R.string.ParseError));
                } else if (error instanceof NoConnectionError) {
                    result.setFailCode(-9995);
                    result.setFailMessage(ZHPayApp.mApplicationContext.getResources().getString(R.string.NoConnectionError));
                } else if (error instanceof NetworkError) {
                    result.setFailCode(-9994);
                    result.setFailMessage(ZHPayApp.mApplicationContext.getResources().getString(R.string.not_network));
                } else if (error instanceof AuthFailureError) {
                    result.setFailCode(-9993);
                    result.setFailMessage(ZHPayApp.mApplicationContext.getResources().getString(R.string.AuthFailureError));
                }

                doCallBack(thisRequestCode, result);
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, listenner, errorListener) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                return thisMap;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("access_token", Api.token);
                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mQueue.add(stringRequest);
    }

    private void doCallBack(int thisRequestCode, ApiResult result, String json) {
        {
            if (result.getResultCode() == ApiResult.RESULT_OK) {
                if (dataCallback != null) {
                    dataCallback.onReturnSucceedResult(thisRequestCode,
                            result);
                }

                if (ENV.isLog) {

                    Log.e(TAG,
                            "START ---- " + url + " ---- requestCode = " + thisRequestCode + " --- START");

                    if (result.getEntity() != null) {
                        Iterator iterator = result.getEntity().keySet().iterator();
                        while (iterator.hasNext()) {
                            String key = (String) iterator.next();
                            String value = (String) result.getEntity().get(key);
                            Log.e(TAG, "<" + key + ">   = " + value);
                        }
                    }


                    Log.e(TAG,
                            "BACK ---- " + url + " ---- requestCode = " + thisRequestCode + " --- BACK");
                    Log.e(TAG, "============================   Json   ===============================");

                    if (!TextUtils.isEmpty(json)) {
                        new JsonFormatTool().formatJsonPrint(TAG, json);
                    }
                    Log.e(TAG,
                            "END ---- " + url + " ---- requestCode = " + thisRequestCode + " --- END");
                }
            } else {
                if (dataCallback != null) {
                    dataCallback.onReturnFailResult(thisRequestCode, result);
                }
                Log.e(TAG,
                        "START ---- " + url + " ---- requestCode = " + thisRequestCode + " --- START");
                if (result.getEntity() != null) {
                    Iterator iterator = result.getEntity().keySet().iterator();
                    while (iterator.hasNext()) {
                        String key = (String) iterator.next();
                        String value = (String) result.getEntity().get(key);
                        if (ENV.isLog) {
                            Log.e(TAG, "<" + key + ">   = " + value);
                        }
                    }
                }
                if (ENV.isLog) {
                    Log.e(TAG, "backCode = " + thisRequestCode);
                    Log.e(TAG, "============================      Error      ===============================");
                    Log.e(TAG, "FailCode = " + result.getFailCode());
                    Log.e(TAG, "FailMessage = " + result.getFailMessage());
                    Log.e(TAG, "============================      Json      ===============================");

                    if (!TextUtils.isEmpty(json)) {
                        new JsonFormatTool().formatJsonPrint(TAG, json);
                    }
                    Log.e(TAG,
                            "END ---- " + url + " ---- END");

                }

            }
        }

    }

    private void doCallBack(int thisRequestCode, ApiResult result) {
        doCallBack(thisRequestCode, result, null);
    }


}
