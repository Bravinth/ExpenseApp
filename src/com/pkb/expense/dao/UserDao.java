package com.pkb.expense.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pkb.expense.service.LoginService;
import com.pkb.expense.vo.UserVO;

/**
 * @author Bravinth
 *
 */
public interface UserDao {

	UserVO getUserInfo(Long userId);
	
	UserVO getUserInfo(String emailId);
	
	Long addBuddy(UserVO userVO);
	
	void addNickNameToUser(Long loggedInUserId, UserVO userVO);

	Map<String, UserVO> getUserInfo(Set<Long> userIdList);
	
	List<UserVO> getUsersInSheet(Long SheetId);

	boolean userExistsWithNickName(Long userId, Long loggedInUserId);

	List<UserVO> getBuddiesList(Long userId);

	Long createNewUser(UserVO userVO);

	void updateUser(Long userId, UserVO userVO);

	UserVO findUser(String emailId);

}
