package cn.mteach.common.jyeoo.util;

import cn.mteach.common.jyeoo.constant.JyeooConfigure;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class DES3 {
    // 密钥算法
    public static final String KEY_ALGORITHM = "DESede";

    // 加密 解密算法/工作模式/填充方式
    public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

    // 密码
    public static final byte[] DES_KEY = Base64.decodeBase64(JyeooConfigure.API_KEY);

    // 加密
    public static String encrypt(String text) throws Exception {
        byte[] data = text.getBytes("UTF-8");

        DESedeKeySpec spec = new DESedeKeySpec(DES_KEY);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        Key desKey = keyFactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        cipher.init(Cipher.ENCRYPT_MODE, desKey);

        return bytesToHexString(cipher.doFinal(data));
    }

    // 解密
    public static String decrypt(String text) throws Exception {
        byte[] data = hexStringToBytes(text);

        DESedeKeySpec spec = new DESedeKeySpec(DES_KEY);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        Key desKey = keyFactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        cipher.init(Cipher.DECRYPT_MODE, desKey);

        return new String(cipher.doFinal(data), "UTF-8");
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
