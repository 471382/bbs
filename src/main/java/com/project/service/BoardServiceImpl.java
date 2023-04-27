package com.project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.BoardDao;
import com.project.dto.BoardDto;
import com.project.vo.PageMaker;

@Service
public class BoardServiceImpl implements IBoardService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void write(BoardDto board) throws Exception {
		System.out.println(sqlSession);
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		dao.create(board);
	}

	@Override
	public BoardDto read(int bno) throws Exception {
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	@Override
	public void modify(BoardDto board) throws Exception {
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		dao.update(board);
	}

	@Override
	public void remove(int bno) throws Exception {
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		dao.delete(bno);
	}

	@Override
	public List<BoardDto> listSearchCriteria(PageMaker pm) throws Exception {
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		return dao.listSearch(pm);
	}

	@Override
	public int listSearchCount(PageMaker pm) throws Exception {
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		return dao.listSearchCount(pm);
	}
}