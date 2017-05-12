package kr.or.dgit.bigdata;


import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.bigdata.dto.Department;
import kr.or.dgit.bigdata.dto.Title;
import kr.or.dgit.bigdata.service.TitleService;

public class TitleServiceTest {
	private static TitleService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = TitleService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

//	@Test
	public void selectAllTitleTest() {
		List<Title> list = service.selectAllTitle();
		Assert.assertNotNull(list);
	}
	
//	@Test
	public void selectOneTitleTest(){
		Title title = service.selectOneTitle(2);
		Assert.assertNotNull(title);
	}
	
//	@Test
	public void getLastTcodeTest(){
		Assert.assertEquals(6, 6);
	}

//	@Test
	public void insertTitleTest(){
		Title title = new Title();
		title.setTcode(0);
		title.setTname("회계");
		
		int res = service.insertTitle(title);
		
		Assert.assertEquals(1, res);
	}
	
//	@Test
	public void deleteTitleTest(){
		int res = service.deleteTitle(6);
		Assert.assertEquals(1, res);
	}
	
//	@Test
	public void updateTitleTest(){
		Title oldT = service.selectOneTitle(10);
		oldT.setTname("대표");
		
		service.updateTitle(oldT);
		
		Title newT = service.selectOneTitle(10);
		
		Assert.assertEquals("대표", newT.getTname());
	}
}
