package net.hdcx.dao.impl;

import net.hdcx.bean.Message;
import net.hdcx.dao.IMessageDAO;
import net.hdcx.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * 留言操作相关的DAO
 * Created by Kevin on 2017/3/7.
 */
public class MessageDAO implements IMessageDAO {

	private QueryRunner qr = new QueryRunner(DBUtils.getDataSource());

	@Override
	public int addMessage(Message message) {
		int affectedRows = 0;
		String sql = "insert into message values(?, ?, ?, now())";
		try {
			affectedRows = qr.update(sql, message.getStudentId(), message.getName(), message.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "留言失败，请联系管理员");
		}
		return affectedRows;
	}

	@Override
	public int removeMessage(String message) {
		int affectedRows = 0;
		String sql = "delete from message where message=?";
		try {
			affectedRows = qr.update(sql, message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public int updateMessage(String studentId, String message) {
		int affectedRows = 0;
		String sql = "update message set message=? where studentId=?";
		try {
			affectedRows = qr.update(sql, message, studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public List<Object[]> checkMessage() {
		List<Object[]> resultList = null;
		String sql = "select studentId, name, message, time from message";
		try {
			resultList = qr.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
