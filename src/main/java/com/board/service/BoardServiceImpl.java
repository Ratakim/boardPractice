package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
   
	@Inject
	private BoardDAO dao;
	
	@Override
	public List<BoardVO> list() throws Exception {
		
		return dao.list();
	}

	@Override
	public void write(BoardVO vo) throws Exception {
		
		dao.write(vo);
		
	}

	@Override
	public BoardVO view(int no) throws Exception {
		
		return dao.view(no);
	}

	@Override
	public void modify(BoardVO mo) throws Exception {
		
		dao.modify(mo);
						
	}

	@Override
	public void delete(int no) throws Exception {
		
		dao.delete(no);
		
	}

	@Override
	public int count() throws Exception {
		
		return dao.count();
	}

	@Override
	public List<BoardVO> listPage(int displayPost, int postNum) throws Exception {
		
		return dao.listPage(displayPost, postNum);
	}

	@Override
	public List<BoardVO> listPageSearch(int displayPost, int postNum, String searchType, String keyword) throws Exception {
		
		return dao.listPageSearch(displayPost, postNum, searchType, keyword);
	}
	
	// 게시물 총 갯수
	@Override
	public int searchCount(String searchType, String keyword) throws Exception {
	 
		return dao.searchCount(searchType, keyword);
	}
	
}
	

