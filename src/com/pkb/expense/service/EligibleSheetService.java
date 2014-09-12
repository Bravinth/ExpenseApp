package com.pkb.expense.service;

import java.util.List;

import com.pkb.expense.vo.SheetVO;

public interface EligibleSheetService {

	List<SheetVO> getEligibleSheets(Long userId);
}
