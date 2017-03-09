package net.hdcx.dao;

import net.hdcx.bean.Notice;

import java.util.List;
import java.util.Map;

/**
 * 公告操作的DAO接口
 * Created by Kevin on 2017/3/6.
 */
public interface INoticesDAO {

	/**
	 * 添加公告
	 * @param notice 公告对象
	 * @param indate 有效期
	 * @return 影响的行数
	 */
	int addNotice(Notice notice, int indate);

	/**
	 * 删除公告
	 *
	 * @param notce 公告对象
	 * @return 影响的行数
	 */
	int removeNotice(Notice notce);

	/**
	 * 更改公告内容
	 * @param orignNotice 原公告
	 * @param newNotice 新公告
	 * @return 影响的行数
	 */
	int updateNotice(String orignNotice, String newNotice);

	/**
	 * 获取有效公告列表
	 * @return 公告列表
	 */
	List<Map<String, Object>> getNotices();
}