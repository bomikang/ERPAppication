package kr.or.dgit.setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.or.dgit.setting.Config;
import kr.or.dgit.setting.jdbc.DBCon;
import kr.or.dgit.setting.jdbc.JdbcUtil;


public class UserDao {
	private static UserDao instance = new UserDao();
	
	private UserDao() {}

	public static UserDao getInstance() {
		return instance;
	}

	public void initUser(){
		createUser();
		grantUser();
	}
	
	private void createUser(){
		String sql = "create user ? identified by ?";
		PreparedStatement pstmt = null;
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Config.PJT_USER);
			pstmt.setString(2, Config.PJT_PASSWD);
			pstmt.execute();
		} catch (SQLException e) {
			if (e.getErrorCode()==1396){
				dropUser();
				createUser();
			}
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private void dropUser() {
		String sql = "drop user ?";
		PreparedStatement pstmt = null;
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Config.PJT_USER);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private void grantUser() {
		String sql = "grant select, insert, update, delete on " + Config.DB_NAME + ".* to ?";
		PreparedStatement pstmt = null;
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Config.PJT_USER);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
