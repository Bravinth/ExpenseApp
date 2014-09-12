package com.pkb.expense.facade.impl;

import java.util.List;

import com.pkb.expense.facade.UserFacade;
import com.pkb.expense.manager.UserManager;
import com.pkb.expense.vo.UserVO;

/**
 * @author Bravinth
 *
 */
public class UserFacadeImpl implements UserFacade {

	
	private UserManager userManager = null;
	
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

	public UserVO getUserInfo(String emailId){
		return userManager.getUserInfo(emailId);
	}
	
	@Override
	public Long addBuddy(UserVO userVO) {
		return userManager.addBuddy(userVO);
	}
	
	public void addNickNameToUser(Long loggedInUserId, UserVO userVO) {
		userManager.addNickNameToUser(loggedInUserId, userVO);
	}

	@Override
	public List<UserVO> getUsersInSheet(Long sheetId) {
		return userManager.getUsersInSheet(sheetId);
	}

	@Override
	public boolean userExistsWithNickName(Long userId, Long loggedInUserId) {
		return userManager.userExistsWithNickName(userId, loggedInUserId);
	}

	@Override
	public void linkSheetToUser(Long loggedInUserId, Long userId, Long sheetId) {
		userManager.linkSheetToUser(loggedInUserId, userId, sheetId);
		
	}

	@Override
	public List<UserVO> getBuddiesList(Long userId) {
		return userManager.getBuddiesList(userId);
	}
}
