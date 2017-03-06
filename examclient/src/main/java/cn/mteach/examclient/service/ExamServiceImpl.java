package cn.mteach.examclient.service;

import cn.mteach.common.domain.exam.*;
import cn.mteach.common.util.Page;
import cn.mteach.common.util.StringUtil;
import cn.mteach.examclient.persistence.ExamMapper;
import cn.mteach.examclient.persistence.ExamPaperMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("examService")
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamMapper examMapper;
	@Autowired
	private ExamPaperMapper examPaperMapper;
	@Override
	public ExamHistory getUserExamHistBySeriNo(String seriNo,int approved) {
		
		return examMapper.getUserExamHistBySeriNo(seriNo,approved);
	}
	@Override
	public Exam getExamById(int examId) {
		
		return examMapper.getExamById(examId);
	}
	@Override
	public ExamHistory getUserExamHistByUserIdAndExamId(int userId, int examId, int ... approved) {
		if(approved != null && approved.length == 0)
			approved = null;
		return examMapper.getUserExamHistByUserIdAndExamId(userId, examId, approved);
	}
	@Override
	public int addUserExamHist(int userId,int examId,int examPaperId,int approved) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		try {
			ExamPaper examPaper = examPaperMapper.getExamPaperById(examPaperId);
			ExamHistory history = new ExamHistory();
			history.setExamId(examId);
			history.setExamPaperId(examPaperId);
			history.setContent(examPaper.getContent());
			history.setDuration(examPaper.getDuration());
			
			history.setApproved(approved);
			Date now = new Date();
			String seriNo = sdf.format(now) + StringUtil.format(userId, 3) + StringUtil.format(examId, 3);
			history.setSeriNo(seriNo);
			
			history.setUserId(userId);
			examMapper.addUserExamHist(history);
			return history.getHistId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getCause());
		}
	}
	@Override
	public ExamHistory getUserExamHistListByHistId(int histId) {
		
		return examMapper.getUserExamHistListByHistId(histId);
	}
	@Override
	public List<Exam> getExamListToApply(int userId, Page<Exam> page) {
		
		return examMapper.getExamListToApply(userId, page);
	}
	@Override
	public List<Exam> getExamListToStart(int userId, Page<Exam> page, int ... typeIdList) {
		
		if(typeIdList != null && typeIdList.length == 0)
			typeIdList = null;
		return examMapper.getExamListToStart(userId, typeIdList, page);
	}
	@Override
	public List<Exam> getExamList(Page<Exam> page, int... typeIdList) {
		
		if(typeIdList != null && typeIdList.length == 0)
			typeIdList = null;
		return examMapper.getExamList(typeIdList,page);
	}
	@Override
	public List<ExamHistory> getUserExamHistByUserId(int userId, Page<ExamHistory> page, int... typeIdList) {
		// TODO Auto-generated method stub
		if(typeIdList != null && typeIdList.length == 0)
			typeIdList = null;
		return examMapper.getUserExamHistByUserId(userId, typeIdList, page);
	}

	@Override
	public void calculateScore(AnswerSheet answerSheet) {
		int approved = 3; //全部是客观题，则状态更改为已阅卷
		Gson gson = new Gson();
		ExamPaper examPaper = examPaperMapper.getExamPaperById(answerSheet.getExamPaperId());
		AnswerSheet paperAnswerSheet = gson.fromJson(examPaper.getAnswerSheet(),AnswerSheet.class);
		HashMap<Integer,AnswerSheetItem> answerMap = new HashMap<Integer,AnswerSheetItem>();
		for(AnswerSheetItem item : paperAnswerSheet.getAnswerSheetItems()){
			answerMap.put(item.getQuestionId(), item);
			if(item.getQuestionTypeId() != 1 && item.getQuestionTypeId() != 2 && item.getQuestionTypeId() != 3){
				approved = 2; //有主观题标记为没有完成阅卷
			}
		}
		//计算题目分值
		answerSheet.setPointMax(paperAnswerSheet.getPointMax());
		for(AnswerSheetItem answerSheetItem : answerSheet.getAnswerSheetItems()){
			AnswerSheetItem answerMapItem = answerMap.get(answerSheetItem.getQuestionId());
			if(answerSheetItem.getAnswer().equals(answerMapItem.getAnswer())){
				//计算总分
				answerSheet.setPointRaw(answerSheet.getPointRaw() + answerMapItem.getPoint() );
				//设置每个题目的得分
				answerSheetItem.setPoint(answerMapItem.getPoint());
				//标记题目为已评分
				answerSheetItem.setRight(true);
			}
		}
		examMapper.updateUserExamHist(answerSheet, gson.toJson(answerSheet),approved);
	}

}
