package com.pkb.expense.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.pkb.expense.dao.UserDao;
import com.pkb.expense.vo.UserVO;

/**
 * @author Bravinth
 *
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	
	public UserVO getUserInfo(Long userId){
		String query = "SELECT user_id, first_name, last_name, email_id, password FROM user WHERE user_id=?";
		return getJdbcTemplate().queryForObject(query, new Object[]{userId}, new UserMapper());
	}
	
	public UserVO getUserInfo(String emailId){
		UserVO user = findUser(emailId);
		if(user != null){
			user.setPassword(null);
		}
		return user;
	}


	@Override
	public UserVO findUser(String emailId) {
		UserVO userVo = null;
		String query = "SELECT user_id, first_name, last_name, email_id, password FROM user WHERE email_id=?";
		System.out.println("query" + query + emailId);
		try{
			userVo = getJdbcTemplate().queryForObject(query, new Object[]{emailId}, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			//When the user does not exist
		}
		
		return userVo;
	}
	
	private static final class UserMapper implements RowMapper<UserVO>{

		@Override
		public UserVO mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			UserVO userVO = new UserVO();
			userVO.setId(resultSet.getLong("user_id"));
			userVO.setFirstName(resultSet.getString("first_name"));
			userVO.setLastName(resultSet.getString("last_name"));
			userVO.setEmailId(resultSet.getString("email_id"));
			userVO.setPassword(resultSet.getString("password"));
			return userVO;
		}
		
	}


	@Override
	public Long addBuddy(final UserVO userVO) {
		
		final String query = "INSERT INTO user (email_id, first_name) VALUES(?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, userVO.getEmailId());
				stmt.setString(2, userVO.getNickName());//YOu can remove this later
				return stmt;
			}
		}, keyHolder);
		
		return keyHolder.getKey().longValue();
	}

	@Override
	public void addNickNameToUser(Long loggedInUserId, UserVO userVO) {
		final String query = "INSERT INTO nick_name (nick_name_created_by, nick_name_created_for, nick_name) VALUES(?, ?, ?)";
		getJdbcTemplate().update(query, new Object[]{loggedInUserId, userVO.getId(), userVO.getNickName()});
	}

	@Override
	public Map<String, UserVO> getUserInfo(Set<Long> userIdList) {
		/*String sql = "SELECT user_id, first_name, last_name, email_id FROM user WHERE user_id in (:ids)";
		List<Long> lis = new ArrayList<Long>();lis.add(new Long(100000001));
		Map<String, List<Long>> params = Collections.singletonMap("ids", lis);
		List<Map<String, Object>> resultSet= getJdbcTemplate().queryForList(sql, params);
		for(Map m : resultSet){
			System.out.println(m);
		}*/
		return null;
	}
	
	public List<UserVO> getUsersInSheet(Long SheetId){
		final String sql = "SELECT u.user_id, u.first_name, u.last_name, u.email_id FROM user u, user_sheet_eligibility e where u.user_id = e.eligible_user_id AND eligible_sheet_id=?";
		return getJdbcTemplate().query(sql, new Object[]{SheetId}, new UserMapperWOPassword());
	}

	private static final class UserMapperWOPassword implements RowMapper<UserVO>{

		@Override
		public UserVO mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			UserVO userVO = new UserVO();
			userVO.setId(resultSet.getLong("user_id"));
			userVO.setFirstName(resultSet.getString("first_name"));
			userVO.setLastName(resultSet.getString("last_name"));
			userVO.setEmailId(resultSet.getString("email_id"));
			return userVO;
		}
		
	}
	
	@Override
	public boolean userExistsWithNickName(Long userId, Long loggedInUserId) {
		final String sql = "SELECT count(nick_name) FROM nick_name WHERE nick_name_created_for=? AND nick_name_created_by=?";
		int count = getJdbcTemplate().queryForInt(sql, new Object[]{userId, loggedInUserId});
		if(count > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<UserVO> getBuddiesList(Long loggedInUserId) {
		final String sql = "SELECT u.user_id, u.first_name, u.email_id, n.nick_name FROM user u, nick_name n where n.nick_name_created_by=? and n.nick_name_created_for = u.user_id order by u.first_name";
		return getJdbcTemplate().query(sql, new Object[]{loggedInUserId}, new UserNickNameMapper());
	}

	private static final class UserNickNameMapper implements RowMapper<UserVO>{

		@Override
		public UserVO mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			UserVO userVO = new UserVO();
			userVO.setId(resultSet.getLong("user_id"));
			userVO.setFirstName(resultSet.getString("first_name"));
			userVO.setEmailId(resultSet.getString("email_id"));
			userVO.setNickName(resultSet.getString("nick_name"));
			return userVO;
		}
		
	}

	@Override
	public Long createNewUser(final UserVO userVO) {
		final String sql = "INSERT INTO USER (first_name, email_id, password) values (? ,? ,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, userVO.getFirstName());
				stmt.setString(2, userVO.getEmailId());
				stmt.setString(3, userVO.getPassword());
				return stmt;
			}
		}, keyHolder);
		
		return keyHolder.getKey().longValue();
	}

	@Override
	public void updateUser(Long userId, UserVO userVO) {
		final String sql="UPDATE user SET first_name=?, password=? where user_id=?";
		getJdbcTemplate().update(sql, new Object[]{userVO.getFirstName(), userVO.getPassword(), userId});
	}


	

}
