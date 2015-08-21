package com.checkout.diancan.tools;

import com.checkout.diancan.config.LogUtils;

/**
 * Created by test on 2015/6/8.
 */
public class TestServiceSignUtils {

    /**
     * 获取签名
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String sign(Object obj) throws Exception {
        String[] SignStrArray = GsonUtils.jsonToArray(obj);
        SignStrArray = StringUtils.StringSort(SignStrArray);
        // 将数组转为字符串
        String signMsg = StringUtils.arrayToString(SignStrArray, "&");
        LogUtils.e(signMsg);
        String sign = KeyUtils.sign(signMsg.getBytes("utf-8"),
                KeyUtils.serivice_pri_path, KeyUtils.alias, KeyUtils.password);
        LogUtils.e(sign);
        return sign;
    }

}
