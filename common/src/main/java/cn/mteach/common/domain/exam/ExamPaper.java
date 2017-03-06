package cn.mteach.common.domain.exam;

import java.io.Serializable;
import java.util.Date;

public class ExamPaper implements Serializable {

	private static final long serialVersionUID = -3878176097815638534L;
	protected int id;
	protected String name;
	protected String content;
	protected int duration;
	protected int passPoint;
	protected float totalPoint;
	protected Date createTime;
	/**
	 * 0:默认 1：发布
	 */
	protected int status;
	protected String summary;
	protected boolean isVisible;
	protected int groupId;
	protected boolean isSubjective;
	protected String answerSheet;
	protected String creator;
	protected String paperType;
	protected int fieldId;
	protected int fieldName;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getPassPoint() {
		return passPoint;
	}

	public void setPassPoint(int passPoint) {
		this.passPoint = passPoint;
	}

	public float getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(float totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public boolean isSubjective() {
		return isSubjective;
	}

	public void setSubjective(boolean isSubjective) {
		this.isSubjective = isSubjective;
	}

	public String getAnswerSheet() {
		return answerSheet;
	}

	public void setAnswerSheet(String answerSheet) {
		this.answerSheet = answerSheet;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public int getFieldName() {
		return fieldName;
	}

	public void setFieldName(int fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public String toString() {
		return "ExamPaper [id=" + id + ", name=" + name + ", content="
				+ content + ", duration=" + duration + ", pass_point="
				+ passPoint + ", total_point=" + totalPoint
				+ ", create_time=" + createTime + ", status=" + status
				+ ", subjective=" + isSubjective + ", summary=" + summary
				+ ", is_visible=" + isVisible + ", group_id=" + groupId + "]";
	}
	
	
	
}
