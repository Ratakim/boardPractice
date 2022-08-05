package com.board.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.vo.BoardVO;
import com.board.vo.ReplyVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession sql;

	String namespace = "com.board.mappers.board";

	@Override
	public List<BoardVO> list() throws Exception {

		return sql.selectList(namespace + ".list");
	}

	@Override
	public void write(BoardVO vo) throws Exception {
        
		sql.insert(namespace +".write",vo);
	}

	@Override
	public BoardVO view(int no) throws Exception {
						
		return sql.selectOne(namespace + ".view", no);
	}

	@Override
	public void modify(BoardVO mo) throws Exception {
		
		sql.update(namespace+".modify",mo);
	}

	@Override
	public void delete(int no) throws Exception {
		
		sql.delete(namespace + ".delete", no);
		
	}

	@Override
	public int count() throws Exception {
		
		return sql.selectOne(namespace+".count");
	}

	@Override
	public List<BoardVO> listPage(int displayPost, int postNum) throws Exception {
		
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		return sql.selectList(namespace+".listPage", data);
	}

	@Override
	public List<BoardVO> listPageSearch(int displayPost, int postNum, String searchType, String keyword) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		  data.put("displayPost", displayPost);
		  data.put("postNum", postNum);
		  
		  data.put("searchType", searchType);
		  data.put("keyword", keyword);
		
		  return sql.selectList(namespace + ".listPageSearch", data);
	}

	@Override
	public int searchCount(String searchType, String keyword) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
			 
		 data.put("searchType", searchType);
		 data.put("keyword", keyword);
		 
		 return sql.selectOne(namespace + ".searchCount", data); 
	}
	
}
		
