package net.hdcx.service;

import java.util.List;

/**
 * 留言服务功能接口
 * Created by Kevin on 2017/3/7.
 */
public interface IMessageService {
	/**
	 * 留言
	 */
	void message();

	/**
	 * 删除留言
	 */
	void deleteMessage();

	/**
	 * 修改留言
	 */
	void changeMessage();

	/**
	 * 查看留言
	 */
	List<Object[]> checkMessage();
}
