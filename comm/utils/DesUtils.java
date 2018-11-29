package com.oee.pikachu.comm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.Key;

/**
 * Created by Aqua on 2018/10/28.
 */
public final class DesUtils {

    /**
     * 日志.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DesUtils.class);

    /**
     * 字符串默认键值.
     */
    private static String strDefaultKey = "pIkAchU6!0IP$@^20*&#";

    /**
     * 解密工具.
     */
    private static Cipher decryptCipher;

    /**
     * Constructor.
     */
    private DesUtils() {

    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB) 互为可逆的转换过程.
     *
     * @param strIn 需要转换的字符串
     *
     * @return 转换后的byte数组
     *
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
     */
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            final int len = 16;
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, len);
        }
        return arrOut;
    }

    /**
     * 解密字节数组.
     *
     * @param arrB 需解密的字节数组
     *
     * @return 解密后的字节数组
     *
     * @throws Exception
     */
    public static byte[] decryptKey(byte[] arrB) {
        Key key = getKey(strDefaultKey.getBytes());
        try {
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
            return decryptCipher.doFinal(arrB);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密字符串.
     *
     * @param strIn 需解密的字符串
     *
     * @return 解密后的字符串
     *
     * @throws Exception
     */
    public static String decryptKey(String strIn) {
        try {
            return new String(decryptKey(hexStr2ByteArr(strIn)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位.
     *
     * @param arrBTmp 构成该字符串的字节数组
     *
     * @return 生成的密钥
     *
     * @throws Exception
     */
    private static Key getKey(byte[] arrBTmp) {
        // 创建一个空的8位字节数组（默认值为0）
        final int arrayLen = 8;
        byte[] arrB = new byte[arrayLen];

        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

        return key;
    }
}
