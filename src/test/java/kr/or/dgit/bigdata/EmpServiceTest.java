package kr.or.dgit.bigdata;


import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.bigdata.dto.Department;
import kr.or.dgit.bigdata.dto.Employee;
import kr.or.dgit.bigdata.dto.Title;
import kr.or.dgit.bigdata.service.DepartmentService;
import kr.or.dgit.bigdata.service.EmployeeService;

public class EmpServiceTest {
	private static EmployeeService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = EmployeeService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

//	@Test
	public void insertEmpTest() {
		Employee emp = new Employee(0, "강보미", 2000000, new Department(), 1, new Date(), new Title());
		int res = service.insertEmp(emp);
		Assert.assertEquals(1, res);
	}
	
//	@Test
	public void selectAllEmpTest() {
		List<Employee> list =  service.selectAllEmp();
		for (Employee emp : list) {
			System.out.println(emp.getStrEno(emp.getEno()));
			System.out.println(emp.getEname());
			System.out.println(emp.getDept().getDcode());
			System.out.println(emp.getTitle().getTcode());
		}
		Assert.assertNotNull(list);
	}
	
//	@Test
	public void getLastEnoTest() {
		int res = service.getLastEno();
		Assert.assertEquals(3, res);
	}
	
//	@Test
	public void selectOneEmpTest() {
		Employee emp = service.selectOneEmp(17016);
		Assert.assertNotNull(emp);
		System.out.println(emp.getEname());
	}
	
//	@Test
	public void deleteEmpTest() {
		int res = service.deleteEmp(17015);
		Assert.assertNull(service.selectOneEmp(17015));
	}
	
	@Test
	public void updateEmpTest(){
		Employee old = service.selectOneEmp(17019);
		old.setEname("강보미");
		
		service.updateEmp(old);
		
		Employee newEmp = service.selectOneEmp(17019);
		
		Assert.assertEquals("강보미", newEmp.getEname());
	}
}
