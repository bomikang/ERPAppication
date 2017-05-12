package kr.or.dgit.bigdata.mappers;

import java.util.List;

import kr.or.dgit.bigdata.dto.Employee;

public interface EmployeeMapper {
	int insertEmp(Employee emp);
	List<Employee> selectAllEmp();
	int getLastEno();
	int deleteEmp(int eno);
	Employee selectOneEmp(int eno);
	int updateEmp(Employee emp);
}
