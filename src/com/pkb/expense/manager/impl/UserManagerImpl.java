package com.pkb.expense.manager.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pkb.expense.dao.UserDao;
import com.pkb.expense.manager.SheetManager;
import com.pkb.expense.manager.UserManager;
import com.pkb.expense.service.LoginService;
import com.pkb.expense.vo.UserVO;

/**
 * @author Bravinth
 *
 */
public class UserManagerImpl implements UserManager {
	
	private UserDao userDao;
	private SheetManager sheetManager;

	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * @return the sheetManager
	 */
	public SheetManager getSheetManager() {
		return sheetManager;
	}

	/**
	 * @param sheetManager the sheetManager to set
	 */
	public void setSheetManager(SheetManager sheetManager) {
		this.sheetManager = sheetManager;
	}

	public UserVO getUserInfo(String emailId){
		return userDao.getUserInfo(emailId);
	}

	@Override
	public Long addBuddy(UserVO userVO) {
		return userDao.addBuddy(userVO);
	}
	
	public void addNickNameToUser(Long loggedInUserId, UserVO userVO) {
		userDao.addNickNameToUser(loggedInUserId, userVO);
	}

	@Override
	public Map<String, UserVO> getUserInfo(Set<Long> userIdList) {
		return userDao.getUserInfo(userIdList);
	}

	@Override
	public List<UserVO> getUsersInSheet(Long sheetId) {
		return userDao.getUsersInSheet(sheetId);
	}

	@Override
	public boolean userExistsWithNickName(Long userId, Long loggedInUserId) {
		return userDao.userExistsWithNickName(userId, loggedInUserId);
	}

	@Override
	public void linkSheetToUser(Long loggedInUserId, Long eligibleUserId, Long eligibleSheetId) {
		sheetManager.linkSheetToUser(eligibleSheetId, eligibleUserId, loggedInUserId);
	}

	@Override
	public List<UserVO> getBuddiesList(Long userId) {
		return userDao.getBuddiesList(userId);
	}

	@Override
	public Long createNewUser(UserVO userVO) {
		return userDao.createNewUser(userVO);
	}

	@Override
	public void updateUser(Long userId, UserVO userVO) {
		userDao.updateUser(userId, userVO);
	}

	@Override
	public UserVO findUser(String emailId) {
		return userDao.findUser(emailId);
	}

}
