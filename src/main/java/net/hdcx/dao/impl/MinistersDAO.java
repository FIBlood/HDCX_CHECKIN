package net.hdcx.dao.impl;

import net.hdcx.bean.Minister;
import net.hdcx.dao.IMinistersDAO;
import net.hdcx.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 部长操作相关的DAO
 * Created by Kevin on 2017/3/6.
 */
public class MinistersDAO implements IMinistersDAO {
	private QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
	@Override
	public int addMinister(Minister minister) {
		int affectedRows = 0;
		String sql = "insert into ministers(studentId, name, contact, email, dept, workWeek, workTIme)";
		try {
			affectedRows = qr.update(
					sql,
					minister.getStudentId(),
					minister.getName(),
					minister.getContact(),
					minister.getEmail(),
					minister.getDept(),
					minister.getWorkWeek(),
					minister.getWorkTime()
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public Minister findMinister(String studentId) {
		Minister minister = null;
		String sql = "select * from ministers where studentId=?";
		try {
			minister = qr.query(sql, new BeanHandler<Minister>(Minister.class), studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return minister;
	}

	@Override
	public int update(String field, String studentId) {
		int affectedRows = 0;
		String sql = "update ministers set " + field + "=" + field + "+1 " + "where studentId=?";
		try {
			affectedRows = qr.update(sql, studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public int update(String sql, Object... params) {
		int affectedRows = 0;
		try {
			affectedRows = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public List<Object[]> query(String sql, Object... params){
		List<Object[]> resultList = null;
		try {
			resultList = qr.query(sql, new ArrayListHandler(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
