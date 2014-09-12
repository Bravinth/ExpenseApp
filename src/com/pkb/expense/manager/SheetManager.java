package com.pkb.expense.manager;

import java.util.List;

import com.pkb.expense.vo.ItemVO;
import com.pkb.expense.vo.SheetVO;

public interface SheetManager {

	Long addSheet(Long userId, SheetVO sheetVO);
	
	boolean linkSheetToUser(Long eligibleSheetId, Long eligibleUserId, Long loggedInUserId);
	
	boolean removeSheet(Long sheetId);
	
	boolean editSheet(Long sheetId, SheetVO sheetVO);
	
	List<ItemVO> getSheetItems(Long sheetId);
	
	Long addItem(ItemVO item);
	
	ItemVO getItem(Long sheetId, Long itemId);
	
	boolean removeItem(Long sheetId, Long itemId);

	boolean editItemName(Long sheetId, Long itemId, String itemName);

	boolean editItemDate(Long sheetId, Long itemId, String itemDate);
	
	boolean editItemPrice(Long sheetId, Long itemId, float itemPrice);

	boolean editItemPaidBy(Long sheetId, Long itemId, Long itemPaidBy);

	boolean editItemSharedBy(Long sheetId, Long itemId, Long itemSharedBy, String share);

	boolean deleteItem(Long sheetId, Long itemId);
}
