package com.checkout.diancan.tools;

import org.apache.http.HttpException;

import java.io.IOException;
import java.util.logging.Logger;


/**
 * 加密解密工具
 *
 * @author Administrator
 */
public class EncDecUtil {

    private static Logger logger = Logger.getLogger(EncDecUtil.class.getName());

    /**
     * 解密
     *
     * @param encMsg
     * @return
     */
    public static String dec(String key, String encMsg) {
        String decMsg = "";
        try {
            byte[] srcBytes = EncodeType.decryptMode(key, Base64.decode(encMsg));
            //正式
            decMsg = new String(srcBytes, "UTF-8");
            //测试
//		decMsg =new String(srcBytes);
        } catch (Exception e) {
            decMsg = "";
            e.printStackTrace();
        }
//		logger.info("decMsg:\n"+decMsg);
        return decMsg;
    }

    /**
     * 加密
     *
     * @param msg
     * @return
     */
    public static String enc(String key, String msg) {
        String encMsg = "";
        try {
            //正式
            byte[] encoded = EncodeType.encryptMode(key, msg.getBytes("UTF-8"));
            //测试
//		byte[] encoded = Des3.encryptMode(key, msg.getBytes());
            encMsg = new String(encoded);
            logger.info("encMsg:\n" + encMsg);
        } catch (Exception e) {
            e.printStackTrace();
            encMsg = "";
        }
        return encMsg;
    }

    public static void main(String[] args) throws HttpException, IOException {
        System.out.println(EncDecUtil.enc("ESJPWIgQQDgoJXlRy91VZncpdJgwQEDi", "34sd3d"));
        //"IHiQsJbYcS0="
//		HttpclientProxy.execPOSTMethodParames("http://127.0.0.1:8080/paygate/inner/bankquery", "IHiQsJbYcS0=");
    }

}
