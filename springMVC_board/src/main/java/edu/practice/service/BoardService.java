package edu.practice.service;

import java.util.List;

import edu.practice.vo.BoardVO;
import edu.practice.vo.SearchVO;

public interface BoardService {

	int insert(BoardVO vo);
	List<BoardVO> list(SearchVO vo);
	BoardVO selectByBidx(int bidx);
	int updateByBidx(BoardVO vo);
	int deleteByBidx(int bidx);
}
