package cn.mteach.common.domain.exam;

import java.util.HashMap;
import java.util.Map;

public class PaperCreatorParam {

	/**
	 * 导入试卷时的文件名
	 */
	private String fileName;
	/**
	 * 试卷名
	 */
	private String paperName;
	/**
	 * 试题类型数量
	 */
	private HashMap<Integer,Integer> questionTypeNum;
	/**
	 * 试题类型分数
	 */
	private HashMap<Integer,Float> questionTypePoint;
	/**
	 * 试题知识点比例
	 */
	private HashMap<Integer,Float> questionKnowledgePointRate;
	/**
	 * 试题难度系数
	 */
	private Float paperDifficulty;
	/**
	 * 及格分数
	 */
	private int passPoint;
	/**
	 * 试卷持续时间
	 */
	private int time;
	/**
	 * 试卷总分
	 */
	private float paperPoint;

	/**
	 * 试卷类别 0:真题 1:模拟 2:专家
	 */
	private int paperType;

	/**
	 * 题库ID
	 * @return
	 */
	private int fieldId;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getPaperType() {
		return paperType;
	}
	public void setPaperType(int paperType) {
		this.paperType = paperType;
	}
	public float getPaperPoint() {
		return paperPoint;
	}
	public void setPaperPoint(float paperPoint) {
		this.paperPoint = paperPoint;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public HashMap<Integer, Integer> getQuestionTypeNum() {
		return questionTypeNum;
	}
	public void setQuestionTypeNum(HashMap<Integer, Integer> questionTypeNum) {
		this.questionTypeNum = questionTypeNum;
	}
	public HashMap<Integer, Float> getQuestionTypePoint() {
		return questionTypePoint;
	}
	public void setQuestionTypePoint(HashMap<Integer, Float> questionTypePoint) {
		this.questionTypePoint = questionTypePoint;
	}
	public HashMap<Integer, Float> getQuestionKnowledgePointRate() {
		return questionKnowledgePointRate;
	}
	public void setQuestionKnowledgePointRate(
			HashMap<Integer, Float> questionKnowledgePointRate) {
		this.questionKnowledgePointRate = questionKnowledgePointRate;
	}
	public Float getPaperDifficulty() {
		return paperDifficulty;
	}
	public void setPaperDifficulty(Float paperDifficulty) {
		this.paperDifficulty = paperDifficulty;
	}
	public int getPassPoint() {
		return passPoint;
	}
	public void setPassPoint(int passPoint) {
		this.passPoint = passPoint;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
}
