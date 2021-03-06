package kr.or.dgit.setting.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import kr.or.dgit.setting.Config;

public class DBCon {
	private static final DBCon instance = new DBCon();
	private static Connection con;
	
	private DBCon() {
		try {
			Class.forName(Config.DRIVER); //jdbc드라이버 상태 확인
			con = DriverManager.getConnection(Config.URL + Config.DB_NAME, Config.USER, Config.PWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if (instance == null) {
			new DBCon();
		}
		return instance.con;
	}
	
	public static void closeConnection(){
		if (con != null){
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
