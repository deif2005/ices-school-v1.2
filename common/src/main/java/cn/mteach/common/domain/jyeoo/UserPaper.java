package cn.mteach.common.domain.jyeoo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuliangpu on 2017/2/14.
 */
public class UserPaper implements Serializable {
    private static final long serialVersionUID = -1637170455587018614L;

    private String ID;//组卷标识
    private String Title;//组卷名称
    private String Description;//组卷描述
    private float Score;//分值
    private long Times;//时间
    private int Count;//点击次数
    private String Flag;//是否公开
    private String Subject;//试卷学科
    private List<Groups> Groups;//试题分组

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float score) {
        Score = score;
    }

    public long getTimes() {
        return Times;
    }

    public void setTimes(long times) {
        Times = times;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public List<Groups> getGroups() {
        return Groups;
    }

    public void setGroups(List<Groups> groups) {
        Groups = groups;
    }
}

