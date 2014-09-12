package com.pkb.expense.facade;

import java.util.List;

import com.pkb.expense.vo.UserVO;

public interface UserFacade {

	UserVO getUserInfo(String emailId);
	
	Long addBuddy(UserVO userVO);
	
	void addNickNameToUser(Long loggedInUserId, UserVO userVO);

	List<UserVO> getUsersInSheet(Long sheetId);

	boolean userExistsWithNickName(Long userId, Long loggedInUserId);

	void linkSheetToUser(Long loggedInUserId, Long userId, Long sheetId);

	List<UserVO> getBuddiesList(Long userId);
}
