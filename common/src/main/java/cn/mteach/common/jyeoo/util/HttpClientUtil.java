package cn.mteach.common.jyeoo.util;

import cn.mteach.common.jyeoo.constant.HttpMethods;
import cn.mteach.common.jyeoo.exception.HttpProcessException;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 请求资源或服务，自定义client对象，传入请求参数，设置内容类型，并指定参数和返回数据的编码
     * @param url           请求地址
     * @param httpMethod    请求方法
     * @param parasMap      请求参数
     * @param headers       请求头信息
     * @param encoding      编码
     * @return              返回处理结果
     * @throws HttpProcessException
     */
    public static String send(String url, HttpMethods httpMethod, Map<String,String> parasMap,
                              Header[] headers, String encoding) throws HttpProcessException {
        String body;//返回body
        try {
            //创建HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //创建请求对象
            HttpRequestBase request = getRequest(url, httpMethod);
            //设置header信息
            request.setHeaders(headers);
            //判断是否支持设置entity(仅HttpPost、HttpPut、HttpPatch支持)
            if (HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
                List<NameValuePair> nvps = new ArrayList<>();
                //检测url中是否存在参数
                url = HttpUtil.checkHasParas(url, nvps);
                //装填参数
                HttpUtil.map2List(nvps, parasMap);
                //设置参数到请求对象中
                ((HttpEntityEnclosingRequestBase) request).setEntity(new UrlEncodedFormEntity(nvps, encoding));
                logger.debug("请求地址：" + url);
                if (nvps.size() > 0) {
                    logger.debug("请求参数：" + nvps.toString());
                }
            } else {
                int idx = url.indexOf("?");
                logger.debug("请求地址：" + url.substring(0, (idx > 0 ? idx - 1 : url.length() - 1)));
                if (idx > 0) {
                    logger.debug("请求参数：" + url.substring(idx + 1));
                }
            }
            //调用发送请求
            body = execute(httpClient, request, url, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new HttpProcessException(e);
        }
        return body;
    }

    /**
     * 根据请求方法名，获取request对象
     * @param url      请求地址
     * @param method   请求方式
     * @return
     */
    private static HttpRequestBase getRequest(String url, HttpMethods method) {
        HttpRequestBase request;
        switch (method.getCode()) {
            case 0:// HttpGet
                request = new HttpGet(url);
                break;
            case 1:// HttpPost
                request = new HttpPost(url);
                break;
            case 2:// HttpHead
                request = new HttpHead(url);
                break;
            case 3:// HttpPut
                request = new HttpPut(url);
                break;
            case 4:// HttpDelete
                request = new HttpDelete(url);
                break;
            case 5:// HttpTrace
                request = new HttpTrace(url);
                break;
            case 6:// HttpPatch
                request = new HttpPatch(url);
                break;
            case 7:// HttpOptions
                request = new HttpOptions(url);
                break;
            default:
                request = new HttpPost(url);
                break;
        }
        return request;
    }

    /**
     * 请求资源或服务
     * @param client        client对象
     * @param request       请求对象
     * @param url           资源地址
     * @param encoding      编码
     * @return              返回处理结果
     * @throws HttpProcessException
     */
    private static String execute(HttpClient client, HttpRequestBase request,String url, String encoding) throws HttpProcessException {
        String body = "";
        HttpResponse response =null;
        try {
            //执行请求操作，并拿到结果（同步阻塞）
            response = client.execute(request);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, encoding);
                logger.debug(body);
            }
            EntityUtils.consume(entity);
        } catch (ParseException | IOException e) {
            throw new HttpProcessException(e);
        } finally {
            close(response);
        }
        return body;
    }

    /**
     * 关闭response
     * @param resp  HttpResponse对象
     */
    private static void close(HttpResponse resp) {
        try {
            if (resp == null) return;
            //如果CloseableHttpResponse 是resp的父类，则支持关闭
            if (CloseableHttpResponse.class.isAssignableFrom(resp.getClass())) {
                ((CloseableHttpResponse) resp).close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     *
     * @Title: doHttpURLConnection
     * @Description: 提交组卷http请求辅助类
     * @param reqUrl   请求url
     * @param json  请求对象json格式
     * @param tokenId
     * @return
     * @throws Exception
     */
    public static String doHttpURLConnection(String reqUrl, String json, String tokenId) throws Exception {
        URL url = new URL(reqUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        byte[] data = json.getBytes("UTF-8");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Authorization", "Token " + tokenId);
        conn.connect();

        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(data);
        out.flush();
        out.close();

        int retCode = conn.getResponseCode();//获取http状态码
        String res;
        if (retCode == 200) {
            InputStream in = conn.getInputStream();
            byte[] data1 = new byte[in.available()];
            in.read(data1);
            in.close();
            res = new String(data1);
        } else {
            res = " Server returned HTTP response code: " + retCode;
        }
        return res;
    }
}
