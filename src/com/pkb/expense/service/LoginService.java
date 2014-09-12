package com.pkb.expense.service;

import com.pkb.expense.vo.UserVO;

public interface LoginService {
	
	Boolean isValidUser(String email, String password);
	
	UserVO getUserInfoIfValidUser(String emailId, String password);
	
	Long createNewUser(UserVO userVO);

	void updateUser(Long userId, UserVO userVO);

	UserVO findUser(String emailId);

}
