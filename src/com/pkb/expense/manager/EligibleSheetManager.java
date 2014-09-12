package com.pkb.expense.manager;

import java.util.List;

import com.pkb.expense.vo.SheetVO;

/**
 * @author Bravinth
 *
 */
public interface EligibleSheetManager {
	
	List<SheetVO> getEligibleSheets(Long userId);

}
