package kr.or.dgit.setting;

public class Config {
	public static final String USER   = "root";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String DB_NAME = "ncs_erp_kbm";
	public static final String PJT_USER = "user_ncs";
	public static final String PJT_PASSWD = "user_ncs";

	public static final String[] TABLE_NAME = {"title", "department", "employee"};
	
	public static final String EXPORT_DIR = System.getProperty("user.dir")+ "\\BackupFiles\\";
	public static final String IMPORT_DIR = System.getProperty("user.dir")+ "\\DataFiles\\";
	public static final String MYSQL_EXPORT_PATH = "C:\\ProgramData\\MySQL\\MySQL Server 5.6\\Uploads\\";
	
	public static final String[] CREATE_SQL ={
			//직책
			"CREATE TABLE title ("
				+ "tcode INTEGER     NOT NULL Auto_increment, "
				+ "tname VARCHAR(10) null, "
				+ "CONSTRAINT PK_title PRIMARY KEY (tcode)) ",
			
			//부서
			"CREATE TABLE ncs_erp_kbm.department ("
				+ "dcode INTEGER  NOT NULL Auto_increment, "
				+ "dname CHAR(10) NOT NULL, "
				+ "floor INTEGER  null, "
				+ "CONSTRAINT PK_department PRIMARY KEY (dcode))",
				
			//사원
			"CREATE TABLE ncs_erp_kbm.employee ("
				+ "eno      INTEGER     NOT NULL Auto_increment, "
				+ "ename    VARCHAR(20) NOT NULL, "
				+ "salary   INTEGER     NULL, "
				+ "dno      INTEGER     NULL, "
				+ "gender   TINYINT(1)  NULL, "
				+ "joindate DATE        NULL, "
				+ "title    INTEGER     null, "
				+ "CONSTRAINT PK_employee PRIMARY KEY (eno), "
				+ "CONSTRAINT FK_title_TO_employee FOREIGN KEY (title) REFERENCES ncs_erp_kbm.title (tcode), "
				+ "CONSTRAINT FK_department_TO_employee FOREIGN KEY (dno) REFERENCES ncs_erp_kbm.department (dcode))"

	};
}
