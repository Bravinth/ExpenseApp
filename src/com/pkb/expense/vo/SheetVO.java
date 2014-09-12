package com.pkb.expense.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.pkb.expense.common.util.Utils;

/**
 * @author Bravinth
 *
 */
public class SheetVO {
	
	private Long sheetId;
	private String sheetName;
	private String sheetCreatedDate;
	private UserVO sheetCreatedByUser = null;
	
	public SheetVO(){
		sheetCreatedByUser = new UserVO();
	}
	
	public SheetVO(String sheetName){
		this.sheetName = sheetName;
	}
	
	public SheetVO(Long sheetId, String sheetName){
		this.sheetId = sheetId;
		this.sheetName = sheetName;
	}
	/**
	 * @return the sheetId
	 */
	public Long getSheetId() {
		return sheetId;
	}
	/**
	 * @param sheetId the sheetId to set
	 */
	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}
	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}
	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	/**
	 * @return the sheetCreatedDate
	 */
	public String getSheetCreatedDate() {
		return sheetCreatedDate;
	}
	/**
	 * @param sheetCreatedDate Sql Timestamp date
	 */
	public void setSheetCreatedDate(Timestamp sheetCreatedDate) {
		this.sheetCreatedDate = Utils.getDisplayDate(new Date(sheetCreatedDate.getTime()));
	}
	
	/**
	 * @param sheetCreatedDate : Java util date
	 */
	public void setSheetCreatedDate(Date sheetCreatedDate) {
		this.sheetCreatedDate = Utils.getDisplayDate(sheetCreatedDate);
	}


	/**
	 * @return the sheetCreatedByUserVO
	 */
	public UserVO getSheetCreatedByUser() {
		return sheetCreatedByUser;
	}

	/**
	 * @param sheetCreatedByUserVO the sheetCreatedByUserVO to set
	 */
	public void setSheetCreatedByUser(UserVO sheetCreatedByUser) {
		this.sheetCreatedByUser = sheetCreatedByUser;
	}


	
}
