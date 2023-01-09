package edu.practice.vo;

public class BoardVO {
	
	private int bidx;
	private String title;
	private String content;
	private String wdate;
	private String hit;
	private int uidx;
	private String id;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBidx() {
		return bidx;
	}
	public void setBidx(int bidx) {
		this.bidx = bidx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public int getUidx() {
		return uidx;
	}
	public void setUidx(int uidx) {
		this.uidx = uidx;
	}
	
	@Override
	public String toString() {
		return "BoardVO [bidx=" + bidx + ", title=" + title + ", content=" + content + ", wdate=" + wdate + ", hit="
				+ hit + ", uidx=" + uidx + ", id=" + id + "]";
	}
	
	
	
	
	
	
	

}
