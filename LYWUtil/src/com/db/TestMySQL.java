package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMySQL {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/lyw?useUnicode=true&characterEncoding=UTF-8";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "123456";

	/**
	 * 
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void main(String[] args) {
		String sqlText = "select * from testext";
		String sqlinsert = "insert into testext (id, name, age) values (3, '梁煜文', '23')";
		String sqlupdate = "update testext set id=2, name='建伟', age='21' where name='梁煜文'";
//		TestMySQL tmsql = new TestMySQL();
		Connection connection = getConnection();
		try {
			Statement stmt = connection.createStatement();
//			stmt.execute(sqlupdate);
			stmt.execute(sqlinsert);
			PreparedStatement ps = connection.prepareStatement(sqlText);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				System.out.println("id:" + res.getInt("id") + "===" + "name:"
						+ res.getString("name") + "===" + "age:"
						+ res.getInt("age"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

