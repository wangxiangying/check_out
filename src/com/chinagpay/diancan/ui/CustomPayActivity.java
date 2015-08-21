package com.chinagpay.diancan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.chinagpay.diancan.BaseActivity;
import com.chinagpay.zhpaysdk.R;
import com.chinagpay.diancan.api.AllRequestApi;
import com.chinagpay.diancan.bean.ServiceParam;
import com.chinagpay.diancan.bean.TrsCreateOrderResp;
import com.chinagpay.diancan.bean.TrsQueryCardTypeReq;
import com.chinagpay.diancan.bean.TrsQueryCardTypeResp;
import com.chinagpay.diancan.config.LogUtils;
import com.chinagpay.diancan.manager.Const;
import com.chinagpay.diancan.manager.OrderDetailManager;
import com.chinagpay.diancan.manager.SelectIdTypeManager;
import com.chinagpay.diancan.tools.DecodeUtils;
import com.chinagpay.diancan.tools.EncodeUtils;
import com.chinagpay.diancan.tools.GsonUtils;
import com.chinagpay.diancan.tools.StringUtils;
import com.chinagpay.diancan.ui.framework.pay.CustomPayLayout;
import com.chinagpay.diancan.ui.widget.InfoItemView;
import com.chinagpay.diancan.volley.Response;
import com.chinagpay.diancan.volley.VolleyError;
import com.google.gson.Gson;

/**
 * Created by test on 2015/5/18.
 */
public class CustomPayActivity extends BaseActivity {

    //    private ImageView back, more;
//    private TextView center_text, amount, custom_order_detail, beneficiary;
//    private RelativeLayout amount_bottom, agree_layout;
//    private LinearLayout amount_benefit, ID_BANK_INFO_LAYOUT, infos, layoutTitle;
    private InfoItemView cardhodler, id, card_number;
    //    private Button next;
    private boolean isCardHolderNotNull, isIDNotNull, isCardNumberNotNull;
    private TrsCreateOrderResp trsCreateOrderResp;
    private String idTYpe = "01";

    CustomPayLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = new CustomPayLayout(this);
        setContentView(main);
        Const.activityList.add(this);
        trsCreateOrderResp = (TrsCreateOrderResp) getIntent().getSerializableExtra("data");
        initLayout();
        initData();
    }

    private void initLayout() {


        main.title.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        main.title.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        back = (ImageView) findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                back();
//            }
//        });
//        more = (ImageView) findViewById(R.id.more);
//        more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        center_text = (TextView) findViewById(R.id.center_text);
        if (StringUtils.isNotNullOrEmpty(ServiceParam.CustomTitle)) {
            main.title.center_text.setText(ServiceParam.CustomTitle);
        }


//        layout = (LinearLayout) findViewById(R.id.layout);
        main.layout.setFocusable(true);
        main.layout.setFocusableInTouchMode(true);
        main.layout.requestFocus();

//        main.amount_layout.amount = (TextView) findViewById(R.id.amount);

//        custom_order_detail = (TextView) findViewById(R.id.custom_order_detail);
        main.amount_layout.custom_order_detail.setVisibility(View.VISIBLE);
        main.amount_layout.custom_order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderDetailManager.orderDetail(CustomPayActivity.this, initDialog(getString(R.string.loading)), trsCreateOrderResp.getOut_trade_no());
            }
        });
//        beneficiary = (TextView) findViewById(R.id.beneficiary);
//        main.amount_layout.amount_bottom = (RelativeLayout) findViewById(R.id.amount_bottom);
        main.amount_layout.amount_bottom.setVisibility(View.GONE);
//        amount_benefit = (LinearLayout) findViewById(R.id.amount_benefit);
        main.amount_layout.amount_benefit.setVisibility(View.VISIBLE);
//        ID_BANK_INFO_LAYOUT = (LinearLayout) findViewById(R.id.ID_BANK_INFO_LAYOUT);
        main.id_bank_info_layout.setVisibility(View.GONE);
//        agree_layout = (RelativeLayout) findViewById(R.id.agree_layout);
        main.agree_layout.setVisibility(View.GONE);
//        infos = (LinearLayout) findViewById(R.id.infos);
        initInfos();
//        next = (Button) findViewById(R.id.next);
        setNextButtonEnable(false);
        main.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TrsQueryCardTypeReq trs = new TrsQueryCardTypeReq();
                trs.setService(ServiceParam.service);
                trs.setService_version(ServiceParam.service_version);
                trs.setInput_charset(ServiceParam.input_charset);
                trs.setSign_type(ServiceParam.sign_type);
                trs.setTrans_mode(ServiceParam.TransMode.QUERY_CARD_INFO);
                trs.setPartner(ServiceParam.partner);
                trs.setCard_no(card_number.info_edit.getText().toString());
                queryCardType(trs);
            }
        });
    }

    private void initInfos() {

        cardhodler = new InfoItemView(this);
        cardhodler.setInfo_title(getString(R.string.cardholder));
        cardhodler.setCenterLayout(InfoItemView.INFO_EDIT_TYPE);
        cardhodler.setInfo_editHintText(getString(R.string.input_cardholder));
        cardhodler.info_edit.requestFocus();
        cardhodler.info_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (StringUtils.isNotNullOrEmpty(s.toString())) {
                    isCardHolderNotNull = true;
                    freshNextButtonStatus();
                } else {
                    isCardHolderNotNull = false;
                    setNextButtonEnable(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        main.infos.addView(cardhodler);

        id = new InfoItemView(this);
        id.setInfo_title(getString(R.string.ID));
        id.setCenterLayout(InfoItemView.INFO_EDIT_TYPE);
        id.setInfo_editHintText(getString(R.string.input_ID));
        id.setRightLayout(InfoItemView.RIGHT_SELECT);
        id.right_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectIdTypeManager.initSelectDilag(CustomPayActivity.this, new SelectIdTypeManager.MyOnClickListener() {

                    @Override
                    public void onclick(String type) {
                        idTYpe = ServiceParam.IDType.getKeyFromValue(type);
                        type = ServiceParam.IDType.getTypeSmallNameFromKey(idTYpe);
                        id.setInfo_title(type + "：");
                        id.setSelect_text(type);
                    }
                });
            }
        });
        id.info_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (StringUtils.isNotNullOrEmpty(s.toString())) {
                    isIDNotNull = true;
                    freshNextButtonStatus();
                } else {
                    isIDNotNull = false;
                    setNextButtonEnable(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        main.infos.addView(id);

        card_number = new InfoItemView(this);
        card_number.setInfo_title(getString(R.string.card_number));
        card_number.setCenterLayout(InfoItemView.INFO_EDIT_TYPE);
        card_number.setInfo_editHintText(getString(R.string.input_card_number));
        card_number.info_edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        card_number.info_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (StringUtils.isNotNullOrEmpty(s.toString())) {
                    isCardNumberNotNull = true;
                    freshNextButtonStatus();
                } else {
                    isCardNumberNotNull = false;
                    setNextButtonEnable(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        main.infos.addView(card_number);
    }

    /**
     * 刷新next按钮状态
     */
    public void freshNextButtonStatus() {
        if (isCardHolderNotNull && isIDNotNull && isCardNumberNotNull) {
            setNextButtonEnable(true);
        } else {
            setNextButtonEnable(false);
        }
    }

    /**
     * 设置next按钮状态及背景颜色
     *
     * @param enable
     */
    public void setNextButtonEnable(boolean enable) {
        main.next.setEnabled(enable);
        if (enable) {
            main.next.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_round));
        } else {
            main.next.setBackgroundDrawable(getResources().getDrawable(R.drawable.grey_button_round));
        }
    }

    private void initData() {

        if (trsCreateOrderResp != null) {
            main.amount_layout.amount.setText(getString(R.string.RMB) + trsCreateOrderResp.getTotal_fee());
            main.amount_layout.beneficiary.setText(getString(R.string.Beneficiary) + trsCreateOrderResp.getPartner_name());
        }
    }

    /**
     * 查询卡类型
     *
     * @param trs
     */
    public void queryCardType(TrsQueryCardTypeReq trs) {

        try {
            String sign = EncodeUtils.sign(trs);
            trs.setSign(sign);
            String enmsg = EncodeUtils.getPostBody(trs);
            initDialog(getString(R.string.loading));
            dialogShow();
            AllRequestApi.request(enmsg, new Response.Listener() {

                @Override
                public void onResponse(Object response) {
                    dialogDismiss();
                    try {
                        String data = DecodeUtils.decode(response.toString());
                        LogUtils.e("data:" + data);
                        Gson gson = new Gson();
                        TrsQueryCardTypeResp trsQueryCardTypeResp = gson.fromJson(data, TrsQueryCardTypeResp.class);
                        String sign = trsQueryCardTypeResp.getSign();
                        String[] str = GsonUtils.jsonToArray(trsQueryCardTypeResp);
                        if (str != null) {
                            str = StringUtils.StringSort(str);
                            // 将数组转为字符串
                            String signMsg = StringUtils.arrayToString(str, "&");
                            LogUtils.e(signMsg);
                            boolean very = DecodeUtils.verySign(signMsg, sign);
                            LogUtils.e("" + very);
                            if (very) {
                                if (ServiceParam.RetCode.REQUEST_SUCCESS_CODE.equals(trsQueryCardTypeResp.getRet_code())) {
                                    Intent intent = ServiceParam.retMethod(CustomPayActivity.this, trsQueryCardTypeResp.getRet_method());
                                    intent.putExtra("data", trsQueryCardTypeResp);
                                    //姓名
                                    intent.putExtra("name", cardhodler.getEditText());
                                    //卡类型
                                    intent.putExtra("idType", idTYpe);
                                    //卡号
                                    intent.putExtra("idNo", id.getEditText());
                                    //支付金额
                                    intent.putExtra("amount", trsCreateOrderResp.getTotal_fee());
                                    //订单号
                                    intent.putExtra("out_trade_no", trsCreateOrderResp.getOut_trade_no());
                                    startActivity(intent);
                                } else {
                                    Intent intent = ServiceParam.retMethod(CustomPayActivity.this, trsQueryCardTypeResp.getRet_method());
                                    intent.putExtra("data", trsCreateOrderResp);
                                    intent.putExtra("name", cardhodler.getEditText());
                                    intent.putExtra("idType", idTYpe);
                                    intent.putExtra("idNo", id.getEditText());
                                    intent.putExtra("cardNo", trsQueryCardTypeResp.getCard_no());
                                    startActivity(intent);
                                }
                            } else {
                                Toast.makeText(CustomPayActivity.this, R.string.wrong_sign, Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    dialogDismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Const.activityList.remove(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        back();
        return super.onKeyDown(keyCode, event);
    }
}
