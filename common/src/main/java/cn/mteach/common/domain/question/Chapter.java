package cn.mteach.common.domain.question;

import cn.mteach.common.domain.jyeoo.ChapterDetail;

import java.util.List;

/**
 * Created by defi on 2017-02-14.
 */
public class Chapter {
    private String chapterId;
    private String parentId;    //父章节
    private String chapterName; //章节名
    private Integer subject;  //科目
    private String subjectName; //科目名
    private Integer termId;  //学期
    private Integer gradeId; //年级
    private Integer editionId; //版本
    private int state;
    private List<ChapterDetail.Points> points; //知识点

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getEditionId() {
        return editionId;
    }

    public void setEditionId(Integer editionId) {
        this.editionId = editionId;
    }

    public List<ChapterDetail.Points> getPoints() {
        return points;
    }

    public void setPoints(List<ChapterDetail.Points> points) {
        this.points = points;
    }
}
