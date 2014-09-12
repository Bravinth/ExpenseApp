package com.pkb.expense.service.impl;

import java.util.List;

import com.pkb.expense.facade.UserFacade;
import com.pkb.expense.service.UserService;
import com.pkb.expense.vo.UserVO;

/**
 * @author Bravinth
 *
 */
public class UserServiceImpl implements UserService {

	
	private UserFacade userFacade = null;

	/**
	 * @return the userFacade
	 */
	public UserFacade getUserFacade() {
		return userFacade;
	}

	/**
	 * @param userFacade the userFacade to set
	 */
	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	@Override
	public UserVO getUserInfo(String emailId) {
		return userFacade.getUserInfo(emailId);
	}

	@Override
	public Long addUser(Long loggedInUserId, UserVO newUserVO) {
		
		boolean userExistsWithNickName = false;
		Long userId = null;
		UserVO userVO = userFacade.getUserInfo(newUserVO.getEmailId());
		if(userVO == null){//create a new user
			System.out.println("User created");
			userId = userFacade.addBuddy(newUserVO);
		}else{
			System.out.println("User does exist");
			userId=userVO.getId();
			userExistsWithNickName = userFacade.userExistsWithNickName(userVO.getId(), loggedInUserId);
		}
		newUserVO.setId(userId);
		if(userExistsWithNickName){
			System.out.println("You have already added this user. You can link this user to this sheet.");
		}else{
			userFacade.addNickNameToUser(loggedInUserId, newUserVO);
		}
		return userId;
	}
	
	public Long addUserWithNickName(Long loggedInUserId, UserVO newUserVO) {
		
		boolean userExistsWithNickName = false;
		Long userId = null;
		UserVO userVO = userFacade.getUserInfo(newUserVO.getEmailId());
		if(userVO == null){//create a new user
			userId = userFacade.addBuddy(newUserVO);
		}else{
			System.out.println("User does exist");
			userId = userVO.getId();
			System.out.println( "1111 user iddd :"+userId);
			userExistsWithNickName = userFacade.userExistsWithNickName(userVO.getId(), loggedInUserId);
		}
		newUserVO.setId(userId);
		if(userExistsWithNickName){
			System.out.println("You have already added this user. You can link this user to this sheet.");
		}else{
			userFacade.addNickNameToUser(loggedInUserId, newUserVO);
		}
		return userId;
	}

	@Override
	public List<UserVO> getUsersInSheet(Long sheetId) {
		return userFacade.getUsersInSheet(sheetId);
	}

	@Override
	public void addAndLinkUser(Long loggedInUserId, UserVO userVO, Long sheetId) {
		Long newUserId = addUserWithNickName(loggedInUserId, userVO);
		linkSheetToUser(loggedInUserId, newUserId, sheetId);
	}

	public void linkSheetToUser(Long loggedInUserId, Long userId, Long sheetId) {
		userFacade.linkSheetToUser(loggedInUserId, userId, sheetId);
		
	}

	@Override
	public List<UserVO> getBuddiesList(Long userId) {
		return userFacade.getBuddiesList(userId);
	}
	
}
