package net.hdcx.dao.impl;

import net.hdcx.bean.Member;
import net.hdcx.dao.IMembersDAO;
import net.hdcx.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据访问层
 *  	干事操作相关的DAO
 * Created by Kevin on 2017/3/5.
 */
public class MembersDAO implements IMembersDAO{
	private QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
	@Override
	public int addMember(Member member) {
		int affectedRows = 0;
		String sql = "insert into members(studentId, name, contact, email, dept, workWeek, workTime) values(?, ?, ?, ?, ?, ?)";
		try {
			affectedRows = qr.update(
					sql,
					member.getStudentId(),
					member.getName(),
					member.getContact(),
					member.getEmail(),
					member.getDept(),
					member.getWorkWeek(),
					member.getWorkTime()
			);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "人员信息添加失败");
		}
		return affectedRows;
	}

	@Override
	public int update(String field, String studentId) {
		int affectedRows = 0;
		String sql = "update members set "+field+"="+field+"+1 where studentId=?";
		try {
			affectedRows = qr.update(sql, studentId);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "操作失败，请联系管理员");
		}
		return affectedRows;
	}

	@Override
	public Member findMember(String studentId) {
		Member member = new Member();
		String sql = "select * from  members where studentId=?";
		try {
			member = qr.query(sql, new BeanHandler<Member>(Member.class), studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public int update(String sql, Object... params) {
		int affectedRows = 0;
		try {
			affectedRows = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "操作失败，请联系管理员");
		}
		return affectedRows;
	}

	@Override
	public List<Object[]> query(String sql, Object... params) {
		List<Object[]> resultList = null;
		try {
			resultList = qr.query(sql, new ArrayListHandler(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
