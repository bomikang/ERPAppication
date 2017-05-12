package kr.or.dgit.setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.or.dgit.setting.Config;
import kr.or.dgit.setting.jdbc.DBCon;
import kr.or.dgit.setting.jdbc.JdbcUtil;

public class DataBaseDao {
	private PreparedStatement pstmt;
	
	private static DataBaseDao instance = new DataBaseDao();

	private DataBaseDao() {}

	public static DataBaseDao getInstance() {
		return instance;
	}
	
	public void createDatabase() {
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("CREATE DATABASE " + Config.DB_NAME);
			pstmt.execute();
			System.out.println("Create Database " + Config.DB_NAME);
		} catch (SQLException e) {
			if (e.getErrorCode()==1007){
				dropDatabase();
				createDatabase();
			}
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private void dropDatabase() {
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("DROP DATABASE IF EXISTS " + Config.DB_NAME);
			pstmt.execute();
			System.out.println("Drop Database if exists " + Config.DB_NAME);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}

	public void selectUseDatabase() {
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("USE " + Config.DB_NAME);
			pstmt.execute();
			System.out.println("Use " + Config.DB_NAME);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}		
	}
	
	public void setForeignKeyCheck(int isCheck){
		try{
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("SET FOREIGN_KEY_CHECKS = ?");
			pstmt.setInt(1, isCheck);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
	}
}
