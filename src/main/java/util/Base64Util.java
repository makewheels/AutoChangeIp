package util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * base64工具类
 */
public class Base64Util {
    static Base64 base64 = new Base64();

    /**
     * base64加密
     *
     * @param source
     * @return
     */
    public static String encode(String source) {
        return base64.encodeToString(source.getBytes());
    }

    /**
     * base64解密
     *
     * @param source
     * @return
     */
    public static String decode(String source) {
        try {
            return new String(base64.decode(source), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
