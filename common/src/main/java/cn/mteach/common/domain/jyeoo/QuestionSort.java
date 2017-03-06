package cn.mteach.common.domain.jyeoo;

import java.util.Date;

/**
 * Created by wuliangpu on 2017/2/17.
 */
public class QuestionSort {

    private String sortId;
    private String sortName;
    private Date createTime;
    private int state;

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
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
