package cn.mteach.common.jyeoo.api;

import cn.mteach.common.domain.question.Chapter;
import cn.mteach.common.jyeoo.constant.HttpMethods;
import cn.mteach.common.jyeoo.util.HttpClientUtil;
import cn.mteach.common.jyeoo.util.HttpHeader;
import cn.mteach.common.jyeoo.util.JsonUtil;
import cn.mteach.common.util.StringUtil;
import net.sf.json.JSONArray;
import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class BaseInfoApiService {
    /**
     * 获取年级信息
     * @param token 用户token
     * @return
     * @throws Exception
     */
    public static String getGrades(String token) throws Exception {
        String url = "http://api.jyeoo.com/v1/grade";
        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }


    /**
     * 获取科目信息
     * @param token 用户token
     * @return
     * @throws Exception
     */
    public static List getSubjects(String token) throws Exception {
        String url = "http://api.jyeoo.com/v1/subject";
        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        JSONArray jArray=JSONArray.fromObject(resp);
        List list=JSONArray.toList(jArray);
        return list;
    }

    /**
     * 获取章节信息
     */
    public static String getChapterInfo(String token, Chapter chapter) {
        String result = "";
        try {
            result = TeachingMaterialApiService.getBook(token, chapter.getSubjectName(),
                    chapter.getEditionId().toString(),chapter.getGradeId().toString(),chapter.getTermId());
        }catch (Exception e){
            System.out.print(e.getStackTrace());
        }
        return result;
    }
}
