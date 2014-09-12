package com.pkb.expense.manager.impl;

import java.util.List;

import com.pkb.expense.dao.SheetDao;
import com.pkb.expense.manager.SheetManager;
import com.pkb.expense.vo.ItemVO;
import com.pkb.expense.vo.SheetVO;

public class SheetManagerImpl implements SheetManager {

	public SheetDao dao;
	
	/**
	 * @return the dao
	 */
	public SheetDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(SheetDao dao) {
		this.dao = dao;
	}

	@Override
	public Long addSheet(Long userId, SheetVO sheetVO) {
		Long sheetId = dao.addSheet(userId, sheetVO);
		linkSheetToUser(sheetId, userId, userId);
		return sheetId;
	}

	@Override
	public boolean removeSheet(Long sheetId) {
		dao.removeSheetFromAllUsers(sheetId);
		return dao.removeSheet(sheetId);
	}

	@Override
	public boolean editSheet(Long sheetId, SheetVO sheetVO) {
		return dao.editSheet(sheetId, sheetVO);
	}

	@Override
	public List<ItemVO> getSheetItems(Long sheetId) {
		return dao.getSheetItems(sheetId);
	}

	@Override
	public Long addItem(ItemVO item) {
		Long itemId = dao.addItem(item);
		item.setItemId(itemId);
		dao.linkItemToSharedUsers(item);
		return itemId;
	}

	@Override
	public ItemVO getItem(Long sheetId, Long itemId) {
		return dao.getItem(sheetId, itemId);
	}

	@Override
	public boolean removeItem(Long sheetId, Long itemId) {
		return dao.removeItem(sheetId, itemId);
	}

	@Override
	public boolean editItemName(Long sheetId, Long itemId, String itemName) {
		return dao.editItemName(sheetId, itemId, itemName);
	}

	@Override
	public boolean editItemDate(Long sheetId, Long itemId, String itemDate) {
		return dao.editItemDate(sheetId, itemId, itemDate);
	}

	@Override
	public boolean editItemPrice(Long sheetId, Long itemId, float itemPrice) {
		return dao.editItemPrice(sheetId, itemId, itemPrice);
	}

	@Override
	public boolean editItemPaidBy(Long sheetId, Long itemId, Long itemPaidBy) {
		return dao.editItemPaidBy(sheetId, itemId, itemPaidBy);
	}

	@Override
	public boolean editItemSharedBy(Long sheetId, Long itemId, Long itemSharedBy, String share) {
		return dao.editItemSharedBy(sheetId, itemId, itemSharedBy, share);
	}

	@Override
	public boolean deleteItem(Long sheetId, Long itemId) {
		return dao.deleteItem(sheetId, itemId);
	}

	@Override
	public boolean linkSheetToUser(Long eligibleSheetId, Long eligibleUserId,Long loggedInUserId) {
		return dao.linkSheetToUser(eligibleSheetId, eligibleUserId, loggedInUserId);
	}

}
