package cn.mteach.common.domain.jyeoo;

import java.util.Date;

/**
 * Created by wuliangpu on 2017/2/17.
 */
public class QuestionLevel {
    private String levelId;
    private String LevelName;
    private Date createTime;
    private int state;

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return LevelName;
    }

    public void setLevelName(String levelName) {
        LevelName = levelName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
