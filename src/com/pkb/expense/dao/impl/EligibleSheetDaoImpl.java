package com.pkb.expense.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pkb.expense.dao.EligibleSheetDao;
import com.pkb.expense.vo.SheetVO;

public class EligibleSheetDaoImpl extends BaseDaoImpl implements EligibleSheetDao {

	@Override
	public List<SheetVO> getEligibleSheets(Long userId) {
		
		List<SheetVO> sheetsList = new ArrayList<SheetVO>();
		//String sql = "SELECT a.sheet_id, a.sheet_name, a.sheet_created_by, a.sheet_created_date FROM expense_sheet a, user_sheet_eligibility b WHERE a.sheet_id = b.eligible_sheet_id AND b.eligible_user_id =?";
		String sql = "SELECT a.sheet_id, a.sheet_name, a.sheet_created_by, a.sheet_created_date,u.first_name,u.last_name FROM expense_sheet a, user_sheet_eligibility b, user u WHERE a.sheet_id = b.eligible_sheet_id AND b.eligible_user_id =? AND a.sheet_created_by=u.user_id";
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, new Object[]{userId});
		for(Map<String, Object> row : rows){
			SheetVO sheetVO = new SheetVO();
			sheetVO.setSheetId((Long)row.get("sheet_id"));
			sheetVO.setSheetName((String)row.get("sheet_name"));
			sheetVO.getSheetCreatedByUser().setId(((Long)row.get("sheet_created_by")));
			sheetVO.getSheetCreatedByUser().setFirstName(((String)row.get("first_name")));
			sheetVO.getSheetCreatedByUser().setLastName(((String)row.get("last_name")));
			sheetVO.setSheetCreatedDate((Timestamp)row.get("sheet_created_date"));
			sheetsList.add(sheetVO);
		}
		return sheetsList;
	}
	
}
