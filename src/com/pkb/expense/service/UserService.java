package com.pkb.expense.service;

import java.util.List;

import com.pkb.expense.vo.UserVO;

public interface UserService {

	UserVO getUserInfo(String emailId);
	
	Long addUser(Long loggedInUserId, UserVO userVO);

	List<UserVO> getUsersInSheet(Long sheetId);

	void addAndLinkUser(Long loggedInUserId, UserVO userVO, Long sheetId);
	
	void linkSheetToUser(Long loggedInUserId, Long id, Long sheetId);

	List<UserVO> getBuddiesList(Long userId);
}
