package com.pkb.expense.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pkb.expense.service.UserService;
import com.pkb.expense.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@RequestMapping(value="addUserWithNickname.do", method=RequestMethod.POST)
	public @ResponseBody String handleAddUser(HttpSession session, @RequestParam("emailId") String emailId, @RequestParam("nickName") String nickName, @RequestParam("sheetId") String sheetId){
		
		UserVO loggedInUserVO = (UserVO)session.getAttribute("loggedInUser");
		UserVO userVO = new UserVO();
		userVO.setEmailId(emailId);
		userVO.setNickName(nickName);
		System.out.println(emailId + " " + nickName + " " + loggedInUserVO.getId());
		userService.addAndLinkUser(loggedInUserVO.getId(), userVO, Long.parseLong(sheetId));
		return "User" + "added successfully";
	}
	
	@RequestMapping(value="getUsersInSheet.do", method=RequestMethod.GET)
	public @ResponseBody List<UserVO> getUsersInSheet(@RequestParam("sheetId") String sheetId, HttpSession session){
		List<UserVO> usersList = userService.getUsersInSheet(Long.valueOf(sheetId));
		//session.setAttribute("usersInSheet", usersList);
		return usersList;
	}
	
	@RequestMapping(value="retrieveBuddies.do", method=RequestMethod.GET)
	public @ResponseBody List<UserVO> getBuddiesList(HttpSession session){
		UserVO loggedInUserVO = (UserVO)session.getAttribute("loggedInUser");
		return userService.getBuddiesList(loggedInUserVO.getId());
	}
}
