package cn.mteach.common.domain.jyeoo;

/**
 * Created by wuliangpu on 2017/2/16.
 */
public class Grade {
    private String gradeId;
    private String gradeName;
    private int state;
    /**
     * api中的key
     */
    private String keyId;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
}
