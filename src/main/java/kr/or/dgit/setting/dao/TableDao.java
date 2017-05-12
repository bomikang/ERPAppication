package kr.or.dgit.setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.or.dgit.setting.jdbc.DBCon;
import kr.or.dgit.setting.jdbc.JdbcUtil;


public class TableDao {
	private PreparedStatement pstmt;
	
	private static TableDao instance = new TableDao();

	private TableDao() {}

	public static TableDao getInstance() {
		return instance;
	}
	
	public void createTable(String sql) {
		Connection con = DBCon.getConnection();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
