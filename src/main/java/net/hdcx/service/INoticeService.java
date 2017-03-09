package net.hdcx.service;

import java.util.List;
import java.util.Map;

/**
 * 公告服务功能接口
 * Created by Kevin on 2017/3/7.
 */
public interface INoticeService {
	/**
	 * 发布公告
	 */
	void publishNotice();

	/**
	 * 删除公告
	 */
	void removeNotice();

	/**
	 * 修改公告
	 */
	void changeContent();

	/**
	 * 查看有效公告
	 * @return 公告列表
	 */
	List<Map<String, Object>> checkNotices();
}
