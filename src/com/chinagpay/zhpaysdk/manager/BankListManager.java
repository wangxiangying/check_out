package com.chinagpay.zhpaysdk.manager;

import android.app.Dialog;
import android.content.Context;
import com.chinagpay.zhpaysdk.api.AllRequestApi;
import com.chinagpay.zhpaysdk.bean.ServiceParam;
import com.chinagpay.zhpaysdk.bean.TrsQueryBankListReq;
import com.chinagpay.zhpaysdk.bean.TrsQueryBankListResp;
import com.chinagpay.zhpaysdk.config.LogUtils;
import com.chinagpay.zhpaysdk.tools.DecodeUtils;
import com.chinagpay.zhpaysdk.tools.EncodeUtils;
import com.chinagpay.zhpaysdk.volley.Response;
import com.chinagpay.zhpaysdk.volley.VolleyError;
import com.google.gson.Gson;

/**
 * 银行卡列表
 * Created by test on 2015/6/9.
 */
public class BankListManager {

    public interface OnSuccessListener {
        public void success(TrsQueryBankListResp trsQueryBankListResp);
    }

    /**
     * 获取银行卡列表
     *
     * @param context
     * @param dialog
     */
    public static void getBankList(final Context context, final Dialog dialog, final OnSuccessListener listener) {

        if (dialog != null) {
            dialog.show();
        }
        TrsQueryBankListReq trs = new TrsQueryBankListReq();
        trs.setService(ServiceParam.service);
        trs.setService_version(ServiceParam.service_version);
        trs.setInput_charset(ServiceParam.input_charset);
        trs.setSign_type(ServiceParam.sign_type);
        trs.setPartner(ServiceParam.partner);
        trs.setTrans_mode(ServiceParam.TransMode.QUERY_BANK_LIST);
        try {
            String sign = EncodeUtils.sign(trs);
            trs.setSign(sign);
            String body = EncodeUtils.getPostBody(trs);
            AllRequestApi.request(body, new Response.Listener() {
                @Override
                public void onResponse(Object response) {

                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    try {
                        String data = DecodeUtils.decode(response.toString());
                        LogUtils.e("data:" + data);
                        Gson gson = new Gson();
                        TrsQueryBankListResp trsQueryBankListResp = gson.fromJson(data, TrsQueryBankListResp.class);
                        listener.success(trsQueryBankListResp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
