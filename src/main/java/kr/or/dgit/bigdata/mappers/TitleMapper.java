package kr.or.dgit.bigdata.mappers;

import java.util.List;

import kr.or.dgit.bigdata.dto.Title;

public interface TitleMapper {
	List<Title> selectAllTitle();
	Title selectOneTitle(int tcode);
	int getLastTcode();
	int insertTitle(Title title);
	int deleteTitle(int tcode);
	int updateTitle(Title title);
}
