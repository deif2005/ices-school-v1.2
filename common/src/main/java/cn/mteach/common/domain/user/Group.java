package cn.mteach.common.domain.user;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.crypto.Data;

@XmlRootElement
public class Group implements Serializable {

	private static final long serialVersionUID = -166573023634513538L;
	private int groupId;
	private String groupName;
	private int userId;
	private boolean defaultt;
	private Timestamp createTime;
	public boolean isDefaultt() {
		return defaultt;
	}
	public void setDefaultt(boolean defaultt) {
		this.defaultt = defaultt;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
