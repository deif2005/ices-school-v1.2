package cn.mteach.common.jyeoo.api;

import cn.mteach.common.jyeoo.constant.HttpMethods;
import cn.mteach.common.jyeoo.util.HttpClientUtil;
import cn.mteach.common.jyeoo.util.HttpHeader;
import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class QuestionApiService {

    /**
     *  按教材或考点搜索试题
     * @param token 用户token
     * @param subject 学科英文名称
     * @param tp 训练类别（1：教材；2：考点）
     * @param p1 教材标识或一级考点编号
     * @param p2 教材目录或二级考点编号
     * @param p3 考点编号
     * @param dg 难度（0：不限；1：基础题；2：中档题；3：难题）
     * @param ct 题型（0：全部；1：选择题；2：填空题；9：解答题）
     * @param sc 真题集（true；false）
     * @param gc 好题集（true；false）
     * @param rc 常考题（true；false）
     * @param yc 压轴题（true；false）
     * @param ec 易错题（true；false）
     * @param er 错题（true；false）
     * @param po 0：综合排序；1：组卷次数；2：真题次数；3：试题难度
     * @param pd 0：升序；1：降序
     * @param pi 当前页（以1开始）
     * @param ps 每页记录数
     * @throws Exception
     */
    public static String SearchQues(String token, String subject, int tp,
                             String p1, String p2, String p3, int dg,
                             int ct, boolean sc, boolean gc, boolean rc,
                             boolean yc, boolean ec, boolean er, int po, int pd, int pi, int ps) throws Exception {

        String url = "http://api.jyeoo.com/v1/" + subject + "/ques?" + "tp=" + tp
                + "&p1=" + p1 + "&p2=" + p2 + "&p3=" + p3
                + "&dg=" + dg + "&ct=" + ct
                + "&sc=" + sc + "&gc=" + gc + "&rc=" + rc + "&yc=" + yc + "&ec=" + ec + "&er=" + er
                + "&po=" + po + "&pd=" + pd + "&pi=" + pi + "&ps=" + ps;

        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

    /**
     *  按关键字搜索题目
     * @param token 用户token
     * @param subject 学科英文名称
     * @param q 搜索词
     * @param pi 当前页（以1开始）
     * @param ps 每页记录数
     * @return
     * @throws Exception
     */
    public static String searchQues(String token, String subject, String q, int pi, int ps) throws Exception {

        String url = "http://api.jyeoo.com/v1/ques?q=" + q+"&pi=" + pi + "&ps=" + ps;

        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

    /**
     * 获取题目解析
     * @param token 用户token
     * @param subject 学科英文名称
     * @param id 试题标识
     * @return
     * @throws Exception
     */
    public static String getQusAnalysis(String token, String subject, String id) throws Exception {

        String url = "http://api.jyeoo.com/v1/"+subject+"/ques/"+id;

        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

    /**
     * 下载试题
     * @param token 用户token
     * @param subject  学科英文名称
     * @param id 试题标识
     * @param method 解析（分析、解答、点评、考点、专题）
     * @return
     * @throws Exception
     */
    public static byte[] download(String token, String subject, String id,int method) throws Exception {
        byte[] result = null;

        String url = "http://api.jyeoo.com/v1/"+subject+"/ques/"+id+"?method="+method;

        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.PUT, null, headers, "UTF-8");

        if(resp!=null){
            Gson gson = new Gson();
            // 反系列化成byte数组
            try{
                result = gson.fromJson(resp, byte[].class);
            }catch (Exception e){
                result = resp.getBytes();
            }
        }
        return result;
    }

    /**
     * 获取推荐试题
     * @param token 用户token
     * @param subject 学科英文名称
     * @param id 试题标识
     * @param ps 推荐试题数量（最多10道）
     * @return
     * @throws Exception
     */
    public static String getRecommendQus(String token, String subject, String id,int ps) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url = "http://api.jyeoo.com/v1/"+subject+"/ques/"+id+"?ps="+ps;

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Authorization", "Token " + token);
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            return  EntityUtils.toString(entity);
        }
        return null;
    }

}
