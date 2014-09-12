package com.pkb.expense.vo;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Date;
import java.util.Map;

import com.pkb.expense.common.util.Utils;

/**
 * @author Bravinth
 *
 */
public class ItemVO {
	
	private Long itemId;
	private Long sheetId;
	private String itemDescription;
	private float itemPrice;
	private String itemPaidDate;
	private UserVO itemPaidBy;
	private UserVO itemCreatedBy;
	private Map<String, String> itemShareUserMap;
	int totalShares;

	public ItemVO() {
		itemPaidBy = new UserVO();
		itemCreatedBy = new UserVO();
		itemShareUserMap = new HashMap<String, String>();
	}
	/**
	 * @return the itemId
	 */
	public Long getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
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
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}
	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	/**
	 * @return the itemPrice
	 */
	public float getItemPrice() {
		return itemPrice;
	}
	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	/**
	 * @return the itemPaidDate
	 */
	public String getItemPaidDate() {
		return itemPaidDate;
	}
	/**
	 * @param itemPaidDate the itemPaidDate to set
	 */
	public void setItemPaidDateByDate(Date itemPaidDate) {
		this.itemPaidDate = Utils.getDisplayDate(itemPaidDate);
	}
	
	public void setItemPaidDatebyTimeStamp(Timestamp itemPaidDate) {
		this.itemPaidDate = Utils.getDisplayDate(new Date(itemPaidDate.getTime()));
	}
	
	/**
	 * @param itemPaidDate the itemPaidDate to set
	 */
	public void setItemPaidDate(String itemPaidDate) {
		this.itemPaidDate = itemPaidDate;
	}
	/**
	 * @return the itemPaidBy
	 */
	public UserVO getItemPaidBy() {
		return itemPaidBy;
	}
	/**
	 * @param itemPaidBy the itemPaidBy to set
	 */
	public void setItemPaidBy(UserVO itemPaidBy) {
		this.itemPaidBy = itemPaidBy;
	}
	/**
	 * @return the itemCreatedBy
	 */
	public UserVO getItemCreatedBy() {
		return itemCreatedBy;
	}
	/**
	 * @param itemCreatedBy the itemCreatedBy to set
	 */
	public void setItemCreatedBy(UserVO itemCreatedBy) {
		this.itemCreatedBy = itemCreatedBy;
	}
	/**
	 * @return the itemShareUserMap
	 */
	public Map<String, String> getItemShareUserMap() {
		return itemShareUserMap;
	}
	/**
	 * @param itemShareUserMap the itemShareUserMap to set
	 */
	public void setItemShareUserMap(Map<String, String> itemShareUserMap) {
		this.itemShareUserMap = itemShareUserMap;
	}
	/**
	 * @return the totalShares
	 */
	public int getTotalShares() {
		return totalShares;
	}
	/**
	 * @param totalShares the totalShares to set
	 */
	public void setTotalShares(int totalShares) {
		this.totalShares = totalShares;
	}
	
	
}
