package cn.mteach.common.jyeoo.api;

import cn.mteach.common.jyeoo.constant.HttpMethods;
import cn.mteach.common.jyeoo.util.HttpClientUtil;
import cn.mteach.common.jyeoo.util.HttpHeader;
import org.apache.http.Header;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class KnowledgePointApiService {

    /**
     *  获取所选学科的考点
     * @param subject 学科英文名称
     * @param token 用户token
     * @return
     * @throws Exception
     */
    public static String getPoints(String subject,String token) throws Exception {
        String url = "http://api.jyeoo.com/v1/"+subject+"/point";

        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }
}
