package com.pkb.expense.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pkb.expense.service.SheetService;
import com.pkb.expense.vo.ItemVO;
import com.pkb.expense.vo.SheetVO;
import com.pkb.expense.vo.TestVO;
import com.pkb.expense.vo.UserVO;

/**
 * @author Bravinth
 *
 */
@Controller
public class SheetController {

	@Autowired
	public SheetService sheetService;
	
	@RequestMapping(value="addsheet.do", method=RequestMethod.POST)
	public @ResponseBody SheetVO addSheet(@RequestParam("sheetName") String sheetName, HttpSession session){
		
		UserVO userVO = (UserVO)session.getAttribute("loggedInUser");
		Long userId = userVO.getId();
		Long sheetId = sheetService.addSheet(userId, new SheetVO(sheetName));
		
		SheetVO newSheetVO = new SheetVO(sheetId, sheetName);
		newSheetVO.setSheetCreatedDate(new Date());
		newSheetVO.setSheetCreatedByUser(userVO);
		return newSheetVO;
	}
	
	@RequestMapping(value="deleteSheet.do", method=RequestMethod.DELETE)
	public @ResponseBody String deleteSheets(@RequestParam("sheetId") String sheetId){
		
		String msg = "Sheet did not get deleted";
		boolean result = false;
		
		//result = sheetService.removeSheet(Long.valueOf(sheetId));
		if(result)
			msg = "Sheet got deleted successfully";
		return msg;
		
	}

	@RequestMapping(value="deleteSheet.do", method=RequestMethod.GET)
	public @ResponseBody String deleteSheet(@RequestParam("sheetId") String sheetId){
		
		String msg = "Sheet did not get deleted";
		boolean result = sheetService.removeSheet(Long.valueOf(sheetId));
		if(result)
			msg = "Sheet got deleted successfully";
		return msg;
		
	}

	
	@RequestMapping(value="getSelectedSheetItems.do", method=RequestMethod.GET)
	public @ResponseBody List<ItemVO> editSheetsGET(@RequestParam("sheetId") String sheetId){
		return sheetService.getSheetItems(Long.valueOf(sheetId));
	}
	
	@RequestMapping(value="addItem.do", method=RequestMethod.POST)
	public @ResponseBody ItemVO addItem(@RequestBody ItemVO item){
		Long itemId = sheetService.addItem(item);
		return sheetService.getItem(item.getSheetId(), itemId);
	}
	
	@RequestMapping(value="editItemName.do", method=RequestMethod.POST)
	public @ResponseBody boolean editItemName(@RequestParam("sheetId") Long sheetId, @RequestParam("itemId") Long itemId, @RequestParam("modifiedValue") String itemName){
		return sheetService.editItemName(sheetId, itemId, itemName.trim());
	}
	
	@RequestMapping(value="editItemDate.do", method=RequestMethod.POST)
	public @ResponseBody boolean editItemDate(@RequestParam("sheetId") Long sheetId, @RequestParam("itemId") Long itemId, @RequestParam("modifiedValue") String itemDate){
		return sheetService.editItemDate(sheetId, itemId, itemDate);
	}
	
	@RequestMapping(value="editItemPrice.do", method=RequestMethod.POST)
	public @ResponseBody ItemVO editItemPrice(@RequestParam("sheetId") Long sheetId, @RequestParam("itemId") Long itemId, @RequestParam("modifiedValue") float itemPrice){
		sheetService.editItemPrice(sheetId, itemId, itemPrice);
		return sheetService.getItem(sheetId, itemId);
	}
	
	@RequestMapping(value="editItemPaidBy.do", method=RequestMethod.POST)
	public @ResponseBody boolean editItemPaidBy(@RequestParam("sheetId") Long sheetId, @RequestParam("itemId") Long itemId, @RequestParam("modifiedValue") Long itemPaidBy){
		return sheetService.editItemPaidBy(sheetId, itemId, itemPaidBy);
	}
	
	@RequestMapping(value="editItemSharedBy.do", method=RequestMethod.POST)
	public @ResponseBody ItemVO editItemSharedBy(@RequestParam("sheetId") Long sheetId, @RequestParam("itemId") Long itemId, 
											@RequestParam("userId") Long itemSharedBy, @RequestParam("modifiedValue") String share){
		sheetService.editItemSharedBy(sheetId, itemId, itemSharedBy, share);
		return sheetService.getItem(sheetId, itemId);
	}
	
	@RequestMapping(value="deleteItem.do", method=RequestMethod.POST)
	public @ResponseBody boolean deleteItem(@RequestParam("sheetId") Long sheetId, @RequestParam("itemId") Long itemId){
		return sheetService.deleteItem(sheetId, itemId);
	}
}
