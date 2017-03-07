package net.hdcx.dao;

import net.hdcx.bean.Minister;

import java.util.List;

/**
 * 部长操作相关的DAO
 * Created by Kevin on 2017/3/6.
 */
public interface IMinistersDAO {
	/**
	 * 添加一个Minister
	 * @param minister 部长Bean
	 * @return 影响的行数
	 */
	int addMinister(Minister minister);

	/**
	 * 根据studentId查找一个Minister
	 * @param studentId 学号
	 * @return A Minister instance
	 */
	Minister findMinister(String studentId);

	/**
	 * 更新某个字段的值
	 * @param studentId 学号
	 * @param field 字段
	 * @return 影响的行数
	 */
	int update(String field, String studentId);

	/**
	 * 通过sql进行增，删，改操作
	 * @param sql SQL语句
	 * @param params 参数
	 * @return 影响的行数
	 */
	int update(String sql, Object... params);

	/**
	 * 通过sql进行查询操作
	 * @param sql SQL语句
	 * @param params 参数
	 * @return 返回二维结果集
	 */
	List<Object[]> query(String sql, Object... params);
}
