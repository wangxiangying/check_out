package com.checkout.diancan.api;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.checkout.diancan.BaseActivity;
import com.checkout.diancan.R;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by test on 2015/6/2.
 */
public class TestActivity extends BaseActivity implements ApiReturnResultListener {
    //标准模式，绑卡模式
    private Button getkey, login,profile;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        api = new Api(this);
        api.setReturnResultListener(this);
        getkey = (Button) findViewById(R.id.getkey);
        getkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api.getKey(0);
            }
        });
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api.login(1);
            }
        });
        profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api.profile(2);
            }
        });



    }

    /**
     * 请求成功时调用此方法
     *
     * @param requestCode
     * @param apiResult
     */
    @Override
    public <T> void onReturnSucceedResult(int requestCode, ApiResult<T> apiResult) {
//        {
//            "code":"99999",
//                "data":{
//            "pub_key":
//            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJVZVqhBbShgTaryGRXNEskWWIZUvYYkUXt9FSyJEb0w9tUrjZv/VTLV3pX2pp7CrwRzFSYW3Wu1KCZnUgMTtxaIsHacwwl/t80yzc+bi37yprRaWEedeluUFVmbpWtmC0tnoZoZz5838aRQcSmc1YN4gHrVUtyYKGNNb7qn/yM7AgMBAAECgYAOAqeCeUDIPOCMXNHFnctZWeNMFr8Ayn9qWj2WQ/WKDgHZ1ZAfRkCz2CRdZRoUYqcQf8tIG3UCGah+kcq7xymxqmbmxL54LMqC3F/s/mAatu4pLFW6LzyQzscrkYbxp5pCp8ovt8VSaj2brqYyEbp+ZOGbOkghk60rahVoH5fu4QJBAOH10e3ljSKJLjAQ9GvWQAkqTTI0oUj9RyETLC3/I7IamWsSs4GUs3oxbWQlIUBNO6ZFouq2LnpuF3QitMteXR8CQQCpNC6bqNCsWht54Jv3CFAblWHjuseZ/tOveFYKHBIhtKPpCSzM/F7Z5fF1ITKA/DMxqu69Vbty5CPn7H5oiNplAkEAqhPuVKNQ7SrWUiWg5D5a6sslWeHVeGv0Cwg0W9bMVnXA/qAP7zwvL41eql9sPMslV8zYiNRh/ZM6IhcxgFXhvwJAZ1ovafc41z4G8HAo9EDIpcCeVXr+dur3pIPNkklJLftn720iR2eUDGbvEQuhDLaAEsOFJnNalzgRUpXbNMD7HQJANWxgEMbqlLbuE/wG8JLgUCV7S6AY8qx+nX97D4fBK4VtMVBMJiF00axdyGGPfaZViMC4sN3VYxcamrOOqNQJpA=="
//        }
//        }
        if (requestCode == 0) {

        } else if (requestCode == 1) {
            JSONObject jsonObject = apiResult.getJsonEntity();
            try {
                String token = jsonObject.getJSONObject("data").getString("access_token");
                Api.token = "111";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == 2) {

        }
    }

    /**
     * 请求失败或发生错误调用此方法
     *
     * @param requestCode
     * @param apiResult
     */
    @Override
    public <T> void onReturnFailResult(int requestCode, ApiResult<T> apiResult) {

    }

    /**
     * 加载进度
     *
     * @param requestCode
     */
    @Override
    public <T> void onReturnLoading(int requestCode) {

    }
}
