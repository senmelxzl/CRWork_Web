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
	private String userId;
	private String userName;
	private int regionID;
	/**
	 * 0:超级管理员; 1:管理员;2:普通用户
	 */
	private int userType;
	private Date registeredDate;
	private String psw;
	private int iscr;
	private int menbers;// 人口

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public int getIscr() {
		return iscr;
	}

	public void setIscr(int iscr) {
		this.iscr = iscr;
	}

	public int getMenbers() {
		return menbers;
	}

	public void setMenbers(int menbers) {
		this.menbers = menbers;
	}

}
