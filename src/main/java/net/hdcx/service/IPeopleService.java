package net.hdcx.service;

import net.hdcx.bean.Member;
import net.hdcx.bean.Minister;

import java.util.List;

/**
 * 人事管理服务功能接口
 * Created by Kevin on 2017/3/7.
 */
public interface IPeopleService {

	/**
	 * 签到
	 */
	void checkin();

	/**
	 * 签退
	 */
	void checkout(String id);

	/**
	 * 请假
	 */
	void askForLeave();


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

	/**
	 * 查找干事
	 * @param sql 查询语句
	 * @param params 参数
	 * @return 干事列表
	 */
	List<Object[]> queryMembers(String sql, Object... params);

	/**
	 * 查找部长
	 * @param sql 查询语句
	 * @param params 参数
	 * @return 部长列表
	 */
	List<Object[]> queryMinisters(String sql, Object... params);

	/**
	 * 根据学号查找部长
	 * @param studentId 学号
	 * @return 部长对象
	 */
	Minister findMinisterById(String studentId);

	/**
	 * 根据学号查找干事
	 * @param studentId 学号
	 * @return 干事对象
	 */
	Member findMemberById(String studentId);
}
