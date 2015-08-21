package com.chinagpay.zhpaysdk.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.*;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.chinagpay.zhpaysdk.BaseActivity;
import com.chinagpay.zhpaysdk.R;
import com.chinagpay.zhpaysdk.api.AllRequestApi;
import com.chinagpay.zhpaysdk.bean.*;
import com.chinagpay.zhpaysdk.config.LogUtils;
import com.chinagpay.zhpaysdk.manager.Const;
import com.chinagpay.zhpaysdk.manager.OrderDetailManager;
import com.chinagpay.zhpaysdk.tools.DecodeUtils;
import com.chinagpay.zhpaysdk.tools.EncodeUtils;
import com.chinagpay.zhpaysdk.tools.GsonUtils;
import com.chinagpay.zhpaysdk.tools.StringUtils;
import com.chinagpay.zhpaysdk.ui.widget.IdCodeInfoItemView;
import com.chinagpay.zhpaysdk.ui.widget.InfoItemView;
import com.chinagpay.zhpaysdk.volley.Response;
import com.chinagpay.zhpaysdk.volley.VolleyError;
import com.google.gson.Gson;

/**
 * Created by test on 2015/5/18.
 */
public class ZHPayActivity extends BaseActivity {

    private ImageView back, more;
    private LinearLayout layout;
    private IdCodeInfoItemView idcode_item;
    private InfoItemView validity, cvv, phone, security_code;
    private TextView amount_textView, order_detail, bankCard;
    private LinearLayout infos;
    private TextView agree_pay_tips;
    private Button next;
    private boolean isValidityNotNull, isCVVNotNull, isPhoneNotNull, isSecurityCodeNotNull;
    private boolean isFromBusiness;
    private TrsQueryCardTypeResp trsQueryCardTypeResp;
    private TrsCreateOrderResp trsCreateOrderResp;
    private String name, idType, idNo, amount, out_trade_no, cardNo, bankName, cardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);
        Const.activityList.add(this);
        isFromBusiness = getIntent().getBooleanExtra("isFromBusiness", false);
        if (isFromBusiness) {
            trsCreateOrderResp = (TrsCreateOrderResp) getIntent().getSerializableExtra("data");
            amount = trsCreateOrderResp.getTotal_fee();
            out_trade_no = trsCreateOrderResp.getOut_trade_no();
            cardNo = getIntent().getStringExtra("cardNo");
            bankName = getIntent().getStringExtra("bankName");
        } else {
            trsQueryCardTypeResp = (TrsQueryCardTypeResp) getIntent().getSerializableExtra("data");
            amount = getIntent().getStringExtra("amount");
            out_trade_no = getIntent().getStringExtra("out_trade_no");
            cardNo = trsQueryCardTypeResp.getCard_no();
            bankName = trsQueryCardTypeResp.getBank_name();
        }

        name = getIntent().getStringExtra("name");
        idType = getIntent().getStringExtra("idType");
        idNo = getIntent().getStringExtra("idNo");

        cardType = getIntent().getStringExtra("cardType");
        if (StringUtils.isNullOrEmpty(cardType)) {
            cardType = ServiceParam.CardType.CREDIT;
        }
        initLayout();
        initData();
    }

    /**
     * 初始化控件
     */
    private void initLayout() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
//        more = (ImageView) findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layout = (LinearLayout) findViewById(R.id.layout);
        layout.setBackgroundColor(getResources().getColor(R.color.background));
        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);
        layout.requestFocus();
        amount_textView = (TextView) findViewById(R.id.amount);
        order_detail = (TextView) findViewById(R.id.order_detail);
        order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderDetailManager.orderDetail(ZHPayActivity.this, initDialog(getString(R.string.loading)), out_trade_no);
            }
        });
        idcode_item = (IdCodeInfoItemView) findViewById(R.id.idcode_item);
        bankCard = (TextView) findViewById(R.id.bankCard);
        infos = (LinearLayout) findViewById(R.id.infos);
        initInfos();
        agree_pay_tips = (TextView) findViewById(R.id.agree_pay_tips);
        final String text = getString(R.string.agree_pay_tips);
        SpannableString ss = new SpannableString(text);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_background)), 6, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_background)), 13, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        agree_pay_tips.setText(ss);
        next = (Button) findViewById(R.id.next);
        setNextButtonEnable(false);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrsConfirmPaymentReq trs = new TrsConfirmPaymentReq();
                trs.setService(ServiceParam.service);
                trs.setService_version(ServiceParam.service_version);
                trs.setInput_charset(ServiceParam.input_charset);
                trs.setSign_type(ServiceParam.sign_type);
                trs.setTrans_mode(ServiceParam.TransMode.CONFIRM_PAYMENT_INFO);
                trs.setPartner(ServiceParam.partner);
                trs.setOut_trade_no(out_trade_no);
                trs.setCurrency(ServiceParam.Currency.CNY);
                trs.setTotal_fee(amount);
                trs.setId_type(idcode_item.getIdType());
                trs.setId_no(idcode_item.getIdNo());
                trs.setAcct_name(name);
                trs.setCard_no(cardNo);
                trs.setPhone(phone.getEditText());
                trs.setVerify_msg(security_code.getEditText());
                pay(trs);
            }
        });
    }

    public void initInfos() {
        validity = new InfoItemView(this);
        validity.setInfo_title(getString(R.string.validity));
        validity.setCenterLayout(InfoItemView.INFO_EDIT_TYPE);
        validity.setInfo_editHintText(getString(R.string.input_validity));
        validity.setRightLayout(InfoItemView.RIGHT_IMAGE);
        validity.info_edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        validity.info_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (StringUtils.isNotNullOrEmpty(s.toString())) {
                    isValidityNotNull = true;
                    freshNextButtonStatus();
                } else {
                    isValidityNotNull = false;
                    setNextButtonEnable(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        if (ServiceParam.CardType.CREDIT.equals(cardType)) {
            infos.addView(validity);
            validity.info_edit.requestFocus();
        } else {
            isValidityNotNull = true;
        }

        cvv = new InfoItemView(this);
        cvv.setInfo_title(getString(R.string.CVV));
        cvv.setCenterLayout(InfoItemView.INFO_EDIT_TYPE);
        cvv.setInfo_editHintText(getString(R.string.input_CVV));
        cvv.setRightLayout(InfoItemView.RIGHT_IMAGE);
        cvv.info_edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        cvv.info_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (StringUtils.isNotNullOrEmpty(s.toString())) {
                    isCVVNotNull = true;
                    freshNextButtonStatus();
                } else {
                    isCVVNotNull = false;
                    setNextButtonEnable(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        if (ServiceParam.CardType.CREDIT.equals(cardType)) {
            infos.addView(cvv);
        } else {
            isCVVNotNull = true;
        }

        phone = new InfoItemView(this);
        phone.setInfo_title(getString(R.string.phone));
        phone.setCenterLayout(InfoItemView.INFO_EDIT_TYPE);
        phone.setInfo_editHintText(getString(R.string.input_phone));
        phone.info_edit.setInputType(InputType.TYPE_CLASS_PHONE);
        if (isCVVNotNull) {
            phone.info_edit.requestFocus();
        }
        phone.info_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (StringUtils.isNotNullOrEmpty(s.toString())) {
                    isPhoneNotNull = true;
                    freshNextButtonStatus();
                } else {
                    isPhoneNotNull = false;
                    setNextButtonEnable(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
        infos.addView(phone);
        security_code = new InfoItemView(this);
        security_code.setInfo_title(getString(R.string.security_code));
        security_code.setCenterLayout(InfoItemView.INFO_EDIT_TYPE);
        security_code.setInfo_editHintText(getString(R.string.input_security_code));
        security_code.setRightLayout(InfoItemView.RIGHT_BUTTON_TYPE);
        security_code.info_edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        security_code.right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ServiceParam.CardType.CREDIT.equals(cardType)) {
                    if (StringUtils.isNullOrEmpty(validity.getEditText())) {
                        Toast.makeText(ZHPayActivity.this, R.string.please_input_validity, Toast.LENGTH_SHORT).show();
                        validity.info_edit.requestFocus();
                        return;
                    } else if (StringUtils.isNullOrEmpty(cvv.getEditText())) {
                        Toast.makeText(ZHPayActivity.this, R.string.please_input_CVV, Toast.LENGTH_SHORT).show();
                        cvv.info_edit.requestFocus();
                        return;
                    }
                }

                if (StringUtils.isNullOrEmpty(phone.getEditText())) {
                    Toast.makeText(ZHPayActivity.this, R.string.input_phone, Toast.LENGTH_SHORT).show();
                    phone.info_edit.requestFocus();
                    return;
                }
                TrsCheckCodeReq trs = new TrsCheckCodeReq();
                trs.setService(ServiceParam.service);
                trs.setService_version(ServiceParam.service_version);
                trs.setInput_charset(ServiceParam.input_charset);
                trs.setSign_type(ServiceParam.sign_type);
                trs.setTrans_mode(ServiceParam.TransMode.VALID_PAYMENT_INFO);
                trs.setPartner(ServiceParam.partner);
                trs.setOut_trade_no(out_trade_no);
                trs.setCurrency(ServiceParam.Currency.CNY);
                trs.setTotal_fee(amount);
                trs.setId_type(idcode_item.getIdType());
                trs.setId_no(idcode_item.getIdNo());
                trs.setAcct_name(name);
                trs.setCard_no(cardNo);
                trs.setCard_type(cardType);
                trs.setBank_name(bankName);
                trs.setPhone(phone.getEditText());
                if (ServiceParam.CardType.CREDIT.equals(cardType)) {
                    trs.setCvv(cvv.getEditText());
                    trs.setValid_date(validity.getEditText());
                }
                sendCode(trs);


            }
        });
        security_code.info_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (StringUtils.isNotNullOrEmpty(s.toString())) {
                    isSecurityCodeNotNull = true;
                    freshNextButtonStatus();
                } else {
                    isSecurityCodeNotNull = false;
                    setNextButtonEnable(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
        infos.addView(security_code);
    }

    /**
     * 刷新next按钮状态
     */
    public void freshNextButtonStatus() {
        if (isValidityNotNull && isCVVNotNull && isPhoneNotNull && isSecurityCodeNotNull) {
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
        next.setEnabled(enable);
        if (enable) {
            next.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_round));
        } else {
            next.setBackgroundDrawable(getResources().getDrawable(R.drawable.grey_button_round));
        }
    }

    private void initData() {

        amount_textView.setText(getString(R.string.RMB) + amount);
        idcode_item.setData(idType,idNo);
        String tailNumber = cardNo.substring(cardNo.length() - 4, cardNo.length());
        StringBuffer sb = new StringBuffer();
        sb.append(String.format(getString(R.string.tail_number), tailNumber));
        bankCard.setText(bankName.replace("\n", "") + sb.toString());

    }

    /**
     * 发送验证码
     *
     * @param req
     */
    public void sendCode(TrsCheckCodeReq req) {

        try {
            String sign = EncodeUtils.sign(req);
            req.setSign(sign);
            String enmsg = EncodeUtils.getPostBody(req);
            dialogShow();
            AllRequestApi.request(enmsg, new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    dialogDismiss();
                    try {
                        String data = DecodeUtils.decode(response.toString());
                        LogUtils.e("data:" + data);
                        Gson gson = new Gson();
                        TrsCheckCodeResp checkCodeResp = gson.fromJson(data, TrsCheckCodeResp.class);
                        String sign = checkCodeResp.getSign();
                        String[] str = GsonUtils.jsonToArray(checkCodeResp);
                        if (str != null) {
                            str = StringUtils.StringSort(str);
                            // 将数组转为字符串
                            String signMsg = StringUtils.arrayToString(str, "&");
                            LogUtils.e(signMsg);
                            boolean very = DecodeUtils.verySign(signMsg, sign);
                            LogUtils.e("" + very);
                            if (very) {
                                if (ServiceParam.RetCode.REQUEST_SUCCESS_CODE.equals(checkCodeResp.getRet_code())) {
                                    Toast.makeText(ZHPayActivity.this, R.string.code_has_send, Toast.LENGTH_SHORT).show();
                                    security_code.right_button.setEnabled(false);
                                    security_code.right_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.light_red_button_round));
                                    security_code.right_button.setText("60秒");
                                    new Thread(new MyThread()).start();
                                } else {
                                    Toast.makeText(ZHPayActivity.this, checkCodeResp.getRet_msg(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ZHPayActivity.this, R.string.wrong_sign, Toast.LENGTH_SHORT).show();
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

    private boolean isRun = true;
    private int recLen = 60;
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    recLen--;
                    if (recLen == 0) {
                        isRun = false;
                        security_code.right_button.setText(getString(R.string.get));
                        security_code.right_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_round));
                        security_code.setEnabled(true);
                    } else if (recLen > 0) {
                        isRun = true;
                        security_code.right_button.setText(recLen + "秒");
                    } else {
                        isRun = false;
                    }
            }
            super.handleMessage(msg);
        }
    };

    public class MyThread implements Runnable {
        @Override
        public void run() {
            while (isRun) {
                try {
                    Thread.sleep(1000); // sleep 1000ms
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (Exception e) {
                }
            }
            return;
        }
    }

    /**
     * 支付
     *
     * @param trs
     */
    public void pay(TrsConfirmPaymentReq trs) {
        try {
            String sign = EncodeUtils.sign(trs);
            trs.setSign(sign);
            String enmsg = EncodeUtils.getPostBody(trs);
            initDialog(getString(R.string.paying));
            dialogShow();
            AllRequestApi.request(enmsg, new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    dialogDismiss();
                    try {
                        String data = DecodeUtils.decode(response.toString());
                        LogUtils.e("data:" + data);
                        Gson gson = new Gson();
                        TrsConfirmPaymentResp confirmPaymentResp = gson.fromJson(data, TrsConfirmPaymentResp.class);
                        String sign = confirmPaymentResp.getSign();
                        String[] str = GsonUtils.jsonToArray(confirmPaymentResp);
                        if (str != null) {
                            str = StringUtils.StringSort(str);
                            // 将数组转为字符串
                            String signMsg = StringUtils.arrayToString(str, "&");
                            LogUtils.e(signMsg);
                            boolean very = DecodeUtils.verySign(signMsg, sign);
                            LogUtils.e("" + very);
                            if (very) {

                                if (ServiceParam.handler != null) {
                                    Message message = new Message();
                                    message.what = ServiceParam.PAYFINISH;
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("result", confirmPaymentResp);
                                    message.setData(bundle);
                                    ServiceParam.handler.sendMessage(message);
                                }
                                if (ServiceParam.RetCode.PAY_SUCCESS_CODE.equals(confirmPaymentResp.getRet_code())) {
                                    Toast.makeText(ZHPayActivity.this, R.string.pay_success, Toast.LENGTH_SHORT).show();
                                    Const.exit();
                                } else {
                                    Toast.makeText(ZHPayActivity.this, confirmPaymentResp.getRet_msg(), Toast.LENGTH_SHORT).show();
                                    Const.exit();
                                }
                            } else {
                                Toast.makeText(ZHPayActivity.this, R.string.wrong_sign, Toast.LENGTH_SHORT).show();
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
