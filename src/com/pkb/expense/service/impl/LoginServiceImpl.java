package com.pkb.expense.service.impl;

import com.pkb.expense.facade.LoginFacade;
import com.pkb.expense.service.LoginService;
import com.pkb.expense.vo.UserVO;

public class LoginServiceImpl implements LoginService {

	public LoginFacade loginFacade;


	/**
	 * @return the loginFacade
	 */
	public LoginFacade getLoginFacade() {
		return loginFacade;
	}


	/**
	 * @param loginFacade the loginFacade to set
	 */
	public void setLoginFacade(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}


	@Override
	public Boolean isValidUser(String email, String password) {
		return loginFacade.isValidUser(email, password);
	}


	@Override
	public UserVO getUserInfoIfValidUser(String emailId, String password) {
		return loginFacade.getUserInfoIfValidUser(emailId, password);
	}


	@Override
	public Long createNewUser(UserVO userVO) {
		return loginFacade.createNewUser(userVO);
	}

	@Override
	public void updateUser(Long userId, UserVO userVO) {
		loginFacade.updateUser(userId, userVO);
	}


	@Override
	public UserVO findUser(String emailId) {
		return loginFacade.findUser(emailId);
	}

}
