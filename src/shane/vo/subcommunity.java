package shane.vo;

import java.util.Date;

public class subcommunity {

	private int sid;
	private String name;
	private String describe;
	private int ArtNum;
	private Date CreTime;
	private boolean status;
	
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getSid() {
		return sid;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getDescribe() {
		return describe;
	}

	public void setArtNum(int artNum) {
		ArtNum = artNum;
	}
	public int getArtNum() {
		return ArtNum;
	}
	
	public void setCreTime(Date creTime) {
		CreTime = creTime;
	}
	public Date getCreTime() {
		return CreTime;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean getStatus() {
		return status;
	}

	
}
