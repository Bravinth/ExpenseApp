package com.pkb.expense.service.impl;

import java.util.List;

import com.pkb.expense.facade.EligibleSheetFacade;
import com.pkb.expense.service.EligibleSheetService;
import com.pkb.expense.vo.SheetVO;

public class EligibleSheetServiceImpl implements EligibleSheetService {

	public EligibleSheetFacade eligibleSheetFacade;

	/**
	 * @return the eligibleSheetfacade
	 */
	public EligibleSheetFacade getEligibleSheetFacade() {
		return eligibleSheetFacade;
	}

	/**
	 * @param eligibleSheetfacade the eligibleSheetfacade to set
	 */
	public void setEligibleSheetFacade(EligibleSheetFacade eligibleSheetFacade) {
		this.eligibleSheetFacade = eligibleSheetFacade;
	}

	@Override
	public List<SheetVO> getEligibleSheets(Long userId) {
		return eligibleSheetFacade.getEligibleSheets(userId);
	}

}
