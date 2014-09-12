package com.pkb.expense.vo;

/**
 * @author Bravinth
 *
 */
public class UserVO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String nickName;
	private String emailId;
	private String password;
	
	public UserVO(){
	}
	
	public UserVO(String emailId, String nickName){
		this.emailId = emailId;
		this.nickName = nickName;
		
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getNickName() {
		return nickName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean equals(UserVO user){
		if(this.id.equals(user.id))
			return true;
		
		return false;
		
	}
}
