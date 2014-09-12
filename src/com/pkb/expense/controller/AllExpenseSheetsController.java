package com.pkb.expense.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pkb.expense.service.EligibleSheetService;
import com.pkb.expense.vo.SheetVO;
import com.pkb.expense.vo.UserVO;

@Controller
@RequestMapping(value="getAllSheets.do")
public class AllExpenseSheetsController {
	
	@Autowired
	EligibleSheetService service;

	
	@RequestMapping(method=RequestMethod.POST)
	public String getAllEligibleSheets(HttpSession session, ModelMap model){

		UserVO loggedInUserVO = (UserVO)session.getAttribute("loggedInUser");
		List<SheetVO> sheetsList = service.getEligibleSheets(loggedInUserVO.getId());
		model.addAttribute("eligibleSheets", sheetsList);
		
		return "eligibleSheets";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<SheetVO> getAllEligibleSheetsJSON(HttpSession session){

		UserVO loggedInUserVO = (UserVO)session.getAttribute("loggedInUser");
		List<SheetVO> sheetsList = service.getEligibleSheets(loggedInUserVO.getId());
		System.out.println(sheetsList);
		return sheetsList;
	}
}
