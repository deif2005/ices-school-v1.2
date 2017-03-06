package cn.mteach.common.jyeoo.api;

import cn.mteach.common.jyeoo.constant.HttpMethods;
import cn.mteach.common.jyeoo.util.HttpClientUtil;
import cn.mteach.common.jyeoo.util.HttpHeader;
import org.apache.http.Header;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class ExamApiService {

    /**
     * 新建训练
     *
     * @param token   用户token
     * @param subject 学科英文名称
     * @param tp      训练类别（1：教材；2：考点；3：训练；4：试卷）
     * @param p1      教材标识或一级考点编号或训练标识或试卷标识
     * @param p2      教材目录或二级考点编号
     * @param p3      考点编号
     * @param q1      选择题数量
     * @param q2      填空题数量
     * @param q3      解答题数量
     * @param dg      难度系数（0：不限；1：基础题；2：中档题；3：难题）
     * @param sc      真题集
     * @param gc      好题集
     * @param rc      常考题
     * @param yc      压轴题
     * @param ec      易错题
     * @param er      错题重做
     * @return
     * @throws Exception
     */
    public String newExam(String token, String subject, int tp, String p1, String p2, String p3,
                          int q1, int q2, int q3, int dg, boolean sc,
                          boolean gc, boolean rc, boolean yc, boolean ec, boolean er) throws Exception {

        String url = "http://api.jyeoo.com/v1/" + subject + "/exam?tp=" + tp + "&p1=" + p1 + "&p2=" + p2 + "&p3=" + p3 + "&q1=" + q1 + "&q2=" + q2 + "&q3=" + q3 + "&dg=" + dg + "&sc=" + sc + "&gc=" + gc + "&rc=" + rc + "&yc=" + yc + "&ec=" + ec + "&er=" + er;
        Header[] headers = HttpHeader.custom().authorization("Token " + token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

    /**
     * 获取训练记录
     *
     * @param token   用户token
     * @param subject 学科英文名称
     * @param tp      分类（0：全部；1：同步练习；2：单元测验；3：期中测验；4：期末测验；5：错题训练；7：中考冲刺；20：竞赛训练）
     * @param st      状态（0：全部；1：未完成；2：已完成）
     * @param pi      当前页（以1开始）
     * @param ps      每页记录数
     * @return
     * @throws Exception
     */
    public String getExamRecord(String token, String subject, int tp, int st, int pi, int ps) throws Exception {

        String url = "http://api.jyeoo.com/v1/" + subject + "/exam?tp=" + tp + "&st=" + st + "&pi=" + pi + "&ps=" + ps;
        Header[] headers = HttpHeader.custom().authorization("Token " + token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

    /**
     * 按教材或考点获取训练记录
     *
     * @param token   用户token
     * @param subject 学科英文名称
     * @param tp      训练类别（1：教材；2：考点）
     * @param p1      教材标识或一级考点编号
     * @param p2      教材目录或二级考点编号
     * @param p3      考点编号
     * @param st      状态（0：全部；1：未完成；2：已完成）
     * @param pi      当前页（以1开始）
     * @param ps      每页记录数
     * @return
     * @throws Exception
     */
    public String getExamRecord(String token, String subject, int tp, String p1, String p2, String p3, int st, int pi, int ps) throws Exception {

        String url = "http://api.jyeoo.com/v1/" + subject + "/exam?tp=" + tp + "&p1=" + p1 + "&p2=" + p2 + "&p3=" + p3 + "&st=" + st + "&pi=" + pi + "&ps=" + ps;

        Header[] headers = HttpHeader.custom().authorization("Token " + token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }


    /**
     * 获取单个训练
     *
     * @param token   用户token
     * @param subject 学科英文名称
     * @param id      训练标识
     * @return
     * @throws Exception
     */
    public String getExam(String token, String subject, String id) throws Exception {

        String url = "http://api.jyeoo.com/v1/" + subject + "/exam/" + id;

        Header[] headers = HttpHeader.custom().authorization("Token " + token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }

    /**
     * 训练诊断
     *
     * @param token   用户token
     * @param subject 学科英文名称
     * @param t       诊断类别（1：成绩诊断；2：考点诊断；3：难度诊断）
     * @return
     * @throws Exception
     */
    public String analyse(String token, String subject, int t) throws Exception {
        String url = "http://api.jyeoo.com/v1/" + subject + "/analyse/?t=" + t;

        Header[] headers = HttpHeader.custom().authorization("Token " + token).build();
        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.GET, null, headers, "UTF-8");
        return resp;
    }


    /**
     * 提交训练
     *
     * @param token    用户token
     * @param subject  学科英文名称
     * @return
     * @throws Exception
     */
    public String submitExam(String token, String subject, String json) throws Exception {
        // 转json
//        JSONObject userExamJson = JSONObject.fromObject(userExam);
        String url = "http://api.jyeoo.com/v1/" + subject + "/exam";
        String resp = HttpClientUtil.doHttpURLConnection(url, json, token);
        return resp;
    }

    public static void main(String[] args) throws Exception {

    }
}
