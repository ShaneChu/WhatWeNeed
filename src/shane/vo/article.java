package shane.vo;

import java.util.Date;

public class article {

	private int aid;
	private String topic;
	private String content;
	private int revNum;
	private Date CreTime;
	private int lastReUser;
	private Date lastReTime;
	private boolean isTop;
	private int userid;
	private int communityid;
	private boolean isOpen;
	
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getAid() {
		return aid;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public void setRevNum(int revNum) {
		this.revNum = revNum;
	}
	public int getRevNum() {
		return revNum;
	}
	public Date getCreTime() {
		return CreTime;
	}
	public void setCreTime(Date creTime) {
		CreTime = creTime;
	}
	
	public int getLastReUser() {
		return lastReUser;
	}
	public void setLastReUser(int lastReUser) {
		this.lastReUser = lastReUser;
	}
	
	public Date getLastReTime() {
		return lastReTime;
	}
	public void setLastReTime(Date lastReTime) {
		this.lastReTime = lastReTime;
	}
	
	public boolean isTop() {
		return isTop;
	}
	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getCommunityid() {
		return communityid;
	}
	public void setCommunityid(int communityid) {
		this.communityid = communityid;
	}
	
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public boolean isOpen() {
		return isOpen;
	}

	
}
