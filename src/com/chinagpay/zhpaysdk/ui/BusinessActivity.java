package com.chinagpay.zhpaysdk.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.chinagpay.zhpaysdk.BaseActivity;
import com.chinagpay.zhpaysdk.R;
import com.chinagpay.zhpaysdk.bean.ServiceParam;
import com.chinagpay.zhpaysdk.bean.TrsConfirmPaymentResp;
import com.chinagpay.zhpaysdk.bean.TrsCreateOrderReq;
import com.chinagpay.zhpaysdk.config.BuildConfig;
import com.chinagpay.zhpaysdk.config.LogUtils;
import com.chinagpay.zhpaysdk.manager.DialogManager;
import com.chinagpay.zhpaysdk.manager.OrderManager;
import com.chinagpay.zhpaysdk.tools.EncodeUtils;
import com.chinagpay.zhpaysdk.tools.TestServiceSignUtils;

import java.lang.ref.PhantomReference;

/**
 * Created by test on 2015/6/2.
 */
public class BusinessActivity extends BaseActivity {
    //标准模式，绑卡模式
    private Button normal_mode, sample_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initLayout();
        initPay();
    }

    /**
     * 初始化支付参数
     */
    private void initPay() {
        //用户自定义title
        ServiceParam.CustomTitle = "Test";
        //初始化接收支付SDK返回消息的handler
        ServiceParam.handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case ServiceParam.PAYFINISH:
                        Bundle bundle = msg.getData();
                        if (bundle != null) {

                            TrsConfirmPaymentResp trs = (TrsConfirmPaymentResp) bundle.getSerializable("result");
                            if (trs != null) {

                                if (ServiceParam.RetCode.PAY_SUCCESS_CODE.equals(trs.getRet_code())) {
                                    Toast.makeText(BusinessActivity.this, "商户弾框——支付成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(BusinessActivity.this, "商户弾框——支付失败" + trs.getRet_msg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;
                    case ServiceParam.PAYCANCLE:
                        DialogManager.notice3Show(BusinessActivity.this, "支付已取消", "");
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    private void initLayout() {
        normal_mode = (Button) findViewById(R.id.normal_mode);
        normal_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                try {
//                    String sign = TestServiceSignUtils.sign(initNormalData());
//                    LogUtils.e(sign);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                OrderManager.order(BusinessActivity.this, initDialog(getString(R.string.loadingZHPay)), initNormalData());
            }
        });
        sample_mode = (Button) findViewById(R.id.sample_mode);
        sample_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                try {
//                    String sign = TestServiceSignUtils.sign(initSampleData());
//                    LogUtils.e(sign);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                OrderManager.order(BusinessActivity.this, initDialog(getString(R.string.loadingZHPay)), initSampleData());
            }
        });
    }

    /**
     * 初始化标准支付模式实例
     *
     * @return
     */
    public TrsCreateOrderReq initNormalData() {
        TrsCreateOrderReq trs = new TrsCreateOrderReq();
        trs.setService(ServiceParam.service);
        trs.setService_version(ServiceParam.service_version);
        trs.setInput_charset(ServiceParam.input_charset);
        trs.setSign_type(ServiceParam.sign_type);
        trs.setSign("Ky1gCvfZn2kRL2v1CyUaaGc337Q8NtGWZc41bJlzjnla412L8ALruCkFTtexDUNBrSc++h/yDdxYH63jBF+xh047QfcHapvGvOhzbaT3bcnbxbcdMD2KPntxI3XG3k/FIx61Rmppy6GsdvV154eSENwxQh0GupFWGFWfLmj6EJg=");
        trs.setTrans_mode(ServiceParam.TransMode.NORMAL_MODE);
        trs.setBody("test");
        trs.setPartner(ServiceParam.partner);
        trs.setNotify_url(BuildConfig.HOST);
        trs.setOut_trade_no("20150715164826");
        trs.setCurrency(ServiceParam.Currency.CNY);
        trs.setTotal_fee("160.00");
        trs.setTime_start("20150615164936");
        trs.setId_type(ServiceParam.IDType.IDType_01);
        return trs;
    }

    /**
     * 初始化绑卡支付模式实例
     *
     * @return
     */
    public TrsCreateOrderReq initSampleData() {
        TrsCreateOrderReq trs = new TrsCreateOrderReq();
        trs.setService(ServiceParam.service);
        trs.setService_version(ServiceParam.service_version);
        trs.setInput_charset(ServiceParam.input_charset);
        trs.setSign_type(ServiceParam.sign_type);
        trs.setSign("HBMBFgDnnZzIksdnEB89wWwG2KECAixSePQT23tAoKrbL3MQyuLwqnGZyy1OamzfsNg5E+dL6mnBnuuxythZT9QTHrpYyGY/sQeXZk3flYi8omQITqn8NrQXQC5lgE+708SpjYOv+FS8Qb9DDXiyj3bPZLgOCSlQSvY/fL399Wc=");
        trs.setTrans_mode(ServiceParam.TransMode.SIMPLE_MODE);
        trs.setBody("test");
        trs.setPartner(ServiceParam.partner);
        trs.setNotify_url(BuildConfig.HOST);
        trs.setOut_trade_no("201506161417");
        trs.setCurrency(ServiceParam.Currency.AUD);
        trs.setTotal_fee("115.33");
        trs.setTime_start("20150616141800");
        trs.setId_type(ServiceParam.IDType.IDType_03);
        trs.setId_no("231025199007020648");
        trs.setAcct_name("王雪娇");
        trs.setCard_no("6222080200023728523");
        trs.setBank_name("工商银行");
        return trs;
    }


}
