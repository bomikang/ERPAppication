package kr.or.dgit.bigdata;


import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.bigdata.dto.Department;
import kr.or.dgit.bigdata.service.DepartmentService;

public class DeptServiceTest {
	private static DepartmentService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = DepartmentService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

//	@Test
	public void selectAllDeptTest() {
		List<Department> list = service.selectAllDept();
		Assert.assertNotNull(list);
	}
	
//	@Test
	public void selectOneDeptTest() {
		Department dept = service.selectOneDept(1);
		Assert.assertNotNull(dept);
		System.out.println(dept.getDname());
	}
	
//	@Test
	public void getLastEnoTest() {
		int res = service.getLastDcode();
		Assert.assertEquals(7, res);
	}

//	@Test
	public void insertDeptTest(){
		Department dept = new Department(0, "대출", 5);
		int res = service.insertDept(dept);
		Assert.assertEquals(1, 1);
	}
	
//	@Test
	public void deleteDeptTest(){
		int res = service.deleteDept(7);
		Assert.assertEquals(1, res);
	}
	
//	@Test
	public void updateDeptTest(){
		Department oldD = service.selectOneDept(8);
		oldD.setDname("법무");
		
		service.updateDept(oldD);
		
		Department newD = service.selectOneDept(8);
		
		Assert.assertEquals("법무", newD.getDname());
	}
}
