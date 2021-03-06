package cn.mteach.common.domain.training;

import java.sql.Timestamp;

public class Training {

	private int trainingId;
	private String trainingName;
	private String trainingDesc;
	private Timestamp createTime;
	private int status;
	private int creatorId;
	private int fieldId;
	private String creatorName;
	private Timestamp stateTime;
	private Timestamp expTime;
	private boolean privatee;
	public boolean isPrivatee() {
		return privatee;
	}
	public void setPrivatee(boolean privatee) {
		this.privatee = privatee;
	}
	public int getFieldId() {
		return fieldId;
	}
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
	public int getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public String getTrainingDesc() {
		return trainingDesc;
	}
	public void setTrainingDesc(String trainingDesc) {
		this.trainingDesc = trainingDesc;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public Timestamp getStateTime() {
		return stateTime;
	}
	public void setStateTime(Timestamp stateTime) {
		this.stateTime = stateTime;
	}
	public Timestamp getExpTime() {
		return expTime;
	}
	public void setExpTime(Timestamp expTime) {
		this.expTime = expTime;
	}
	
}
