package com.chinagpay.diancan.bean;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.chinagpay.diancan.ui.CustomPayActivity;
import com.chinagpay.zhpaysdk.R;
import com.chinagpay.diancan.config.ZHPayApp;
import com.chinagpay.diancan.ui.UnCardBinActivity;
import com.chinagpay.diancan.ui.ZHPayActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by test on 2015/6/2.
 */
public class ServiceParam {
    public static String service = "create_mobile_trade";
    public static String service_version = "1.0";
    public static String input_charset = "UTF-8";
    public static String sign_type = "RSA";
    public static String partner = "99999999";

    /**
     * 交易模式
     */
    public static class TransMode {
        /**
         * 标准支付方式。每次支付用户均需要自己完成卡号、姓名、身份证等要素的填写。
         */
        public final static String NORMAL_MODE = "NORMAL_MODE";
        /**
         * 绑卡支付模式。用户卡号、姓名、身份证、开户行几项支付信息由商户端传送完成。
         */
        public final static String SIMPLE_MODE = "SIMPLE_MODE";
        /**
         * 提交手机号与卡号、姓名、证件号进行关联验证。验证通过发送验证短信。
         */
        public final static String VALID_PAYMENT_INFO = "VALID_PAYMENT_INFO";
        /**
         * 提交支付
         */
        public final static String CONFIRM_PAYMENT_INFO = "CONFIRM_PAYMENT_INFO";
        /**
         * 查询订单明细
         */
        public final static String QUERY_ORDER_INFO = "QUERY_ORDER_INFO";
        /**
         * 查询卡bin信息
         */
        public final static String QUERY_CARD_INFO = "QUERY_CARD_INFO";
        /**
         * 查询银行列表
         */
        public final static String QUERY_BANK_LIST = "QUERY_BANK_LIST";
    }

    /**
     * 币种
     */
    public static class Currency {
        /**
         * 人民币
         */
        public final static String CNY = "CNY";
        /**
         * 英镑
         */
        public final static String GBP = "GBP";
        /**
         * 港币
         */
        public final static String HKD = "HKD";
        /**
         * 美元
         */
        public final static String USD = "USD";
        /**
         * 日元
         */
        public final static String JPY = "JPY";
        /**
         * 加拿大元
         */
        public final static String CAD = "CAD";
        /**
         * 澳大利亚元
         */
        public final static String AUD = "AUD";
        /**
         * 欧元
         */
        public final static String EUR = "EUR";
    }

    /**
     * 卡类型
     */
    public static class CardType {
        /**
         * 信用卡
         */
        public final static String CREDIT = "C";
        /**
         * 借记卡
         */
        public final static String DEBIT = "D";
    }

    /**
     * 证件类型
     */
    public static class IDType {
        /**
         * 身份证
         */
        public final static String IDType_01 = "01";
        /**
         * 户口簿
         */
        public final static String IDType_02 = "02";
        /**
         * 护照
         */
        public final static String IDType_03 = "03";
        /**
         * 军官证
         */
        public final static String IDType_04 = "04";
        /**
         * 士兵证
         */
        public final static String IDType_05 = "05";
        /**
         * 港澳居民来往
         */
        public final static String IDType_06 = "06";
        /**
         * 台湾同胞来往内地通行
         */
        public final static String IDType_07 = "07";
        /**
         * 临时身份证
         */
        public final static String IDType_08 = "08";
        /**
         * 外国人居留证
         */
        public final static String IDType_09 = "09";
        /**
         * 警官证
         */
        public final static String IDType_10 = "10";
        /**
         * 其他证件
         */
        public final static String IDType_X = "X";

        public static HashMap<String, String> idType = new HashMap<String, String>() {

            {
                put("01", "身份证");
                put("02", "户口簿");
                put("03", "护照");
                put("04", "军官证");
                put("05", "士兵证");
                put("06", "港澳居民来往");
                put("07", "台湾同胞来往内地通行");
                put("08", "临时身份证");
                put("09", "外国人居留证");
                put("10", "警官证");
                put("X", "其他证件");
            }
        };

        /**
         * 根据key获取证件全名
         *
         * @param key
         * @return
         */
        public static String getTypeFullNameFromKey(String key) {

            String value = idType.get(key);
            return value;
        }

        /**
         * 根据key获取证件缩写
         *
         * @param key
         * @return
         */
        public static String getTypeSmallNameFromKey(String key) {

            String value;
            if (IDType_06.equals(key)) {
                value = ZHPayApp.mApplicationContext.getString(R.string.HKMacao);
            } else if (IDType_07.equals(key)) {
                value = ZHPayApp.mApplicationContext.getString(R.string.TaiWan);
            } else if (IDType_08.equals(key)) {
                value = ZHPayApp.mApplicationContext.getString(R.string.TemCred);
            } else if (IDType_09.equals(key)) {
                value = ZHPayApp.mApplicationContext.getString(R.string.Foreigner);
            } else {
                value = idType.get(key);
            }
            return value;
        }

        /**
         * 根据证件全名获取对应的key
         *
         * @param v
         * @return
         */
        public static String getKeyFromValue(String v) {

            Iterator iterator = idType.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String value = (String) entry.getValue();
                String key = (String) entry.getKey();
                if (value.equals(v)) {
                    return key;
                }
            }
            return null;
        }
    }

    /**
     * 返回页面
     */
    public static class RetMethod {

        public final static String NORMAL_ST_PROC = "NORMAL_ST_PROC";
        public final static String NORMAL_CC_PROC = "NORMAL_CC_PROC";
        public final static String NORMAL_DC_PROC = "NORMAL_DC_PROC";
        public final static String NORMAL_NS_PROC = "NORMAL_NS_PROC";
        public final static String SIMPLE_CC_PROC = "SIMPLE_CC_PROC";
        public final static String SIMPLE_DC_PROC = "SIMPLE_DC_PROC";
        public final static String NOTIFY_ALERT1_A = "NOTIFY_ALERT1_A";
        public final static String NOTIFY_ALERT2 = "NOTIFY_ALERT2";
        public final static String NOTIFY_TOAST_A1 = "NOTIFY_TOAST_A1";
        public final static String NOTIFY_TOAST_A2 = "NOTIFY_TOAST_A2";
        public final static String NOTIFY_TOAST_B1 = "NOTIFY_TOAST_B1";
        public final static String NOTIFY_TOAST_B2 = "NOTIFY_TOAST_B2";
        public final static String BANK_LIST = "BANK_LIST";
        public final static String PAYMENT_RESULT = "PAYMENT_RESULT";
        public final static String ID_LIST = "ID_LIST";
        public final static String TXT_LIST = "TXT_LIST";
    }

    /**
     * 返回码
     */
    public static class RetCode {
        /**
         * 支付成功
         */
        public final static String PAY_SUCCESS_CODE = "0000";
        /**
         * 请求已接收/任务处理成功
         */
        public final static String REQUEST_SUCCESS_CODE = "0001";
        /**
         * 未登记的卡bin
         */
        public final static String UN_CARDBIN_CODE = "2010";
    }

    public static Intent retMethod(Context context, String ret_method) {

        Intent intent = new Intent();
        if (RetMethod.NORMAL_ST_PROC.equals(ret_method)) {
            intent.setClass(context, CustomPayActivity.class);
        } else if (RetMethod.NORMAL_CC_PROC.equals(ret_method) || RetMethod.NORMAL_DC_PROC.equals(ret_method)) {
            intent.setClass(context, ZHPayActivity.class);
        } else if(RetMethod.NORMAL_NS_PROC.equals(ret_method)){
            intent.setClass(context, UnCardBinActivity.class);
        }
        return intent;
    }

    /**
     * 消息传送handler
     */
    public static Handler handler;

    public static final int PAYFINISH = 1001;
    public static final int PAYCANCLE = 1002;
    public static String CustomTitle;
}
