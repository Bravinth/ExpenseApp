package com.pkb.expense.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.pkb.expense.common.util.Utils;
import com.pkb.expense.dao.SheetDao;
import com.pkb.expense.vo.ItemVO;
import com.pkb.expense.vo.SheetVO;

public class SheetDaoImpl extends BaseDaoImpl implements SheetDao {

	@Override
	public Long addSheet(final Long userId, final SheetVO sheetVO) {

		final String sql="INSERT INTO expense_sheet (sheet_name, sheet_created_date, sheet_created_by) VALUES (?, sysdate(), ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, sheetVO.getSheetName());
				stmt.setLong(2, userId);
				return stmt;
			}
		}, keyHolder);
		
		return keyHolder.getKey().longValue();

	}
	

	@Override
	public boolean linkSheetToUser(Long eligibleSheetId, Long eligibleUserId, Long loggedInUserId) {
		
		final String sql = "INSERT INTO user_sheet_eligibility(eligible_sheet_id, eligible_user_id, access_given_by) values(?, ?, ?)";
		getJdbcTemplate().update(sql, new Object[]{eligibleSheetId, eligibleUserId, loggedInUserId});
		return true;
	}


	@Override
	public boolean removeSheetFromAllUsers(Long sheetId) {
		final String sql = "DELETE FROM user_sheet_eligibility WHERE eligible_sheet_id = ? ";
		getJdbcTemplate().update(sql, new Object[]{sheetId});
		return false;
	}

	
	@Override
	public boolean removeSheet(Long sheetId) {

		final String sql = "DELETE FROM expense_sheet WHERE sheet_id = ? ";
		getJdbcTemplate().update(sql, new Object[]{sheetId});
		return true;
	}

	@Override
	public boolean editSheet(Long Long, SheetVO sheetVO) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public static final class SheetMapper implements RowMapper<Long>{

		@Override
		public Long mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			System.out.println("dataaaaaaa  : " +resultSet.getInt(1));
			return null;
		}
		
	}


	@Override
	public List<ItemVO> getSheetItems(Long sheetId) {
		
		//final String sql = "SELECT i.item_description, i.item_paid_date, i.item_price, i.item_paid_by, GROUP_CONCAT(concat_ws('-', s.item_shared_by, s.item_split_amt)) as share_detail, sum(s.item_split_amt) as total_share, u.first_name FROM item i, item_split s, user u  WHERE (i.item_id = s.item_id AND i.sheet_id = s.sheet_id) AND i.sheet_id=? AND i.item_paid_by=u.user_id group by i.sheet_id,i.item_id";
		final String sql = "SELECT i.item_id, i.sheet_id, i.item_description, i.item_paid_date, i.item_price, i.item_paid_by, GROUP_CONCAT(concat_ws('-', s.item_shared_by, s.item_share)) as share_detail, sum(s.item_share) as total_share, u.first_name " +
		"FROM item i LEFT JOIN item_split s ON (i.item_id = s.item_id AND i.sheet_id = s.sheet_id) " +
		"JOIN user u on i.item_paid_by=u.user_id " +
		"where i.sheet_id=? group by i.sheet_id,i.item_id";
		return getJdbcTemplate().query(sql, new Object[]{sheetId}, new ItemRowMapper());
		
	}

	class ItemRowMapper implements RowMapper<ItemVO>{

		@Override
		public ItemVO mapRow(ResultSet resultSet, int row) throws SQLException {
			ItemVO itemVO = new ItemVO();
			itemVO.setItemId(Long.parseLong(resultSet.getString("item_id")));
			itemVO.setItemDescription(resultSet.getString("item_description"));
			itemVO.setItemPaidDatebyTimeStamp(resultSet.getTimestamp("item_paid_date"));
			itemVO.setItemPrice(Float.parseFloat(resultSet.getString("item_price")));
			itemVO.getItemPaidBy().setId(resultSet.getLong("item_paid_by"));
			itemVO.getItemPaidBy().setFirstName(resultSet.getString("first_name"));
			//get and set the remaing user fields if necessary
			itemVO.setItemShareUserMap(Utils.getUsersShareMap(resultSet.getString("share_detail")));
			itemVO.setTotalShares(resultSet.getInt("total_share"));
			return itemVO;
		}
		
	}


	@Override
	public Long addItem(final ItemVO item) {
		final String sql = "INSERT INTO item (sheet_id, item_description, item_price, item_paid_date, item_paid_by, item_created_by) VALUES (?,?,?,?,?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setLong(1, item.getSheetId());
				stmt.setString(2, item.getItemDescription());
				stmt.setString(3, String.valueOf(item.getItemPrice()));
				stmt.setDate(4, new Date(new java.util.Date(item.getItemPaidDate()).getTime()));
				stmt.setLong(5, item.getItemPaidBy().getId());
				stmt.setLong(6, item.getItemCreatedBy().getId());
				return stmt;
			}
		}, keyHolder);
		
		return keyHolder.getKey().longValue();
	}
	
	
	@Override
	public void linkItemToSharedUsers(final ItemVO item){
		
		//item.getItemShareUserMap()
		final String sql = "Insert INTO item_split (item_id,sheet_id,item_shared_by,item_share) values (?,?,?,?)";
		final List<Entry<String, String>> itemShareList = new ArrayList<Entry<String, String>>(item.getItemShareUserMap().entrySet());
		//itemShareList.addAll(item.getItemShareUserMap().entrySet());
		
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
	
				ps.setLong(1, item.getItemId());
				ps.setLong(2, item.getSheetId());
				ps.setLong(3, Long.parseLong(itemShareList.get(i).getKey()));
				ps.setInt(4, Integer.parseInt(itemShareList.get(i).getValue()));
				
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return itemShareList.size();
			}
		});
	}


	@Override
	public ItemVO getItem(Long sheetId, Long itemId) {
		
		final String sql = "SELECT i.item_id, i.sheet_id, i.item_description, i.item_paid_date, i.item_price, i.item_paid_by, GROUP_CONCAT(concat_ws('-', s.item_shared_by, s.item_share)) as share_detail, sum(s.item_share) as total_share, u.first_name " +
				"FROM item i LEFT JOIN item_split s ON (i.item_id = s.item_id AND i.sheet_id = s.sheet_id) " +
				"JOIN user u on i.item_paid_by=u.user_id " +
				"where i.sheet_id=? AND i.item_id=?";
		return (ItemVO)getJdbcTemplate().queryForObject(sql, new Object[]{sheetId, itemId}, new ItemRowMapper());
	}


	@Override
	public boolean removeItem(Long sheetId, Long itemId) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean editItemName(Long sheetId, Long itemId, String itemName) {
		final String sql="UPDATE item SET item_description=? where sheet_id=? AND item_id=?";
		getJdbcTemplate().update(sql, new Object[]{itemName, sheetId, itemId});
		return true;
	}

	@Override
	public boolean editItemDate(Long sheetId, Long itemId, String itemDate) {
		final String sql="UPDATE item SET item_paid_date=? where sheet_id=? AND item_id=?";
		getJdbcTemplate().update(sql, new Object[]{new Date(new java.util.Date(itemDate).getTime()), sheetId, itemId});
		return true;
	}


	@Override
	public boolean editItemPrice(Long sheetId, Long itemId, float itemPrice) {
		final String sql="UPDATE item SET item_price=? where sheet_id=? AND item_id=?";
		getJdbcTemplate().update(sql, new Object[]{itemPrice, sheetId, itemId});
		return true;
	}


	@Override
	public boolean editItemPaidBy(Long sheetId, Long itemId, Long itemPaidBy) {
		final String sql="UPDATE item SET item_paid_by=? where sheet_id=? AND item_id=?";
		getJdbcTemplate().update(sql, new Object[]{itemPaidBy, sheetId, itemId});
		return true;
	}


	@Override
	public boolean editItemSharedBy(Long sheetId, Long itemId, Long itemSharedBy, String share) {
		
		final String sql="Insert into item_split (sheet_id, item_id, item_shared_by, item_share) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE item_share=?";
		getJdbcTemplate().update(sql, new Object[]{sheetId, itemId, itemSharedBy, Integer.parseInt(share), Integer.parseInt(share)});
		return false;
	}


	@Override
	public boolean deleteItem(Long sheetId, Long itemId) {
		String sql = "DELETE FROM item_split WHERE item_id=? AND sheet_id=?";
		getJdbcTemplate().update(sql, new Object[]{itemId, sheetId});
		
		sql = "DELETE FROM item WHERE item_id=? AND sheet_id=?";
		getJdbcTemplate().update(sql, new Object[]{itemId, sheetId});
		
		return true;
	}


	


}
