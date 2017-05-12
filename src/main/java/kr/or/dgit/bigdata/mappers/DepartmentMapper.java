package kr.or.dgit.bigdata.mappers;

import java.util.List;

import kr.or.dgit.bigdata.dto.Department;

public interface DepartmentMapper {
	List<Department> selectAllDept();
	Department selectOneDept(int dcode);
	int getLastDcode();
	int insertDept(Department dept);
	int deleteDept(int dcode);
	int updateDept(Department dept);
}
