package cn.mteach.management.service;

import cn.mteach.common.domain.jyeoo.*;
import cn.mteach.common.domain.question.Reference;
import cn.mteach.management.persistence.JyeooMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuliangpu on 2017/2/16.
 */
@Service("jyeooService")
public class JyeooServiceImpl implements JyeooService {
    @Autowired
    private JyeooMapper jyeooMapper;

    /**
     * 判断是否存在年级
     * @param gradeName
     * @return
     */
    public Boolean isExistGrade(String gradeName){
        List<Grade> list=jyeooMapper.searchGradeByName(gradeName);
        if(list.size()>0){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 添加年级
     * @param grade
     */
    public void addGrade(Grade grade){
        jyeooMapper.addGrade(grade);
    }

    /**
     * 查找版本
     * @param versionName
     * @param subjectName
     * @return
     */
    public Boolean isExistVersion(String versionName,String subjectName){
        List<Version> list=jyeooMapper.searchVersionByName(versionName,subjectName);
        if(list.size()>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 添加版本
     * @param version
     */
    public void addVersion(Version version){
        jyeooMapper.addVersion(version);
    }
    /**
     * 根据学科获得来源
     * @param subjectName
     * @return
     */
    public List<Reference> getSourceBySubject(String subjectName){
        return jyeooMapper.getSourceBySubject(subjectName);
    }
    /**
     * 根据学科获得版本
     * @param subjectName
     */
    public List<Version> getVersionBySubject(String subjectName){
        return jyeooMapper.getVersionBySubject(subjectName);
    }

    /**
     * 获取题目难度
     */
    public List<QuestionLevel> getQuestionLevel(){
        return jyeooMapper.getQuestionLevel();
    }
    /**
     * 获取题目类别
     */
    public List<QuestionSort> getQuestionSort(){
        return jyeooMapper.getQuestionSort();
    }

    public void addJyPaper(JyPaper jyPaper){
        jyeooMapper.addJyPaper(jyPaper);
    }

}
