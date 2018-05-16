package com.crwork.web.model;

import java.sql.Date;

/**
 * LitterDomain
 * 
 * @author xiezhenlin
 *
 */
public class LitterModel {
	private int ID;
	private int userId;
	private int littertypeID;
	private Double weight;
	private Double tPrice;
	private Date litterdate;

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

	public int getLittertypeID() {
		return littertypeID;
	}

	public void setLittertypeID(int littertypeID) {
		this.littertypeID = littertypeID;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double gettPrice() {
		return tPrice;
	}

	public void settPrice(Double tPrice) {
		this.tPrice = tPrice;
	}

	public Date getLitterdate() {
		return litterdate;
	}

	public void setLitterdate(Date litterdate) {
		this.litterdate = litterdate;
	}
}
