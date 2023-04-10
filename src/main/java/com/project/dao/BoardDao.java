package com.project.dao;

import java.util.List;

import com.project.dto.BoardDto;
import com.project.vo.PageMaker;

public interface BoardDao {
	public void create(BoardDto dto) throws Exception;
	public BoardDto read(int bno) throws Exception;
	public void update(BoardDto dto) throws Exception;
	public void delete(int bno) throws Exception;
	public List<BoardDto> listSearch(PageMaker pm) throws Exception;
	public int listSearchCount(PageMaker pm) throws Exception;
}
