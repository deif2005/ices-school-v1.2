package cn.mteach.common.util.word;

import java.util.List;

/**
 * Created by Sugior on 2016/12/8.
 */
public class BigQuestion {
    private String title;
    private String no;
    private String type;
    private String name;
    private String count;
    private String singleScore;
    private String totalScore;
    private String content;
    private String description;
    private List<SmallQuestion> questionList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(String singleScore) {
        this.singleScore = singleScore;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public List<SmallQuestion> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<SmallQuestion> questionList) {
        this.questionList = questionList;
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
}
