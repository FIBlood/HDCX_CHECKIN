package net.hdcx.bean;

/**
 * 部长的bean对象
 * Created by Kevin on 2017/2/27.
 */
public class Minister {
	private String studentId;//学号
	private String name;//姓名
	private String contact;//联系电话
	private String email;//邮箱地址
	private String dept;//所在部门
	private String workWeek;//值班周期
	private String workTime;//班次
	private int mountOfCheckin;//签到次数
	private int mountOfCheckout;//签退次数
	private int mountOfAskForLeave;//请假次数
	private int mountOfLate;//迟到次数
	private int mountOfAbsent;//旷班次数

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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getWorkWeek() {
		return workWeek;
	}

	public void setWorkWeek(String workWeek) {
		this.workWeek = workWeek;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public int getMountOfCheckin() {
		return mountOfCheckin;
	}

	public void setMountOfCheckin(int mountOfCheckin) {
		this.mountOfCheckin = mountOfCheckin;
	}

	public int getMountOfCheckout() {
		return mountOfCheckout;
	}

	public void setMountOfCheckout(int mountOfCheckout) {
		this.mountOfCheckout = mountOfCheckout;
	}

	public int getMountOfAskForLeave() {
		return mountOfAskForLeave;
	}

	public void setMountOfAskForLeave(int mountOfAskForLeave) {
		this.mountOfAskForLeave = mountOfAskForLeave;
	}

	public int getMountOfLate() {
		return mountOfLate;
	}

	public void setMountOfLate(int mountOfLate) {
		this.mountOfLate = mountOfLate;
	}

	public int getMountOfAbsent() {
		return mountOfAbsent;
	}

	public void setMountOfAbsent(int mountOfAbsent) {
		this.mountOfAbsent = mountOfAbsent;
	}
}
