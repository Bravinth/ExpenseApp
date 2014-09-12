package com.pkb.expense.manager;

import com.pkb.expense.service.LoginService;
import com.pkb.expense.vo.UserVO;

public interface LoginManager {
	
	Boolean isValidUser(String email, String password);

	UserVO getUserInfoIfValidUser(String emailId, String password);

	Long createNewUser(UserVO userVO);

	void updateUser(Long userId, UserVO userVO);

	UserVO findUser(String emailId);
}
