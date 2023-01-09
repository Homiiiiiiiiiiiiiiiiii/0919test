package edu.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.practice.dao.BoardDAO;
import edu.practice.vo.BoardVO;
import edu.practice.vo.SearchVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	

	@Override
	public int insert(BoardVO vo) {
		return boardDAO.insert(vo);
	}


	@Override
	public List<BoardVO> list(SearchVO vo) {
		List<BoardVO> list = boardDAO.list(vo);
		return list;
	}


	@Override
	public BoardVO selectByBidx(int bidx) {
		return boardDAO.selectByBidx(bidx);
	}


	@Override
	public int updateByBidx(BoardVO vo) {
		return boardDAO.updateByBidx(vo);
	}


	@Override
	public int deleteByBidx(int bidx) {
		return boardDAO.deleteByBidx(bidx);
	}

}
