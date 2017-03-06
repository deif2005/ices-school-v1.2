package cn.mteach.management.persistence;

import cn.mteach.common.domain.jyeoo.*;
import cn.mteach.common.domain.question.Field;
import cn.mteach.common.domain.question.Reference;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wuliangpu on 2017/2/16.
 */
public interface JyeooMapper {

    public List<Grade> searchGradeByName(String gradeName);

    public void addGrade(Grade grade);

    public List<Version> searchVersionByName(@Param("versionName") String versionName,@Param("subjectName")String subjectName);

    public void addVersion(Version version);

    public List<Reference> getSourceBySubject(String subjectName);

    public List<Version> getVersionBySubject(String subjectName);

    public List<QuestionLevel> getQuestionLevel();

    public List<QuestionSort> getQuestionSort();

    public Field getFieldByKeyId(int keyId);

    public void addJyPaper(JyPaper jyPaper);
}
