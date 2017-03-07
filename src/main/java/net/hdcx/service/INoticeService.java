package net.hdcx.service;

import net.hdcx.bean.Notice;

import java.util.List;

/**
 * 公告服务功能接口
 * Created by Kevin on 2017/3/7.
 */
public interface INoticeService {
	/**
	 * 发布公告
	 * @param notice 公告
	 */
	void publishNotice(Notice notice);

	/**
	 * 删除公告
	 * @param notice 公告
	 */
	void removeNotice(Notice notice);

	/**
	 * 修改公告
	 * @param orignNotice 原公告
	 * @param newNotice 新公告
	 */
	void changeContent(String orignNotice, String newNotice);

	/**
	 * 查看有效公告
	 * @return 公告列表
	 */
	List<Object[]> checkNotices();
}
