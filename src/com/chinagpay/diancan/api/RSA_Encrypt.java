package com.chinagpay.diancan.api;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import android.text.TextUtils;


import java.io.*;
import java.math.BigDecimal;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class RSA_Encrypt {
    /**
     * 指定加密算法为DESede
     */
    private static String ALGORITHM = "RSA";
    /**
     * 指定key的大小
     */
    private static int KEYSIZE = 1024;

    private static PublicKey publicKey = null;

    private static PrivateKey privateKey = null;

    /**
     * 生成密钥对
     */
    private static void generateKeyPair(String pubStr, String priStr) throws Exception {
        if (null == publicKey) {
            /** 得到公钥 */
            if (pubStr!=null) {
                publicKey = getPubKey(pubStr);
            }
        }
        if (null == privateKey) {
            /** 得到私钥 */
            if (priStr!=null) {
                privateKey = getPrivateKey(priStr);
            }
        }
    }

    /**
     * 实例化公钥
     *
     * @return
     */
    private static PublicKey getPubKey(String key) {
        PublicKey publicKey = null;
        try {
            // 自己的公钥(测试)
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(
                    new BASE64Decoder().decodeBuffer(key));
            // RSA对称加密算法
            KeyFactory keyFactory;
            keyFactory = KeyFactory.getInstance("RSA");
            // 取公钥匙对象
            publicKey = keyFactory.generatePublic(bobPubKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * 实例化私钥
     *
     * @return
     */
    private static PrivateKey getPrivateKey(String key) {
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec priPKCS8;
        try {
            priPKCS8 = new PKCS8EncodedKeySpec(
                    new BASE64Decoder().decodeBuffer(key));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            privateKey = keyf.generatePrivate(priPKCS8);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    /**
     * 加密方法
     * source： 源数据
     */
    public static String encrypt(String source, String pubStr) throws Exception {
        generateKeyPair(pubStr, null);
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b1);
    }

//    public byte[] RSAEncrypt(final String plain) throws NoSuchAlgorithmException, NoSuchPaddingException,
//            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        kpg = KeyPairGenerator.getInstance("RSA");
//        kpg.initialize(1024);
//        kp = kpg.genKeyPair();
//        publicKey = kp.getPublic();
//        privateKey = kp.getPrivate();
//
//        cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        encryptedBytes = cipher.doFinal(plain.getBytes());
//        System.out.println("EEncrypted?????" + org.apache.commons.codec.binary.Hex.encodeHexString(encryptedBytes));
//        return encryptedBytes;
//    }

    /**
     * 解密算法
     * cryptograph:密文
     */
    public static String decrypt(String cryptograph, String keyStr) throws Exception {
        generateKeyPair(null, keyStr);
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    public static void main(String[] args) throws Exception {
        String source = "中文测试";//要加密的字符串
        String pubKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVWVaoQW0oYE2q8hkVzRLJFliGVL2GJFF7fRUsiRG9MPbVK42b/1Uy1d6V9qaewq8EcxUmFt1rtSgmZ1IDE7cWiLB2nMMJf7fNMs3Pm4t+8qa0WlhHnXpblBVZm6VrZgtLZ6GaGc+fN/GkUHEpnNWDeIB61VLcmChjTW+6p/8jOwIDAQAB";
        String priKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJVZVqhBbShgTaryGRXNEskWWIZUvYYkUXt9FSyJEb0w9tUrjZv/VTLV3pX2pp7CrwRzFSYW3Wu1KCZnUgMTtxaIsHacwwl/t80yzc+bi37yprRaWEedeluUFVmbpWtmC0tnoZoZz5838aRQcSmc1YN4gHrVUtyYKGNNb7qn/yM7AgMBAAECgYAOAqeCeUDIPOCMXNHFnctZWeNMFr8Ayn9qWj2WQ/WKDgHZ1ZAfRkCz2CRdZRoUYqcQf8tIG3UCGah+kcq7xymxqmbmxL54LMqC3F/s/mAatu4pLFW6LzyQzscrkYbxp5pCp8ovt8VSaj2brqYyEbp+ZOGbOkghk60rahVoH5fu4QJBAOH10e3ljSKJLjAQ9GvWQAkqTTI0oUj9RyETLC3/I7IamWsSs4GUs3oxbWQlIUBNO6ZFouq2LnpuF3QitMteXR8CQQCpNC6bqNCsWht54Jv3CFAblWHjuseZ/tOveFYKHBIhtKPpCSzM/F7Z5fF1ITKA/DMxqu69Vbty5CPn7H5oiNplAkEAqhPuVKNQ7SrWUiWg5D5a6sslWeHVeGv0Cwg0W9bMVnXA/qAP7zwvL41eql9sPMslV8zYiNRh/ZM6IhcxgFXhvwJAZ1ovafc41z4G8HAo9EDIpcCeVXr+dur3pIPNkklJLftn720iR2eUDGbvEQuhDLaAEsOFJnNalzgRUpXbNMD7HQJANWxgEMbqlLbuE/wG8JLgUCV7S6AY8qx+nX97D4fBK4VtMVBMJiF00axdyGGPfaZViMC4sN3VYxcamrOOqNQJpA==";
        String cryptograph = encrypt(source, pubKeyStr);//生成的密文
        System.out.println("加密字符串:" + cryptograph + "-------len:" + cryptograph.length());

        String target = decrypt(cryptograph, priKeyStr);//解密密文
        System.out.println("解密字符串:" + target);

//
//        BigDecimal decimal = new BigDecimal("1.00");
//        BigDecimal decimal1 = new BigDecimal("10.00");
//        BigDecimal decimal2 = decimal.add(decimal1);
//        System.out.println(decimal.toString());
//        System.out.println(decimal1.toString());
//        System.out.println(decimal2.toString());


    }
}
