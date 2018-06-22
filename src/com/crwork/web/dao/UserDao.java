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
	}

	/**
	 * get user list
	 * 
	 * @return List UserModel
	 */
	public ArrayList<String[]> getUserList() {
		ArrayList<String[]> umlist = new ArrayList<String[]>();
		mConnection = mCRWorkJDBC.getCRWorkConn();
		try {
			String sql = "SELECT cu.ID, cu.userId,cu.userName,cc.city_name_zh,cu.regionID,cu.userType,cu.registeredDate,cu.iscr FROM crwork_user cu INNER JOIN crwork_citys cc On cu.regionID = cc.id";
			Statement st = mConnection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			String[] mUserModel = null;
			while (rs.next()) {
				mUserModel = new String[8];
				mUserModel[0] = String.valueOf(rs.getInt(1));
				mUserModel[1] = rs.getString(2);
				mUserModel[2] = rs.getString(3);
				mUserModel[3] = rs.getString(4);
				mUserModel[4] = String.valueOf(rs.getInt(5));
				mUserModel[5] = String.valueOf(rs.getInt(6));
				mUserModel[6] = String.valueOf(rs.getDate(7));
				mUserModel[7] = String.valueOf(rs.getInt(8));
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
	 * get user information
	 * 
	 * @return UserModel
	 */
	public UserModel getUser(String userId, String psw) {
		UserModel mUserModel = null;
		mConnection = mCRWorkJDBC.getCRWorkConn();
		try {
			String sql = "select * from " + CRWorkJDBC.USER_TABLE + " where userId=? and psw=?";
			PreparedStatement psmt = mConnection.prepareStatement(sql);
			psmt.setString(1, userId);
			psmt.setString(2, psw);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				mUserModel = new UserModel();
				mUserModel.setID(rs.getInt(1));
				mUserModel.setUserId(rs.getString(2));
				mUserModel.setUserName(rs.getString(3));
				mUserModel.setRegionID(rs.getInt(4));
				mUserModel.setUserType(rs.getInt(5));
				mUserModel.setRegisteredDate(rs.getDate(6));
				mUserModel.setPsw(rs.getString(7));
			}
			System.out.println("getUserInfor()  success!" + "\n");
			mConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("getUserInfor() completed!" + "\n");
		return mUserModel;
	}

	/**
	 * insert user data
	 * 
	 * @return boolean
	 */
	public boolean insertUser(UserModel mUserModel) {
		mConnection = mCRWorkJDBC.getCRWorkConn();
		try {
			String sql = "insert into " + CRWorkJDBC.USER_TABLE
					+ " (userId,userName,regionID,userType,registeredDate,psw,iscr)" + "values(?,?,?,?,?,?,?)";
			PreparedStatement pst = mConnection.prepareStatement(sql);
			pst.setString(1, mUserModel.getUserId());
			pst.setString(2, mUserModel.getUserName());
			pst.setInt(3, mUserModel.getRegionID());
			pst.setInt(4, mUserModel.getUserType());
			pst.setDate(5, DateUtil.getCurrentDate());
			pst.setString(6, "12345678");
			pst.setInt(7, mUserModel.getIscr());
			pst.executeUpdate();
			pst.close();
			mConnection.close();
			System.out.println("insertUserInfor()  success!" + "\n");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("insertUserInfor() completed!" + "\n");
		}
		return false;
	}

	/**
	 * update user data
	 * 
	 * @return boolean
	 */
	public boolean updateUser(UserModel mUserModel) {
		mConnection = mCRWorkJDBC.getCRWorkConn();
		try {
			String sql = "update " + CRWorkJDBC.USER_TABLE
					+ " set userName=?,regionID=?,userType=?,userId=?,iscr=? where ID=?";
			PreparedStatement pst = mConnection.prepareStatement(sql);
			pst.setString(1, mUserModel.getUserName());
			pst.setInt(2, mUserModel.getRegionID());
			pst.setInt(3, mUserModel.getUserType());
			pst.setString(4, mUserModel.getUserId());
			pst.setInt(5, mUserModel.getIscr());
			pst.setInt(6, mUserModel.getID());
			pst.executeUpdate();
			pst.close();
			mConnection.close();
			System.out.println("updateUserInfor()  success!" + "\n");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("updateUserInfor() completed!" + "\n");
		}
		return false;
	}

	/**
	 * update user psw
	 * 
	 * @return boolean
	 */
	public boolean updateUserPsw(String userId, String psw) {
		mConnection = mCRWorkJDBC.getCRWorkConn();
		try {
			String sql = "update " + CRWorkJDBC.USER_TABLE + " set psw=? where userId=?";
			PreparedStatement pst = mConnection.prepareStatement(sql);
			pst.setString(1, psw);
			pst.setString(2, userId);
			pst.executeUpdate();
			pst.close();
			mConnection.close();
			System.out.println("updateUserPsw()  success!" + "\n");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("updateUserPsw() completed!" + "\n");
		}
		return false;
	}

	/**
	 * delete user data
	 * 
	 * @return boolean
	 */
	public boolean deleteUser(String userId) {
		mConnection = mCRWorkJDBC.getCRWorkConn();
		try {
			String sql = "delete from " + CRWorkJDBC.USER_TABLE + " where userId=?";
			PreparedStatement pst = mConnection.prepareStatement(sql);
			pst.setString(1, userId);
			pst.executeUpdate();
			pst.close();
			mConnection.close();
			System.out.println("deleteUserInfor()  success!" + "\n");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("deleteUserInfor() completed!" + "\n");
		}
		return false;
	}

	/**
	 * get userId by Name
	 * 
	 * @param userName
	 * @return
	 */
	public int getUserIdByUserName(String userName) {
		// TODO Auto-generated method stub
		int UserId = 0;
		mConnection = mCRWorkJDBC.getCRWorkConn();
		try {
			String sql = "select userId from " + CRWorkJDBC.USER_TABLE + " where userName=?";
			PreparedStatement psmt = mConnection.prepareStatement(sql);
			psmt.setString(1, userName);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				UserId = rs.getInt(1);
			}
			System.out.println("getUserIdByUserName()  success!" + UserId + "\n");
			mConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("getUserIdByUserName() completed!" + UserId + "\n");
		return UserId;
	}

	/**
	 * get userId by Name
	 * 
	 * @param userName
	 * @return
	 */
	public String getUserNameByUserId(String userId) {
		// TODO Auto-generated method stub
		String UserName = "";
		mConnection = mCRWorkJDBC.getCRWorkConn();
		try {
			String sql = "select userName from " + CRWorkJDBC.USER_TABLE + " where userId=?";
			PreparedStatement psmt = mConnection.prepareStatement(sql);
			psmt.setString(1, userId);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				UserName = rs.getString(1);
			}
			System.out.println("getUserIdByUserName()  success!" + UserName + "\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("getUserIdByUserName() completed!" + UserName + "\n");
		return UserName;
	}
}
