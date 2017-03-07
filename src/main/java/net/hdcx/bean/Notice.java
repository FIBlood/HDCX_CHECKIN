package net.hdcx.bean;

/**
 * 公告DAO
 * Created by Kevin on 2017/2/27.
 */
public class Notice {
	private String publisher;//发布人
	private String publishTime;//发布时间
	private String content;//发布内容
	private String deadline;//截止时间

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
}
