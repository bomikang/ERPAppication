package kr.or.dgit.bigdata.dto;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
	private int eno;
	private String ename;
	private int salary;
	private Department dept;
	private int gender;
	private Date joindate;
	private Title title;
	
	public Employee() {
	}

	public Employee(int eno, String ename, int salary, Department dept, int gender, Date joindate, Title title) {
		this.eno = eno;
		this.ename = ename;
		this.salary = salary;
		this.dept = dept;
		this.gender = gender;
		this.joindate = joindate;
		this.title = title;
	}
	
	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public static String getStrEno(int eno){
		return String.format("E%02d", eno);
	}//getStrEno
	
	public static String getStrDept(Department dept){
		return dept.getDname()+"("+dept.getFloor()+"층)";
	}//getStrDept
	
	public String getStrGender(){
		if (gender == 0) {
			return "남자";
		}else{
			return "여자";
		}
	}//getStrGender
	
	public static String getStrJoindate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String res = sdf.format(date);
		return res;
	}//getStrJoindate


	@Override
	public String toString() {
		return String.format("Employee [eno=%s, ename=%s, salary=%s, dept=%s, gender=%s, joindate=%s, title=%s]", eno,
				ename, salary, dept, gender, joindate, title);
	}

	public String[] toArray() {
		String strSalary = String.format("%,d", salary);
		return new String[]{getStrEno(eno), ename, title.getTname(), strSalary, getStrGender(), getStrDept(dept), getStrJoindate(joindate)};
	}
}
