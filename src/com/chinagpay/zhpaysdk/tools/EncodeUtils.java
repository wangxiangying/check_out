package com.chinagpay.zhpaysdk.tools;

import com.chinagpay.zhpaysdk.config.LogUtils;
import com.google.gson.Gson;

import javax.crypto.Cipher;
import java.security.PublicKey;
import java.util.Random;

/**
 * Created by test on 2015/6/1.
 */
public class EncodeUtils {

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
                KeyUtils.phone_pri_path, KeyUtils.alias, KeyUtils.password);
        LogUtils.e(sign);
        return sign;
    }

    /**
     * 数据加密
     *
     * @param trs
     * @return
     */
    public static String getPostBody(Object trs) {
        Gson gson = new Gson();
        String encMs = null;
        try {
            encMs = EncodeUtils.encode(gson.toJson(trs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.e(encMs);
        return encMs;
    }

    /**
     * 加密数据
     *
     * @param json
     * @return
     * @throws Exception
     */
    public static String encode(String json) throws Exception {

        String roundKey = generateKey(9999, 24);

        String encMsg = EncDecUtil.enc(roundKey, json);
        String encRound = Base64.encode(encryptByPublicKey(roundKey.getBytes()));

        return encMsg + "|" + encRound;
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data)
            throws Exception {
        // 取得公钥
        PublicKey publicKey = KeyUtils.getPublicKeyInstance();
        // 对数据加密
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 生成随机密钥
     *
     * @author mah
     * @version 创建时间：2015年5月5日 下午3:30:14 方法说明
     */
    public static String generateKey(int round, int length) {

        String key = "";

        for (int i = 0; i < length; i++) {

            Random rand = new Random();
            int random = rand.nextInt(round) % 16;

            switch (random) {
                case 0:
                    key += "0";
                    break;
                case 1:
                    key += "1";
                    break;
                case 2:
                    key += "2";
                    break;
                case 3:
                    key += "3";
                    break;
                case 4:
                    key += "4";
                    break;
                case 5:
                    key += "5";
                    break;
                case 6:
                    key += "6";
                    break;
                case 7:
                    key += "7";
                    break;
                case 8:
                    key += "8";
                    break;
                case 9:
                    key += "9";
                    break;
                case 10:
                    key += "A";
                    break;
                case 11:
                    key += "B";
                    break;
                case 12:
                    key += "C";
                    break;
                case 13:
                    key += "D";
                    break;
                case 14:
                    key += "E";
                    break;
                case 15:
                    key += "F";
                    break;
                default:
                    i--;
            }

        }

        return Base64.encode(key.getBytes());
    }

}
