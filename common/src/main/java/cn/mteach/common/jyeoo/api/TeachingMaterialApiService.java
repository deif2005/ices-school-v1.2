package cn.mteach.common.jyeoo.api;

import cn.mteach.common.jyeoo.constant.HttpMethods;
import cn.mteach.common.jyeoo.util.HttpClientUtil;
import cn.mteach.common.jyeoo.util.HttpHeader;
import org.apache.http.Header;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class TeachingMaterialApiService {

    /**
     * 获取题型、试题来源、教材版本、学科分类（选修或必修）、竞赛分类、学科来源的二级分类相关信息（通过tp参数控制）
     * @param token 用户token
     * @param subject 学科英文名称
     * @param tp  1：试题题型 2：试题来源 3：教材版本 4：学科分类（选修或必修）
     * @param pd 试卷来源(当tp为2时需要传入该参数，否则不用传该参数)
     * @return
     * @throws Exception
     */
    public static String getCommon(String token,String subject,int tp,int pd) throws Exception {

        String url = "http://api.jyeoo.com/v1/"+subject+"/common?tp="+tp+"&pd"+pd;

        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

    /**
     *  获取所选学科的教材（包含教材章节）
     * @param token 用户token
     * @param subject 学科英文名称
     * @param e  版本标识
     * @param g  年级标识
     * @param t  学期标识（1：上学期；2：下学期）
     * @return
     * @throws Exception
     */
    public static String getBook(String token,String subject,String e,String g,int t) throws Exception {

        String url = "http://api.jyeoo.com/v1/"+subject+"/book?e="+e+"&g="+g+"&t="+t;

        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

    /**
     *  按教材标识获取教材（包含教材章节）
     * @param token
     * @param subject 学科英文名称
     * @param id 教材标识
     * @return
     * @throws Exception
     */
    public static String getBook(String token,String subject,String id)throws Exception{

        String url = "http://api.jyeoo.com/v1/"+subject+"/book?id="+id;

        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

    /**
     * 获取教材版本信息
     * @param token
     * @param subject 学科英文名称
     * @return
     * @throws Exception
     */
    public static String getBookVersion(String token, String subject)throws Exception{
        String url = "http://api.jyeoo.com/v1/"+subject+"/common?tp=3";
        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

}
