package cn.mteach.common.domain.jyeoo;


/**
 * Created by wuliangpu on 2017/2/20.
 */
public class JyPaper {
    /**
     * 试卷id
     */
    private String id;
    /**
     * 试卷标题
     */
    private String title;
    /**
     * 试卷描述
     */
    private String description;
    /**
     * 试卷试题内容
     */
    private String questionXml;
    /**
     * 试卷分数
     */
    private int score;
    /**
     * 查看次数
     */
    private int viewCount;
    /**
     * 下载次数
     */
    private int downCount;
    /**
     * 学科id
     */
    private int subject;
    /**
     * 创建者id
     */
    private String creatorId;
    /**
     * 创建日期
     */
    private String creatorDate;
    /**
     * 状态1正常  0废弃
     */
    private int state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestionXml() {
        return questionXml;
    }

    public void setQuestionXml(String questionXml) {
        this.questionXml = questionXml;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getDownCount() {
        return downCount;
    }

    public void setDownCount(int downCount) {
        this.downCount = downCount;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorDate() {
        return creatorDate;
    }

    public void setCreatorDate(String creatorDate) {
        this.creatorDate = creatorDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
