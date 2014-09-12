package com.pkb.expense.facade.impl;

import java.util.List;

import com.pkb.expense.facade.EligibleSheetFacade;
import com.pkb.expense.manager.EligibleSheetManager;
import com.pkb.expense.manager.impl.EligibleSheetManagerImpl;
import com.pkb.expense.vo.SheetVO;

public class EligibleSheetFacadeImpl implements EligibleSheetFacade {

	public EligibleSheetManager manager = new EligibleSheetManagerImpl();
	
	/**
	 * @return the manager
	 */
	public EligibleSheetManager getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(EligibleSheetManager manager) {
		this.manager = manager;
	}

	@Override
	public List<SheetVO> getEligibleSheets(Long userId) {
		return manager.getEligibleSheets(userId);
	}

}
