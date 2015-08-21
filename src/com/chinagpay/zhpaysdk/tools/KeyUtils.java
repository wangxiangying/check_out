package com.chinagpay.zhpaysdk.tools;

import com.chinagpay.zhpaysdk.config.ZHPayApp;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * 公钥私钥保存获取
 * Created by test on 2015/6/1.
 */
public class KeyUtils {

    public static final String phone_Cer_path = "mobileback.cer";
    public static final String phone_pri_path = "sdk.p12";
    public static final String serivice_pri_path = "mer99jks.p12";
    // 生成证书时的密码
    public static final String password = "mobiletest";
    // 别名
    public static final String alias = "mobiletest";
    // 类型证书
    private static final String CERT_TYPE = "X.509";

    private static PrivateKey privateKey;
    private static PublicKey publicKey;
    //秘钥库
    private static KeyStore ks;
    //私钥证书
    private static X509Certificate x509Certificate_Pri;
    //公钥证书
    private static X509Certificate x509Certificate_Cer;

    public static PrivateKey getPrivateKeyInstance() {
        if (privateKey == null) {
            try {
                privateKey = getPrivateKeyByKeyStore(phone_pri_path, alias, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return privateKey;
    }

    public static PublicKey getPublicKeyInstance() {
        if (publicKey == null) {
            try {
                publicKey = getPublicKeyByCertificate(phone_Cer_path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return publicKey;
    }

    /**
     * 由KeyStore获得私钥
     *
     * @param keyStorePath 密钥库路径
     * @param alias        别名
     * @param password     密码
     * @return PrivateKey 密钥
     * @throws Exception
     */
    private static PrivateKey getPrivateKeyByKeyStore(String keyStorePath,
                                                      String alias, String password) throws Exception {
        // 获得密钥库
        if (ks == null) {
            ks = getKeyStore(keyStorePath, password);
        }
        // 获得私钥
        return (PrivateKey) ks.getKey(alias, password.toCharArray());
    }

    /**
     * 由Certificate获得公钥
     *
     * @param certificatePath 证书路径
     * @return PublicKey 公钥
     */
    private static PublicKey getPublicKeyByCertificate(String certificatePath)
            throws Exception {
        // 获得证书
        Certificate certificate = getCertificate(certificatePath);
        // 获得公钥
        return certificate.getPublicKey();
    }

    /**
     * 获得certificate
     *
     * @param certificatePath 证书路径
     * @return Certificate 证书
     * @throws Exception
     */
    private static Certificate getCertificate(String certificatePath)
            throws Exception {
        // 实例化证书工厂
        CertificateFactory certificateFactory = CertificateFactory
                .getInstance(CERT_TYPE);
        // 取得证书文件流
        //FileInputStream in = new FileInputStream(certificatePath);
        InputStream in = ZHPayApp.mApplicationContext.getResources().getAssets().open(certificatePath);
        // 生成证书
        Certificate certificate = certificateFactory.generateCertificate(in);
        // 关闭证书文件流
        in.close();
        return certificate;
    }

    /**
     * 取得Certificate
     *
     * @param keyStorePath 密钥库路径
     * @param alias        别名
     * @param password     密码
     * @return Certificate 证书
     * @throws Exception
     */
    private static Certificate getCertificate(String keyStorePath,
                                              String alias, String password) throws Exception {
        // 获得密钥库
        if (ks == null) {
            ks = getKeyStore(keyStorePath, password);
        }
        // 获得证书
        return ks.getCertificate(alias);
    }

    /**
     * 获得密钥库
     *
     * @param keyStorePath 密钥库路径
     * @param password     密码
     * @return KeyStore 密钥库
     * @throws Exception
     */
    private static KeyStore getKeyStore(String keyStorePath, String password)
            throws Exception {
        // 实例化密钥库
        // KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        KeyStore ks = KeyStore.getInstance("PKCS12");
        // 获得密钥库文件流
        //FileInputStream in = new FileInputStream(keyStorePath);
        InputStream in = ZHPayApp.mApplicationContext.getResources().getAssets().open(keyStorePath);

        // 加载密钥库
        ks.load(in, password.toCharArray());
        // 关闭密钥库文件流
        in.close();
        return ks;
    }


    /**
     * 签名
     *
     * @param data         待签名数据
     * @param keyStorePath 密钥库路径
     * @param alias        别名
     * @param password     密码
     * @return byte[] 签名
     * @throws Exception
     */
    public static String sign(byte[] data, String keyStorePath, String alias,
                              String password) throws Exception {
        // 获得证书
        if (x509Certificate_Pri == null) {
            x509Certificate_Pri = (X509Certificate) getCertificate(
                    keyStorePath, alias, password);
        }
        // 构建签名,由证书指定签名算法
        Signature signature = Signature.getInstance(x509Certificate_Pri
                .getSigAlgName());
        // 获取私钥
        PrivateKey privateKey = getPrivateKeyInstance();
        // 初始化签名,由私钥构建
        signature.initSign(privateKey);
        signature.update(data);
        return Base64.encode(signature.sign());
    }

    /**
     * 签名验证
     *
     * @param data            数据
     * @param sign            签名
     * @param certificatePath 证书路径
     * @return boolean 验证通过为true
     * @throws Exception
     */
    public static boolean verify(byte[] data, String sign,
                                 String certificatePath) throws Exception {
        // 获得证书
        if(x509Certificate_Cer==null){
            x509Certificate_Cer = (X509Certificate) getCertificate(certificatePath);
        }
        // 构建签名,由证书指定签名算法
        Signature signature = Signature.getInstance(x509Certificate_Cer
                .getSigAlgName());
        // 由证书初始化签名,实际上是使用了证书中的公钥
        signature.initVerify(x509Certificate_Cer);
        signature.update(data);
        // sign=URLDecoder.decode(sign, "UTF-8");
        // byte[] si= Base64.decode(sign);
        // char [] temp =new String(data).toCharArray();
        // byte[] si= Hex.decodeHex(sign.toCharArray());
        byte[] si = Base64.decode(sign);
        return signature.verify(si);
    }
}
