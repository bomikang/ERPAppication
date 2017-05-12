package kr.or.dgit.bigdata.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.bigdata.dto.Department;
import kr.or.dgit.bigdata.dto.Title;
import kr.or.dgit.bigdata.mappers.TitleMapper;
import kr.or.dgit.bigdata.util.MyBatisSqlSessionFactory;

public class TitleService {
	private static final TitleService instance = new TitleService();

	public static TitleService getInstance() {
		return instance;
	}
	
	public List<Title> selectAllTitle(){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try{
			TitleMapper mapper = session.getMapper(TitleMapper.class);
			return mapper.selectAllTitle();
		}finally{
			session.close();
		}
	}
	
	public Title selectOneTitle(int tcode){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try{
			TitleMapper mapper = session.getMapper(TitleMapper.class);
			return mapper.selectOneTitle(tcode);
		}finally{
			session.close();
		}
	}
	
	public int getLastTcode(){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try{
			TitleMapper mapper = session.getMapper(TitleMapper.class);
			List<Title> list = selectAllTitle();
			int res = list.get(list.size()-1).getTcode()+1;
			return res;
		}finally{
			session.close();
		}
	}
	
	public int insertTitle(Title title){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try {
			TitleMapper mapper = session.getMapper(TitleMapper.class);
			int res = mapper.insertTitle(title);
			session.commit();
			return res;
		} finally {
			session.close();
		}
	}
	
	public int deleteTitle(int tcode){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try {
			TitleMapper mapper = session.getMapper(TitleMapper.class);
			int res = 0;
			try{
				res = mapper.deleteTitle(tcode);
				session.commit();
			}catch(Exception e){
				
			}
			return res;
		} finally {
			session.close();
		}
	}
	
	public int updateTitle(Title title){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		
		try {
			TitleMapper mapper = session.getMapper(TitleMapper.class);
			int res = mapper.updateTitle(title);
			session.commit();
			return res;
		}finally{
			session.close();
		}
	}
}
