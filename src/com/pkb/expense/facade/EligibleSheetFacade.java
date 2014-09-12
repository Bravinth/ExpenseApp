package com.pkb.expense.facade;

import java.util.List;

import com.pkb.expense.vo.SheetVO;

public interface EligibleSheetFacade {
	
	List<SheetVO> getEligibleSheets(Long userId);

}
