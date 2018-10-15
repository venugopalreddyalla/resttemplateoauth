package com.cognine.onlinetest.model;

import java.util.List;

public class User {

	private int userId;
	private String userName;
	private String password;
	private String userEmail;
	private boolean firstTimeLogin;
	private List<Role> userRoles;
	private String setFirstTimeLogin;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<Role> userRoles) {
		this.userRoles = userRoles;
	}

	public boolean isFirstTimeLogin() {
		return firstTimeLogin;
	}

	public void setFirstTimeLogin(boolean firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}

	public String getSetFirstTimeLogin() {
		return setFirstTimeLogin;
	}

	public void setSetFirstTimeLogin(String setFirstTimeLogin) {
		this.setFirstTimeLogin = setFirstTimeLogin;
	}

}
