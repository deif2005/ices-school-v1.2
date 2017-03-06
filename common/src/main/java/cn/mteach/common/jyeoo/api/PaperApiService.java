package cn.mteach.common.jyeoo.api;

import cn.mteach.common.domain.jyeoo.Groups;
import cn.mteach.common.domain.jyeoo.Ques;
import cn.mteach.common.domain.jyeoo.UserPaper;
import cn.mteach.common.jyeoo.constant.HttpMethods;
import cn.mteach.common.jyeoo.util.HttpClientUtil;
import cn.mteach.common.jyeoo.util.HttpHeader;
import cn.mteach.common.jyeoo.util.HttpUtil;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class PaperApiService {

    /**
     * 智能组卷
     * @param token 用户token
     * @param subject 学科英文名称
     * @param tp 训练类别（1：教材；2：考点）
     * @param p1 教材标识或一级考点编号或训练标识或试卷标识
     * @param p2 教材目录或二级考点编号
     * @param p3 考点编号
     * @param q1 选择题数量
     * @param q2 填空题数量
     * @param q3 解答题数量
     * @param dg 难度系数（0：不限；1：基础题；2：中档题；3：难题）
     * @param sc 真题集
     * @param gc 好题集
     * @param rc 常考题
     * @param yc 压轴题
     * @param ec 易错题
     * @return
     * @throws Exception
     */
    public String newPaper(String token, String subject,int tp,String p1,String p2,String p3,
                           int q1,int q2,int q3,int dg,boolean sc,boolean gc,boolean rc,boolean yc,boolean ec) throws Exception {

        String url = "http://api.jyeoo.com/v1/"+subject+"/paper?tp="+tp+"&p1="+p1+"&p2="+p2+"&p3="+p3+"&q1="+q1+"&q2="+q2+"&q3="+q3+"&dg="+dg+"&sc="+sc+"&gc="+gc+"&rc="+rc+"&yc="+yc+"&ec="+ec;

        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

    /**
     * 搜索组卷
     * @param token 用户token
     * @param subject 学科英文名称
     * @param tp 分类（0：最新组卷；1：最热组卷；2：用户组卷）
     * @param pi 当前页（以1开始）
     * @param ps 每页记录数
     * @return
     * @throws Exception
     */
    public String searchPaper(String token, String subject,int tp,int pi,int ps) throws Exception {
        String url = "http://api.jyeoo.com/v1/"+subject+"/paper?tp="+tp+"&pi="+pi+"&ps="+ps;

        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }


    /**
     * 下载试卷
     * @param token 用户token
     * @param subject 学科英文名称
     * @param id 试卷标识
     * @param method 解析（分析、解答、点评、考点、专题）
     * @return
     * @throws Exception
     */
    public byte[] downPaper(String token, String subject,String id, int method) throws Exception {
        byte[] result = null;
        String url = "http://api.jyeoo.com/v1/"+subject+"/paper/"+id+"?method="+method;

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
     * 删除组卷
     * @param token 用户token
     * @param subject 学科英文名称
     * @param id 组卷标识
     * @return
     * @throws Exception
     */
    public String deletePaper(String token, String subject,String id) throws Exception {

        String url = "http://api.jyeoo.com/v1/"+subject+"/paper/"+id;

        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.DELETE, null, headers, "UTF-8");
        return resp;
    }

    /**
     * 获取组卷解析
     * @param token 用户token
     * @param subject 学科英文名称
     * @param id 组卷标识
     * @return
     * @throws Exception
     */
    public String getPaper(String token, String subject,String id) throws Exception {

        String url = "http://api.jyeoo.com/v1/"+subject+"/paper/"+id;
        Header[] headers = HttpHeader.custom().authorization("Token "+token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

    /**
     * 提交组卷
     * @param token 用户token
     * @param subject 学科英文名称
     * @param userPaper 用户组卷对象
     * @return
     * @throws Exception
     */
    public String submitPaper(String token,String subject,UserPaper userPaper) throws Exception {
        // 转json
        JSONObject jsonObject = JSONObject.fromObject(userPaper);
        String url = "http://api.jyeoo.com/v1/" + subject + "/paper";
        String resp = HttpClientUtil.doHttpURLConnection(url, jsonObject.toString(), token);
        return resp;
    }

    public static void main(String[] args) throws Exception {
        UserPaper userPaper = new UserPaper();
        userPaper.setID("536d00cf-ecda-11e6-b865-b82a72df95f8");
        userPaper.setTitle("2017年02月7日高中数学组卷");
        userPaper.setDescription("");
        userPaper.setScore(100);
        userPaper.setTimes(120);
        userPaper.setSubject("math2");
        Ques q1 = new Ques();
        q1.setID("70b2b342-5b87-40f1-be5e-284324feca42");
        q1.setSeq(1);
        q1.setScore(100);

        List<Ques> ques = new ArrayList<>();
        ques.add(q1);
        Groups groups = new Groups();
        groups.setKey("1、填空题");
        groups.setValue(ques);
        List<Groups> listGroups = new ArrayList<>();
        listGroups.add(groups);
        userPaper.setGroups(listGroups);

        String token = "34F96C65BAFBABC90F18844A91795DFDEF20ECCA97328103E9A5FA938A457ABAFFB3B7384C020E1048D123D91B4A4E5A98E01270B7D4B1FFEAA07A9B1D18D489BC841A2B5375FAC255CA34E0ED38DDEA4F3AE6C54AD80FC77E1B7A2CC88B221C79F1EB933D91D6E05780E29654C5D2A4343DF874E8A6925C7A6D007F65E9486718D07C0C8C3B8F918CC0986E8BD2BDE9";
        PaperApiService paperApiService = new PaperApiService();

        //提交组卷
        String resp = paperApiService.submitPaper(token,"math2",userPaper);
        System.out.println("提交组卷返回:"+resp);

        //下载组卷
        byte[] result = paperApiService.downPaper(token,"math2","536d00cf-ecda-11e6-b865-b82a72df95f8",1);
        HttpUtil.getFile(result, "D:\\资料", "paper.doc");
        System.out.println("下载组卷："+result);

        //删除组卷
        String deletePaper = paperApiService.deletePaper(token, "math2", "536d00cf-ecda-11e6-b865-b82a72df95f8");
        System.out.println("删除组卷：" + deletePaper);
    }
}
