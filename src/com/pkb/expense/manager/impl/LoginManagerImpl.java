package com.pkb.expense.manager.impl;

import com.pkb.expense.manager.LoginManager;
import com.pkb.expense.manager.UserManager;
import com.pkb.expense.service.LoginService;
import com.pkb.expense.vo.UserVO;

public class LoginManagerImpl implements LoginManager {
	
	public UserManager userManager;
	
	/**
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	@Override
	public Boolean isValidUser(String email, String password) {
		
		boolean validUser = false;
		if(getUserInfoIfValidUser(email, password) != null)
			validUser = true;
			
		return validUser;
	}

	@Override
	public UserVO getUserInfoIfValidUser(String email, String password) {
		//boolean validUser = false;
		UserVO userVO = userManager.getUserInfo(email);
		if(userVO != null){
			if(email.equalsIgnoreCase(userVO.getEmailId())/* && password.equalsIgnoreCase(userVO.getPassword())*/){
				
			}else{
				userVO = null;
			}
		}
		
		return userVO;
	}

	@Override
	public Long createNewUser(UserVO userVO) {
		return userManager.createNewUser(userVO);
	}

	@Override
	public void updateUser(Long userId, UserVO userVO) {
		userManager.updateUser(userId, userVO);
	}

	@Override
	public UserVO findUser(String emailId) {
		return userManager.findUser(emailId);
	}

}
