package com.massoftind.rnd.firechatonetoone.datamodal;

import java.io.Serializable;

public class ApiLogin implements Serializable {
	private String RoleName="";
	private String UserName="";
	private String UserId="";
	private String Email="";
	private boolean EmailVerified=false;
	private String Passkey="";
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public boolean isEmailVerified() {
		return EmailVerified;
	}
	public void setEmailVerified(boolean emailVerified) {
		EmailVerified = emailVerified;
	}
	public String getPasskey() {
		return Passkey;
	}
	public void setPasskey(String passkey) {
		Passkey = passkey;
	}

}
