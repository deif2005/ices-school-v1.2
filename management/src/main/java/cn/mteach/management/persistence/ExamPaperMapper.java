package cn.mteach.management.persistence;

import cn.mteach.common.domain.exam.ExamPaper;
import cn.mteach.common.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamPaperMapper {

	public List<ExamPaper> getExamPaperList(@Param("searchStr") String searchStr,@Param("paperType") String paperType, @Param("page") Page<ExamPaper> page);

	/**
	 * 导入试卷信息
	 * @param examPaper
	 */
	public void insertExamPaper(ExamPaper examPaper);
	
	public ExamPaper getExamPaperById(int examPaperId);

	public ExamPaper getExamPaperByName(String examPaperName);
	
	public void updateExamPaper(ExamPaper examPaper);
	
	public void deleteExamPaper(int id);
	
	public List<ExamPaper> getEnabledExamPaperList(@Param("userName") String userName,@Param("page") Page<ExamPaper> page);

	/**
	 * 获取最大试卷序列号
	 */
	public Integer getMaxExamPaperId();

}
