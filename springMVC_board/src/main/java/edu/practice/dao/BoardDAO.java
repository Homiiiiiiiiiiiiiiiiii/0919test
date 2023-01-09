package edu.practice.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.practice.vo.BoardVO;
import edu.practice.vo.SearchVO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(BoardVO vo) {
		return sqlSession.insert("edu.pratice.mapper.BoardMapper.insert", vo);
	}
	
	public List<BoardVO> list(SearchVO vo){
		return sqlSession.selectList("edu.pratice.mapper.BoardMapper.list",vo);
	}
	
	public BoardVO selectByBidx(int bidx) {
		return sqlSession.selectOne("edu.pratice.mapper.BoardMapper.selectByBidx", bidx);
	}
	
	public int updateByBidx(BoardVO vo) {
		return sqlSession.update("edu.pratice.mapper.BoardMapper.updateByBidx", vo);
	}
	
	public int deleteByBidx(int bidx) {
		return sqlSession.delete("edu.pratice.mapper.BoardMapper.deleteByBidx", bidx);
	}
}
