package kr.or.dgit.bigdata.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.bigdata.dto.Department;
import kr.or.dgit.bigdata.dto.Employee;
import kr.or.dgit.bigdata.mappers.DepartmentMapper;
import kr.or.dgit.bigdata.mappers.EmployeeMapper;
import kr.or.dgit.bigdata.util.MyBatisSqlSessionFactory;

public class DepartmentService{
	private static final DepartmentService instance = new DepartmentService();

	public static DepartmentService getInstance() {
		return instance;
	}

	public List<Department> selectAllDept() {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try{
			DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
			return mapper.selectAllDept();
		}finally{
			session.close();
		}
	}
	
	public Department selectOneDept(int dcode) {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try{
			DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
			return mapper.selectOneDept(dcode);
		}finally{
			session.close();
		}
	}
	
	public int getLastDcode(){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try{
			DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
			List<Department> list = selectAllDept();
			int res = list.get(list.size()-1).getDcode()+1;
			return res;
		}finally{
			session.close();
		}
	}
	
	public int insertDept(Department dept){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try {
			DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
			int res = mapper.insertDept(dept);
			session.commit();
			return res;
		} finally {
			session.close();
		}
	}
	
	public int deleteDept(int dcode){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try {
			DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
			int res = 0;
			
			try{
				res = mapper.deleteDept(dcode);
				session.commit();
			}catch(Exception e){
				
			}
			return res;
		} finally {
			session.close();
		}
	}
	
	public int updateDept(Department dept){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try {
			DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
			int res = mapper.updateDept(dept);
			session.commit();
			return res;
		} finally {
			session.close();
		}
	}
}
