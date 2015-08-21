package com.checkout.diancan.manager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.checkout.diancan.R;
import com.checkout.diancan.api.AllRequestApi;
import com.checkout.diancan.bean.ServiceParam;
import com.checkout.diancan.bean.TrsCreateOrderResp;
import com.checkout.diancan.tools.DecodeUtils;
import com.checkout.diancan.tools.EncodeUtils;
import com.checkout.diancan.tools.GsonUtils;
import com.checkout.diancan.tools.StringUtils;
import com.checkout.diancan.ui.CustomPayActivity;
import com.checkout.diancan.volley.Response;
import com.checkout.diancan.volley.VolleyError;
 import com.checkout.diancan.bean.TrsCreateOrderReq;
import com.checkout.diancan.config.LogUtils;
import com.checkout.diancan.ui.UnCardBinActivity;
import com.checkout.diancan.ui.ZHPayActivity;
import com.google.gson.Gson;

/**
 * 下单
 * Created by test on 2015/6/8.
 */
public class OrderManager {

    /**
     * 下单
     *
     * @param context
     * @param dialog
     * @param trs
     */
    public static void order(final Context context, final Dialog dialog, final TrsCreateOrderReq trs) {

        if (dialog != null) {
            dialog.show();
        }

        String encMs = EncodeUtils.getPostBody(trs);
        AllRequestApi.request(encMs, new Response.Listener() {

            @Override
            public void onResponse(Object response) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                try {
                    String data = DecodeUtils.decode(response.toString());
                    LogUtils.e("data:" + data);
                    Gson gson = new Gson();
                    TrsCreateOrderResp trsCreateOrderResp = gson.fromJson(data, TrsCreateOrderResp.class);
                    String sign = trsCreateOrderResp.getSign();
                    String[] str = GsonUtils.jsonToArray(trsCreateOrderResp);
                    if (str != null) {
                        str = StringUtils.StringSort(str);
                        // 将数组转为字符串
                        String signMsg = StringUtils.arrayToString(str, "&");
                        LogUtils.e(signMsg);
                        boolean very = DecodeUtils.verySign(signMsg, sign);
                        LogUtils.e("" + very);
                        if (very) {
                            if (ServiceParam.RetCode.REQUEST_SUCCESS_CODE.equals(trsCreateOrderResp.getRet_code())) {
                                Toast.makeText(context, R.string.order_success, Toast.LENGTH_SHORT).show();

//                                Intent intent = ServiceParam.retMethod(context, trsCreateOrderResp.getRet_method());
//                                intent.putExtra("data", trsCreateOrderResp);
//                                context.startActivity(intent);

                                Intent intent = new Intent();
                                if (ServiceParam.TransMode.NORMAL_MODE.equals(trs.getTrans_mode())) {
                                    intent.setClass(context, CustomPayActivity.class);
                                    intent.putExtra("data", trsCreateOrderResp);
                                } else if (ServiceParam.TransMode.SIMPLE_MODE.equals(trs.getTrans_mode())) {
                                    intent.setClass(context, ZHPayActivity.class);
                                    intent.putExtra("data", trsCreateOrderResp);
                                    intent.putExtra("isFromBusiness", true);
                                    intent.putExtra("name", trs.getAcct_name());
                                    intent.putExtra("idType", trs.getId_type());
                                    intent.putExtra("idNo", trs.getId_no());
                                    intent.putExtra("cardNo", trs.getCard_no());
                                    intent.putExtra("bankName", trs.getBank_name());
                                }
                                context.startActivity(intent);

                            } else if (ServiceParam.RetCode.UN_CARDBIN_CODE.equals(trsCreateOrderResp.getRet_code())) {
                                Intent intent = new Intent();
                                intent.setClass(context, UnCardBinActivity.class);
                                intent.putExtra("data", trsCreateOrderResp);
                                intent.putExtra("name", trs.getAcct_name());
                                intent.putExtra("idType", trs.getId_type());
                                intent.putExtra("idNo", trs.getId_no());
                                intent.putExtra("cardNo", trs.getCard_no());
                                context.startActivity(intent);
                            } else {
                                Toast.makeText(context, trsCreateOrderResp.getRet_msg(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, R.string.wrong_sign, Toast.LENGTH_SHORT).show();
                        }

                    }

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
                LogUtils.e("error" + error.getMessage());
            }
        });

    }

}
