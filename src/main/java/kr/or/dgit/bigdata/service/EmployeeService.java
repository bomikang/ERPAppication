package kr.or.dgit.bigdata.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.bigdata.dto.Employee;
import kr.or.dgit.bigdata.mappers.EmployeeMapper;
import kr.or.dgit.bigdata.util.MyBatisSqlSessionFactory;

public class EmployeeService {
	private static final EmployeeService instance = new EmployeeService();

	public static EmployeeService getInstance() {
		return instance;
	}
	
	public int insertEmp(Employee emp){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try{
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			int res = mapper.insertEmp(emp);
			session.commit();
			return res;
		}finally{
			session.close();
		}
	}
	
	public List<Employee> selectAllEmp(){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try{
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			List<Employee> list = mapper.selectAllEmp();
			return list;
		}finally{
			session.close();
		}
	}
	
	public Employee selectOneEmp(int eno){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			Employee emp = mapper.selectOneEmp(eno);
			return emp;
		} finally {
			session.close();
		}
	}
	
	public int getLastEno(){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try{
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			List<Employee> list = selectAllEmp();
			int res = list.get(list.size()-1).getEno()+1;
			return res;
		}finally{
			session.close();
		}
	}
	
	public int deleteEmp(int eno){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try{
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			int res = mapper.deleteEmp(eno);
			session.commit();
			return res;
		}finally{
			session.close();
		}
	}
	
	public int updateEmp(Employee emp){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			int res = mapper.updateEmp(emp);
			session.commit();
			return res;
		} finally {
			session.close();
		}
	}
}
