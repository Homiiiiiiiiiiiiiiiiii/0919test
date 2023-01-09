package edu.practice.service;

import edu.practice.vo.UserVO;

public interface UserService {
	
	int insert(UserVO vo);
	int selectById(String id);
	UserVO selectByUidx(UserVO vo);
	

}
