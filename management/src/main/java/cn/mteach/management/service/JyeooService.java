package cn.mteach.management.service;

import cn.mteach.common.domain.jyeoo.*;
import cn.mteach.common.domain.question.Reference;

import java.util.List;

/**
 * Created by wuliangpu on 2017/2/16.
 */
public interface JyeooService {
    /**
     * 判断是否存在年级
     * @param gradeName
     * @return
     */
    public Boolean isExistGrade(String gradeName);

    /**
     * 添加年级
     * @param grade
     */
    public void addGrade(Grade grade);
    /**
     * 查找版本
     * @param versionName
     * @param subjectName
     * @return
     */
    public Boolean isExistVersion(String versionName,String subjectName);
    /**
     * 添加版本
     * @param version
     */
    public void addVersion(Version version);
    /**
     * 根据学科获得来源
     * @param subjectName
     */
    public List<Reference> getSourceBySubject(String subjectName);
    /**
     * 根据学科获得版本
     * @param subjectName
     */
    public List<Version> getVersionBySubject(String subjectName);
    /**
     * 获取题目难度
     */
    public List<QuestionLevel> getQuestionLevel();
    /**
     * 获取题目类别
     */
    public List<QuestionSort> getQuestionSort();

    public void addJyPaper(JyPaper jyPaper);

}
