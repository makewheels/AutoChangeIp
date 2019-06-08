package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Http工具类
 *
 * @author Administrator
 */
public class HttpUtil {

    private static String CHARSET = "utf-8";
    private static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36";
    private static String CONTENT_TYPE = "application/x-www-form-urlencoded";

    /**
     * 简单get请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        System.out.println("HttpClient GET: " + url);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet();
//        httpGet.addHeader("User-Agent", USER_AGENT);
        httpGet.setHeader("Content-type", CONTENT_TYPE);
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setURI(URI.create(url));
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            return EntityUtils.toString(entity, CHARSET);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 简单post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        System.out.println("HttpClient POST: " + url);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null) {
            for (Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        httpPost.setHeader("User-Agent", USER_AGENT);
        httpPost.setHeader("Content-type", CONTENT_TYPE);
        String body = null;
        try {
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                body = EntityUtils.toString(entity, CHARSET);
            }
            EntityUtils.consume(entity);
            response.close();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * 带header参数的get请求
     *
     * @param url
     * @param headerMap
     * @return
     */
    public static String get(String url, Map<String, String> headerMap) {
        System.out.println("HttpClient GET (header): " + url);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet();
        httpGet.setHeader("User-Agent", USER_AGENT);
        Set<String> keySet = headerMap.keySet();
        for (String key : keySet) {
            httpGet.setHeader(key, headerMap.get(key));
        }
        httpGet.setURI(URI.create(url));
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            return EntityUtils.toString(entity, CHARSET);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 带header参数的post请求
     *
     * @param url
     * @param headerMap
     * @param params
     * @return
     */
    public static String post(String url, Map<String, String> headerMap, String params) {
        System.out.println("HttpClient POST (header): " + url);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        Set<String> keySet = headerMap.keySet();
        httpPost.setHeader("User-Agent", USER_AGENT);
        for (String key : keySet) {
            httpPost.setHeader(key, headerMap.get(key));
        }
        try {
            httpPost.setEntity(new StringEntity(params));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String body = null;
        try {
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                body = EntityUtils.toString(entity, CHARSET);
            }
            EntityUtils.consume(entity);
            response.close();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return body;
    }

}
