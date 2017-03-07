package net.hdcx.dao;

import net.hdcx.bean.Message;

import java.util.List;

/**
 * 留言操作的DAO
 * Created by Kevin on 2017/3/6.
 */
public interface IMessageDAO {
	/**
	 * 添加留言
	 * @param message 留言对象
	 * @return 影响的行数
	 */
	int addMessage(Message message);

	/**
	 * 根据留言的内容删除留言
	 * @param message 留言
	 * @return 影响的行数
	 */
	int removeMessage(String message);

	/**
	 * 修改留言
	 * @param message 留言
	 * @param studentId 学号
	 * @return 影响的行数
	 */
	int updateMessage(String studentId, String message);

	/**
	 * 查看所有留言
	 * @return 留言列表
	 */
	List<Object[]> checkMessage();
}
