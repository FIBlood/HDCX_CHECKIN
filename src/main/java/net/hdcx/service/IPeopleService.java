package net.hdcx.service;

/**
 * 人事管理服务功能接口
 * Created by Kevin on 2017/3/7.
 */
public interface IPeopleService {

	/**
	 * 签到
	 * @param o 部长和干事中某一对象
	 */
	void checkin(Object o);

	/**
	 * 签退
	 * @param o 部长和干事中某一对象
	 */
	void checkout(Object o);

	/**
	 * 请假
	 * @param o 部长和干事中某一对象
	 */
	void askForLeave(Object o);

	/**
	 * 记录迟到
	 * @param o 部长和干事中某一对象
	 */
	void recordLate(Object o);

	/**
	 * 记录旷班
	 * @param o 部长和干事中某一对象
	 */
	void recordAbsent(Object o);

	/**
	 * 添加成员(部长，干事)
	 * @param o 部长和干事中某一对象
	 */
	void addPeople(Object o);
}
