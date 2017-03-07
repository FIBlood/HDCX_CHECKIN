package net.hdcx.service;

import net.hdcx.bean.Message;

import java.util.List;

/**
 * 留言服务功能接口
 * Created by Kevin on 2017/3/7.
 */
public interface IMessageService {
	/**
	 * 留言
	 * @param message 留言
	 */
	void message(Message message);

	/**
	 * 删除留言
	 * @param message 留言
	 */
	void deleteMessage(Message message);

	/**
	 * 修改留言
	 * @param message 留言
	 * @param newMessage 新留言
	 */
	void changeMessage(Message message, String newMessage);

	/**
	 * 查看留言
	 */
	List<Object[]> checkMessage();
}
