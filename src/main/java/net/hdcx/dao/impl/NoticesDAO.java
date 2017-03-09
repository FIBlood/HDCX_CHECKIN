package net.hdcx.dao.impl;

import net.hdcx.bean.Notice;
import net.hdcx.dao.INoticesDAO;
import net.hdcx.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 公告操作相关的DAO
 * Created by Kevin on 2017/3/7.
 */
public class NoticesDAO implements INoticesDAO{
	private QueryRunner qr = new QueryRunner(DBUtils.getDataSource());

	@Override
	public int addNotice(Notice notice, int indate) {
		int affectedRows = 0;
		String sql = "insert into notices values(?, ?, now(), date_add(now(), interval ? day))";
		try {
			affectedRows = qr.update(sql, notice.getPublisher(), notice.getContent(), indate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public int removeNotice(Notice notice) {
		int affectedRows = 0;
		String sql = "delete from notices where content=?";
		try {
			affectedRows = qr.update(sql, notice.getContent());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public int updateNotice(String orignNotice, String newNotice) {
		int affectedRows = 0;
		String sql = "update notices set content=? where content=?";
		try {
			affectedRows = qr.update(sql, newNotice, orignNotice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public List<Map<String, Object>> getNotices() {
		List<Map<String, Object>> resultMap = null;
		String sql = "select publisher, content, date_format(publishTime, '%Y/%m/%d %H:%i') as publishTime, date_format(deadline, '%Y/%m/%d %H:%i') as deadline from notices where now()<deadline";
		try {
			resultMap = qr.query(sql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
