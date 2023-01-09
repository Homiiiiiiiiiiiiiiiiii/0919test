package edu.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.practice.dao.UserDAO;
import edu.practice.vo.UserVO;

@Service
public class UserServieceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public int insert(UserVO vo) {
		return userDAO.insert(vo);
	}

	@Override
	public int selectById(String id) {
		return userDAO.selectById(id);
	}

	@Override
	public UserVO selectByUidx(UserVO vo) {
		return userDAO.selectByUidx(vo);
	}

}
