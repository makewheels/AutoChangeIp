package util;

/**
 * ssr工具类
 */
public class SsrUtil {

    /**
     * ssr base64替换
     * 把-换为+，把_换为/
     *
     * @param source
     * @return
     */
    public static String ssrBase64Replace(String source) {
        source = source.replace("+", "-");
        source = source.replace("/", "_");
        return source;
    }

    /**
     * ssr方式的base64编码，包含替换-和_
     *
     * @param source
     * @return
     */
    public static String ssrBase64Encode(String source) {
        return ssrBase64Replace(Base64Util.encode(source));
    }

    /**
     * 通过ssr信息，得到ssr连接
     *
     * @param ip
     * @param port
     * @param password
     * @param remark   备注
     * @param group    分组
     * @return
     */
    public static String getSsrLink(String ip, String port, String password, String remark, String group) {
        String passwordEncoded = ssrBase64Encode(password);
        String remarkEncoded = ssrBase64Encode(remark);
        String groupEncoded = ssrBase64Encode(group);
        String content = ip + ":" + port + ":origin:aes-256-cfb:plain:" + passwordEncoded
                + "/?obfsparam=&remarks=" + remarkEncoded + "&group=" + groupEncoded;
        String ssrLink = "ssr://" + ssrBase64Encode(content);
        while (ssrLink.charAt(ssrLink.length() - 1) == '=') {
            ssrLink = ssrLink.substring(0, ssrLink.length() - 2);
        }
        return ssrLink;
    }

}
