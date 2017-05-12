package kr.or.dgit.setting.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	public static void close(Statement pstmt){
		if (pstmt != null){
			try {
				pstmt.close();
				pstmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public static void close(ResultSet rs){
		if (rs != null){
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
}
