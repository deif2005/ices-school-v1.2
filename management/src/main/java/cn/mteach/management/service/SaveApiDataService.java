package cn.mteach.management.service;

import cn.mteach.common.domain.exam.Paper;
import cn.mteach.common.domain.jyeoo.UserPaper;

import java.util.List;

/**
 * Created by wuliangpu on 2017/2/17.
 */
public interface SaveApiDataService {

    /**
     * 保存api中的学科
     */
    public void saveSubjectFromApi();

    /**
     * 保存api中所有来源
     */
    public void saveSourceFromApi();

    /**
     * 保存api中 年级
     */
    public void saveGradeFromApi();

    /**
     * 保存api中所有版本
     */
    public void saveVersionFromApi();
    /**
     * 查找组卷
     * @param subjectName 学科英文名称
     * @param tp 分类（0：最新组卷；1：最热组卷；2：用户组卷）
     * @param pi 当前页（以1开始）
     * @param ps 每页记录数
     */
    public List<UserPaper> savePaperFromPai(String subjectName,int tp,int pi,int ps);


}
