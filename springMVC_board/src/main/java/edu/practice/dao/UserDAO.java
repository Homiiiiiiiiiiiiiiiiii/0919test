package edu.practice.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.practice.vo.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVO vo) {
		return sqlSession.insert("edu.pratice.mapper.UserMapper.insert", vo);
	}

	public int selectById(String id) {
		return sqlSession.selectOne("edu.pratice.mapper.UserMapper.selectById", id);
	}
	
	public UserVO selectByUidx(UserVO vo) {
		return sqlSession.selectOne("edu.pratice.mapper.UserMapper.selectByUidx", vo);
	}
}
