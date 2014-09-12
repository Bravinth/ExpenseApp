package com.pkb.expense.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pkb.expense.service.LoginService;
import com.pkb.expense.vo.UserVO;

/**
 * @author Bravinth
 *
 */
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public @ResponseBody Object processSubmit(@RequestParam("email") String email, 
			@RequestParam("password") String password, HttpSession session) throws Exception {
		
		String returnMessage = "loginUnSuccessful";
		
		if(email.isEmpty() || password.isEmpty()){
			System.out.println("Please enter the required fields");
			return returnMessage;
		}
		
		UserVO userVO = loginService.getUserInfoIfValidUser(email, password);
		if(userVO == null){
			System.out.println("Please enter a valid userId and password");
			return returnMessage;
		}else{
			session.setAttribute("loggedInUser", userVO);
			//session.putValue("loggedInUser", userVO);
			//returnMessage = "loginSuccessful";
		}
			
		return userVO;
	}
	
	@RequestMapping(value="/signup.do", method=RequestMethod.POST)
	public @ResponseBody Object processSubmit(@RequestBody UserVO userVO, HttpSession session) throws Exception {
		
		String returnMessage = "loginUnSuccessful";
		Long userId = null;
		
		if(userVO.getFirstName().isEmpty() || userVO.getEmailId().isEmpty() || userVO.getPassword().isEmpty()){
			System.out.println("All fields are required.");
			return returnMessage;
		}
		
		UserVO existinUserVO = loginService.findUser(userVO.getEmailId());
		
		if(existinUserVO == null){
			userId = loginService.createNewUser(userVO);
		}else{
			userId = existinUserVO.getId();
			if(existinUserVO.getPassword() == null){
				loginService.updateUser(userId, userVO);
			}else{
				return "User Already Exists.";
			}
		}

		userVO.setId(userId);
		session.setAttribute("loggedInUser", userVO);
			
		return userVO;
	}

}
