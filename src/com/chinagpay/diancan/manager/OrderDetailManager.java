package com.chinagpay.diancan.manager;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.chinagpay.diancan.api.AllRequestApi;
import com.chinagpay.diancan.bean.ServiceParam;
import com.chinagpay.diancan.bean.TrsQueryOrderReq;
import com.chinagpay.diancan.bean.TrsQueryOrderResp;
import com.chinagpay.diancan.config.LogUtils;
import com.chinagpay.diancan.tools.DecodeUtils;
import com.chinagpay.diancan.tools.EncodeUtils;
import com.chinagpay.diancan.tools.GsonUtils;
import com.chinagpay.diancan.tools.StringUtils;
import com.chinagpay.diancan.volley.Response;
import com.chinagpay.diancan.volley.VolleyError;
import com.chinagpay.zhpaysdk.R;
import com.google.gson.Gson;

/**
 * 查看订单详情
 * Created by test on 2015/6/8.
 */
public class OrderDetailManager {

    /**
     * 订单详情
     *
     * @param context
     * @param dialog
     * @param out_trade_no 订单号
     */
    public static void orderDetail(final Context context, final Dialog dialog, String out_trade_no) {

        if (dialog != null) {
            dialog.show();
        }
        TrsQueryOrderReq trs = new TrsQueryOrderReq();
        trs.setService(ServiceParam.service);
        trs.setService_version(ServiceParam.service_version);
        trs.setInput_charset(ServiceParam.input_charset);
        trs.setSign_type(ServiceParam.sign_type);
        trs.setTrans_mode(ServiceParam.TransMode.QUERY_ORDER_INFO);
        trs.setPartner(ServiceParam.partner);
        trs.setOut_trade_no(out_trade_no);

        try {

            String sign = EncodeUtils.sign(trs);
            trs.setSign(sign);
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
                        TrsQueryOrderResp trsQueryOrderResp = gson.fromJson(data, TrsQueryOrderResp.class);
                        String sign = trsQueryOrderResp.getSign();
                        String[] str = GsonUtils.jsonToArray(trsQueryOrderResp);
                        if (str != null) {
                            str = StringUtils.StringSort(str);
                            // 将数组转为字符串
                            String signMsg = StringUtils.arrayToString(str, "&");
                            LogUtils.e(signMsg);
                            boolean very = DecodeUtils.verySign(signMsg, sign);
                            LogUtils.e("" + very);
                            if (very) {
                                if (ServiceParam.RetCode.REQUEST_SUCCESS_CODE.equals(trsQueryOrderResp.getRet_code())
                                        || StringUtils.isNullOrEmpty(trsQueryOrderResp.getRet_code())) {
                                    orderDetailDialog(context, trsQueryOrderResp);
                                } else {
                                    Toast.makeText(context, trsQueryOrderResp.getRet_msg(), Toast.LENGTH_SHORT).show();
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
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Dialog dialog;

    private static void orderDetailDialog(Context context, TrsQueryOrderResp trsQueryOrderResp) {
        if (dialog == null) {
            View view = LayoutInflater.from(context).inflate(
                    R.layout.order_detail_dialog, null);
            TextView beneficiary = (TextView) view.findViewById(R.id.beneficiary);
            TextView productName = (TextView) view.findViewById(R.id.productName);
            TextView payAmount = (TextView) view.findViewById(R.id.payAmount);
            if (trsQueryOrderResp != null) {
                beneficiary.setText(context.getString(R.string.Beneficiary) + trsQueryOrderResp.getPartner_name());
                productName.setText(context.getString(R.string.product_name));
                payAmount.setText(context.getString(R.string.pay_amount) + context.getString(R.string.RMB) + trsQueryOrderResp.getTotal_fee());
            }
            ImageView close = (ImageView) view.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });
            dialog = new Dialog(context, R.style.MyDialog);
            dialog.setContentView(view);
        }
        dialog.show();
    }
}
