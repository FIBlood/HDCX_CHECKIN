package net.hdcx.bean;

/**
 * 留言信息DAO
 * Created by Kevin on 2017/2/27.
 */
public class Message {
	private String studentId;
	private String name;
	private String message;
	private String time;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
