package cn.mteach.common.domain.jyeoo;

/**
 * Created by defi on 2017-02-21.
 * 提取Jyeoo网的题目信息
 */
public class JyQuestion {
    private String id;
    private Integer subject; //科目
    private Integer cate; //题目类别代号
    private String label; //标签
    private String content;  //题干内容
    private String options;  //选项内容
    private String answer;   //标准答案
    private String quesfiles; //关联文件
    private Integer point;
    private Float degree;     //难度系数
    private Integer papertime;  //制卷次数
    private String questionJson;  //题信息json串

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public Integer getCate() {
        return cate;
    }

    public void setCate(Integer cate) {
        this.cate = cate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuesfiles() {
        return quesfiles;
    }

    public void setQuesfiles(String quesfiles) {
        this.quesfiles = quesfiles;
    }

    public Integer getPOINT() {
        return point;
    }

    public void setPOINT(Integer POINT) {
        this.point = POINT;
    }

    public Float getDegree() {
        return degree;
    }

    public void setDegree(Float degree) {
        this.degree = degree;
    }

    public Integer getPapertime() {
        return papertime;
    }

    public void setPapertime(Integer papertime) {
        this.papertime = papertime;
    }

    public String getQuestionJson() {
        return questionJson;
    }

    public void setQuestionJson(String questionJson) {
        this.questionJson = questionJson;
    }
}
