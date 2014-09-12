package com.pkb.expense.facade.impl;

import com.pkb.expense.facade.LoginFacade;
import com.pkb.expense.manager.LoginManager;
import com.pkb.expense.service.LoginService;
import com.pkb.expense.vo.UserVO;

public class LoginFacadeImpl implements LoginFacade {
	
	public LoginManager loginManager;

	/**
	 * @return the loginManager
	 */
	public LoginManager getLoginManager() {
		return loginManager;
	}

	/**
	 * @param loginManager the loginManager to set
	 */
	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

	@Override
	public Boolean isValidUser(String email, String password) {
		return loginManager.isValidUser(email, password);
	}

	@Override
	public UserVO getUserInfoIfValidUser(String emailId, String password) {
		return loginManager.getUserInfoIfValidUser(emailId, password);
	}

	@Override
	public Long createNewUser(UserVO userVO) {
		return loginManager.createNewUser(userVO);
	}

	@Override
	public void updateUser(Long userId, UserVO userVO) {
		loginManager.updateUser(userId, userVO);
	}

	@Override
	public UserVO findUser(String emailId) {
		return loginManager.findUser(emailId);
	}

}
