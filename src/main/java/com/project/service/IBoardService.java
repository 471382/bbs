package com.project.service;

import java.util.List;

import com.project.dto.BoardDto;
import com.project.vo.PageMaker;

public interface IBoardService {
	public void write(BoardDto board) throws Exception;
	public BoardDto read(int bno) throws Exception;
	public void modify(BoardDto board) throws Exception;
	public void remove(int bno) throws Exception;
	
	public List<BoardDto> listSearchCriteria(PageMaker pm) throws Exception;
	public int listSearchCount(PageMaker pm) throws Exception;
	public String readAttach(int bno) throws Exception;
}
