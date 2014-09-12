package com.pkb.expense.manager.impl;

import java.util.List;

import com.pkb.expense.dao.EligibleSheetDao;
import com.pkb.expense.manager.EligibleSheetManager;
import com.pkb.expense.manager.UserManager;
import com.pkb.expense.vo.SheetVO;

public class EligibleSheetManagerImpl implements EligibleSheetManager {

	public EligibleSheetDao dao;
	public UserManager userManager;
		
	/**
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * @return the dao
	 */
	public EligibleSheetDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(EligibleSheetDao dao) {
		this.dao = dao;
	}

	@Override
	public List<SheetVO> getEligibleSheets(Long userId) {
		List<SheetVO> sheetList = dao.getEligibleSheets(userId);	
		return sheetList;
	}

}
