package com.crwork.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.crwork.web.dbutil.CRWorkJDBC;
import com.crwork.web.model.UserModel;
import com.crwork.web.util.DateUtil;

/**
 * 
 * @author xiezhenlin
 *
 */
public class UserDao {
	private Connection mConnection = null;
	private CRWorkJDBC mCRWorkJDBC = null;

	public UserDao() {
		// TODO Auto-generated constructor stub
		mCRWorkJDBC = new CRWorkJDBC();
		mConnection = mCRWorkJDBC.getCRWorkConn();
	}

	/**
	 * get user list
	 * 
	 * @return List UserModel
	 */
	public ArrayList<UserModel> getUserList() {
		ArrayList<UserModel> umlist = new ArrayList<UserModel>();
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql.executeQuery("SELECT * FROM " + CRWorkJDBC.USER_TABLE);
			UserModel mUserModel = null;
			while (rs.next()) {
				mUserModel = new UserModel();
				mUserModel.setID(rs.getInt(1));
				mUserModel.setUserID(rs.getInt(2));
				mUserModel.setUserName(rs.getString(3));
				mUserModel.setVillageID(rs.getInt(4));
				mUserModel.setUserType(rs.getInt(5));
				mUserModel.setRegisteredDate(rs.getString(6));
				umlist.add(mUserModel);
			}
			mConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return umlist;
	}

	/**
	 * insert user data
	 * 
	 * @return boolean
	 */
	public boolean insertUserInfor(UserModel mUserModel) {
		try {
			PreparedStatement psql;
			psql = mConnection.prepareStatement("insert into " + CRWorkJDBC.USER_TABLE
					+ " (userID,userName,villageID,userType,registeredDate)" + "values(?,?,?,?,?)");
			psql.setInt(1, mUserModel.getUserID());
			psql.setString(2, mUserModel.getUserName());
			psql.setInt(3, mUserModel.getVillageID());
			psql.setInt(4, mUserModel.getUserType());
			psql.setString(5, mUserModel.getRegisteredDate());
			psql.executeUpdate();
			psql.close();
			mConnection.close();
			System.out.println("insertUserInfor() insert data success!" + "\n");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("insertUserInfor() function completed!" + "\n");
		}
		return false;
	}

	/**
	 * insert user data test
	 * 
	 * @return boolean
	 */
	public boolean insertUserInforTest() {
		UserModel mUserModelTest = new UserModel();
		mUserModelTest.setUserID(1928883);
		mUserModelTest.setUserName("Tester");
		mUserModelTest.setUserType(1);
		mUserModelTest.setVillageID(12);
		mUserModelTest.setRegisteredDate(DateUtil.getLitterDate());
		return insertUserInfor(mUserModelTest);
	}
}
