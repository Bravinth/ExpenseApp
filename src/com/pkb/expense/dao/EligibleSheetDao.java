package com.pkb.expense.dao;

import java.util.List;

import com.pkb.expense.vo.SheetVO;

public interface EligibleSheetDao {

	List<SheetVO> getEligibleSheets(Long userId);
	
}
