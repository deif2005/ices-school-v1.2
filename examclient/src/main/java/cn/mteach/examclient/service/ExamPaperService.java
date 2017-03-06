package cn.mteach.examclient.service;


import cn.mteach.common.domain.exam.ExamPaper;

public interface ExamPaperService {
	
	/**
	 * 获取一张试卷
	 * @param examPaperId
	 * @return
	 */
	public ExamPaper getExamPaperById(int examPaperId);
	
	
}
