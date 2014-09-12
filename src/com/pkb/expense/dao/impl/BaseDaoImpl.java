package com.pkb.expense.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.pkb.expense.dao.BaseDao;

/**
 * @author Bravinth
 *
 */
public class BaseDaoImpl extends NamedParameterJdbcDaoSupport implements BaseDao {
	
	/*public JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getUserName(Long userId){
		String query = "SELECT first_name FROM user WHERE user_id=?";
		return jdbcTemplate.queryForObject(query, new Object[]{userId},String.class);
	}
	
	public String getUserName1(Long userId){
		String url = "jdbc:mysql://localhost:3306/expense?user=root&password=root";
		String query = "SELECT first_name FROM user WHERE user_id=?";
		String userName = "";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userName = rs.getString("first_name");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userName;
	}*/
	
}
