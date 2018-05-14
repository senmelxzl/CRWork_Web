package com.crwork.web.model;

import java.sql.Date;

/**
 * user model
 * 
 * @author Amao
 *
 */
public class UserModel {

	private int ID;
	private int userId;
	private String userName;
	private int regionID;
	/**
	 * 0:超级管理员; 1:管理员;2:普通用户
	 */
	private int userType;
	private Date userRD;
	private String psw;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

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

	public int getRegionID() {
		return regionID;
	}

	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Date getUserRD() {
		return userRD;
	}

	public void setUserRD(Date userRD) {
		this.userRD = userRD;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

}
