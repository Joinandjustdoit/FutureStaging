package com.touzila.futurestaging.utils;

import android.annotation.SuppressLint;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by liu on 2017/12/5.
 */

public class RSAUtil {

    // 解密最大长度128
    private static final int MAX_DECRYPT_BLOCK = 128;

    // 加密最大长度117
    private static final int MAX_ENCRYPT_BLOCK = 117;

    //公钥
    public static final String DEFAULT_PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9XIQJNnsK6o6wtaGCrawYxEm5uNFKsKqraCME4dVeKoNTc9PdjyQx0REbE0hxP465ZgRZ+J2Tl7PIO628A2dZW0GeEdi55ubbDPwMQjdj1pDhvWJaEpcVM2E+PJTAdFP8eoBfp9RF+igpqHZLmPQatdvquJNWUmUujYeFJjPkOQIDAQAB";

    //私钥
    public static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAL1chAk2ewrqjrC1oYKtrBjESbm40UqwqqtoIwTh1V4qg1Nz092PJDHRERsTSHE/jrlmBFn4nZOXs8g7rbwDZ1lbQZ4R2Lnm5tsM/AxCN2PWkOG9YloSlxUzYT48lMB0U/x6gF+n1EX6KCmodkuY9Bq12+q4k1ZSZS6Nh4UmM+Q5AgMBAAECgYEAo3sv8tQ6Ph5qulzU54EQxwEPtWu+JQGJFxp8wUZHc1i4sF+bVfiygt/AKOPo8vywN5e/wf1F7ZpW+FOtllhJ6/hJmXbAbnF6rqGR3xri3pQkU9uM6YmawNuihdvlOot6DVFb3dfh3Y0Y0Iv7Tt9wz5IciK7/oyEPegIex9eW4UECQQD4i6IJ2aIQNMAn+ydBbVlbY2DoPsjfTpCwi/t+w7jMvrT0XXYQUgk0dO0+UOqeYp609ON13X4HyWE5HvHD9fgtAkEAwwp1k96QrBG3u6WMrht+n0nq45PvNZ3boBUor+cwcealRpHlCD6B1wcPSsOnQpCMOXWFf4ThI2n/9iGMmrI3vQJAQ4Jh4/0KKQ669uEgG4RhFhKbOtn647TKVjnfeOIeqvZN3mYYcHxn5aiw3BFMePLemtY9hkFAP0syrjo6fvirjQJBAKM5V5i+RBicY0T4kLkMbXVk6Nw365LVXv2jd39uXQ6VVW+vnRq/JO4NDHEnOAzu50sW3PgQ/lEi3oDfJso3p4kCQQChbc5pDhxZEgFRouBMEOyBu6FfV0mv5UbQEP8xnQToAAQU0a9xjdTV+3YWYRLa9aEzGcPmSGK6byq+I2QKLFb7";

    private static final String KEY_ALGORITHM = "RSA";




    /**
     * 公钥解密 http://blog.csdn.net/dianyueneo/article/details/17883883
     *
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData) throws Exception {
        byte[] keyBytes = Base64.decode(DEFAULT_PUBLICKEY, Base64.DEFAULT);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(x509KeySpec);
        // android与java后台交互 RSA加密 需改变 默认值RSA/ECB/PKCS1Padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 公钥加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    @SuppressLint("TrulyRandom")
    public static byte[] encryptByPublicKey(byte[] data) throws Exception {
        byte[] keyBytes = Base64.decode(DEFAULT_PUBLICKEY, Base64.DEFAULT);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(x509KeySpec);
        // android与java后台交互 RSA加密 需改变 默认值RSA/ECB/PKCS1Padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 私钥解密
     *
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData) throws Exception {
        byte[] keyBytes = Base64.decode(PRIVATE_KEY, Base64.DEFAULT);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(keySpec);
        // android与java后台交互 RSA加密 需改变 默认值RSA/ECB/PKCS1Padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 私钥加密
     *
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] encryptedData) throws Exception {
        byte[] keyBytes = Base64.decode(PRIVATE_KEY, Base64.DEFAULT);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(keySpec);
        // android与java后台交互 RSA加密 需改变 默认值RSA/ECB/PKCS1Padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

}
