package com.project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.BoardDao;
import com.project.dto.BoardDto;
import com.project.vo.PageMaker;

@Service
public class BoardServiceImpl implements IBoardService {
	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	@Override
	public void write(BoardDto board) throws Exception {
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		dao.create(board);
		if(board.getFile() == null) return;
		else dao.addAttach(board.getFile());
	}

	@Override
	public BoardDto read(int bno) throws Exception {
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}
	
	@Override
	public String readAttach(int bno) throws Exception{
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		return dao.readAttach(bno);
	}

	@Override
	public void modify(BoardDto board) throws Exception {
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		if(board.getFile()!= null) dao.updateAttach(board);
		dao.update(board);
	}
	
	@Transactional
	@Override
	public void remove(int bno) throws Exception {
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		dao.deleteAttach(bno);
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