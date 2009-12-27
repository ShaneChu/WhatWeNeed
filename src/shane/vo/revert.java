package shane.vo;

import java.util.Date;

public class revert {

	private int rid;
	private String content;
	private Date CreTime;
	private int articleid;
	private int communityid;
	private int userid;
	

	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getRid() {
		return rid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreTime() {
		return CreTime;
	}
	public void setCreTime(Date creTime) {
		CreTime = creTime;
	}
	
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	public int getArticleid() {
		return articleid;
	}
	
	public void setCommunityid(int communityid) {
		this.communityid = communityid;
	}
	public int getCommunityid() {
		return communityid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getUserid() {
		return userid;
	}

	
}
