package com.pkb.expense.service.impl;

import java.util.List;

import com.pkb.expense.manager.SheetManager;
import com.pkb.expense.service.SheetService;
import com.pkb.expense.vo.ItemVO;
import com.pkb.expense.vo.SheetVO;

public class SheetServiceImpl implements SheetService {

	public SheetManager manager;
	
	/**
	 * @return the manager
	 */
	public SheetManager getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(SheetManager manager) {
		this.manager = manager;
	}

	@Override
	public Long addSheet(Long userId, SheetVO sheetVO) {
		return manager.addSheet(userId, sheetVO);
	}

	@Override
	public boolean removeSheet(Long sheetId) {
		return manager.removeSheet(sheetId);
	}

	@Override
	public boolean editSheet(Long sheetId, SheetVO sheetVO) {
		return manager.editSheet(sheetId, sheetVO);
	}

	@Override
	public List<ItemVO> getSheetItems(Long sheetId) {
		return manager.getSheetItems(sheetId);
	}

	@Override
	public Long addItem(ItemVO item) {
		return manager.addItem(item);
	}

	@Override
	public ItemVO getItem(Long sheetId, Long itemId) {
		return manager.getItem(sheetId, itemId);
	}

	@Override
	public boolean removeItem(Long sheetId, Long itemId) {
		return manager.removeItem(sheetId, itemId);
	}

	@Override
	public boolean editItemName(Long sheetId, Long itemId, String itemName) {
		return manager.editItemName(sheetId, itemId, itemName);
	}

	@Override
	public boolean editItemDate(Long sheetId, Long itemId, String itemDate) {
		return manager.editItemDate(sheetId, itemId, itemDate);
	}

	@Override
	public boolean editItemPrice(Long sheetId, Long itemId, float itemPrice) {
		return manager.editItemPrice(sheetId, itemId, itemPrice);
	}

	@Override
	public boolean editItemPaidBy(Long sheetId, Long itemId, Long itemPaidBy) {
		return manager.editItemPaidBy(sheetId, itemId, itemPaidBy);
	}

	@Override
	public boolean editItemSharedBy(Long sheetId, Long itemId, Long itemSharedBy, String share) {
		return manager.editItemSharedBy(sheetId, itemId, itemSharedBy, share);
	}

	@Override
	public boolean deleteItem(Long sheetId, Long itemId) {
		return manager.deleteItem(sheetId, itemId);
	}

}
