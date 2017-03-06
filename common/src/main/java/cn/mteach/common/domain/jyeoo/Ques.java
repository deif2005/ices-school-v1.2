package cn.mteach.common.domain.jyeoo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wuliangpu on 2017/2/14.
 */
public class Ques implements Serializable {

    private static final long serialVersionUID = 5111518406356040367L;
    private String Subject; //试题学科
    private String ID;//试题学科
    private String Content;//题干
    private String Method;//解答
    private String Analyse;//分析
    private String Discuss;//点评
    private float Degree;//难度
    private String Cate;//题型（1：选择题；2：填空题；9：解答题）
    private String Label;//试题来源（2012 ?重庆）
    private String VIP;//VIP标志（大于0位VIP试题）
    private String  Teacher;//答题老师
    private String Star;//星级
    private String CateName;//题型名称
    private List<Map<String,String>> Points;//考点
    private List<Map<String,String>>  Topics;//专题
    private List<String> Options;//选择题选项
    private List<String> Answers ;//标准答案
    private int seq;//题号
    public float Score;//分值
    public List<String> UserAnswers;//学生答案
    public List<Integer> UserScores;//答案得分（0：错误；1：正确；2：未判）
    public int FavTime;//收藏
    public int ViewCount;//浏览
    public int DownCount;//下载
    public int RealCount;//真题
    public int PaperCount;//组卷

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public String getAnalyse() {
        return Analyse;
    }

    public void setAnalyse(String analyse) {
        Analyse = analyse;
    }

    public String getDiscuss() {
        return Discuss;
    }

    public void setDiscuss(String discuss) {
        Discuss = discuss;
    }

    public float getDegree() {
        return Degree;
    }

    public void setDegree(float degree) {
        Degree = degree;
    }

    public String getCate() {
        return Cate;
    }

    public void setCate(String cate) {
        Cate = cate;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getVIP() {
        return VIP;
    }

    public void setVIP(String VIP) {
        this.VIP = VIP;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public String getStar() {
        return Star;
    }

    public void setStar(String star) {
        Star = star;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String cateName) {
        CateName = cateName;
    }

    public List<Map<String, String>> getPoints() {
        return Points;
    }

    public void setPoints(List<Map<String, String>> points) {
        Points = points;
    }

    public List<Map<String, String>> getTopics() {
        return Topics;
    }

    public void setTopics(List<Map<String, String>> topics) {
        Topics = topics;
    }

    public List<String> getOptions() {
        return Options;
    }

    public void setOptions(List<String> options) {
        Options = options;
    }

    public List<String> getAnswers() {
        return Answers;
    }

    public void setAnswers(List<String> answers) {
        Answers = answers;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float score) {
        Score = score;
    }

    public List<String> getUserAnswers() {
        return UserAnswers;
    }

    public void setUserAnswers(List<String> userAnswers) {
        UserAnswers = userAnswers;
    }

    public List<Integer> getUserScores() {
        return UserScores;
    }

    public void setUserScores(List<Integer> userScores) {
        UserScores = userScores;
    }

    public int getFavTime() {
        return FavTime;
    }

    public void setFavTime(int favTime) {
        FavTime = favTime;
    }

    public int getViewCount() {
        return ViewCount;
    }

    public void setViewCount(int viewCount) {
        ViewCount = viewCount;
    }

    public int getDownCount() {
        return DownCount;
    }

    public void setDownCount(int downCount) {
        DownCount = downCount;
    }

    public int getRealCount() {
        return RealCount;
    }

    public void setRealCount(int realCount) {
        RealCount = realCount;
    }

    public int getPaperCount() {
        return PaperCount;
    }

    public void setPaperCount(int paperCount) {
        PaperCount = paperCount;
    }
}
