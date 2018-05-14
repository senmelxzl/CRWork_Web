package com.crwork.web.model;

import java.sql.Date;

/**
 * Village data
 * 
 * @author xiezhenlin
 *
 */
public class RegionModel {
	private int ID;
	private int regionID;
	private String regionName;
	private int regionParentID;
	private int statusMark;
	private Date regionRD;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getRegionID() {
		return regionID;
	}

	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getRegionParentID() {
		return regionParentID;
	}

	public void setRegionParentID(int regionParentID) {
		this.regionParentID = regionParentID;
	}

	public int getStatusMark() {
		return statusMark;
	}

	public void setStatusMark(int statusMark) {
		this.statusMark = statusMark;
	}

	public Date getRegionRD() {
		return regionRD;
	}

	public void setRegionRD(Date regionRD) {
		this.regionRD = regionRD;
	}

}
