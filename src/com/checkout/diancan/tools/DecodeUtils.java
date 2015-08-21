package com.checkout.diancan.tools;

import com.checkout.diancan.config.LogUtils;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;

/**
 * Created by test on 2015/6/1.
 */
public class DecodeUtils {
    /**
     * 解密密文
     *
     * @author mah
     * @version 创建时间：2015年5月1日 下午2:44:27 方法说明
     */
    public static String decode(String encMsg) throws Exception {
        // 获取公钥路径
        String[] msg = encMsg.split("\\|");
        // 加密后的随机数
        String encround = msg[1];
        // 加密后的密文
        String encmsg = msg[0];

        //  byte[] test1 =encround.getBytes();
        byte[] test2 = Base64.decode(encround);
        byte[] test3 = decryptByPrivateKey(test2);
        String round = new String(test3, "utf-8");
        LogUtils.e(round);

        String retMsg = EncDecUtil.dec(round, encmsg);

        return retMsg;
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data) throws Exception {
        // 取得私钥
        PrivateKey privateKey = KeyUtils.getPrivateKeyInstance();
        // 对数据解密
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding","BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }


    /**
     * 验证内部签名
     *
     * @author mah
     * @version 创建时间：2015年5月3日 下午12:12:24
     *
     */
    public static boolean verySign(String sortmsg, String sign)
            throws UnsupportedEncodingException, Exception {
        boolean signResult = KeyUtils.verify(sortmsg.getBytes("UTF-8"),
                sign, KeyUtils.phone_Cer_path);
        return signResult;

    }
}
