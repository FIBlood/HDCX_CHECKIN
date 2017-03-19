package net.hdcx.utils;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接的工具类
 * 作用：
 *  	1.获取数据源
 *  	2.获取数据库连接对象
 * Created by Kevin on 2017/2/26.
 */
public class DBUtils {
	private static DataSource dataSource;

	static{
		Properties prop = new Properties();
		try {
			InputStream is = Class.class.getResourceAsStream("/properties/db.properties");
			prop.load(is);
			dataSource = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DataSource getDataSource(){
		return dataSource;
	}

	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return conn;
	}

	public static void close(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
