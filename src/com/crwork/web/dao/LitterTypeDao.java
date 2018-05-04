package com.crwork.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.crwork.web.dbutil.CRWorkJDBC;
import com.crwork.web.model.LitterTypeModel;

/**
 * 
 * @author xiezhenlin
 *
 */
public class LitterTypeDao {
	private final static String TAG = "LitterTypeDao";

	private Connection mConnection = null;
	private CRWorkJDBC mCRWorkJDBC = null;

	public LitterTypeDao() {
		super();
		// TODO Auto-generated constructor stub
		mCRWorkJDBC = new CRWorkJDBC();
		mConnection = mCRWorkJDBC.getCRWorkConn();
	}

	/**
	 * insert littertype data
	 *
	 * @param mLitterTypeModel
	 * @return
	 */
	public boolean insertLitterTypeData(LitterTypeModel mLitterTypeModel) {

		try {
			PreparedStatement psql;
			psql = mConnection.prepareStatement("insert into " + CRWorkJDBC.LITTER_TYPE_TABLE
					+ " (littertypeID,typeName,typemark,price)" + "values(?,?,?,?)");
			psql.setInt(1, mLitterTypeModel.getLittertypeID());
			psql.setString(2, mLitterTypeModel.getTypeName());
			psql.setInt(3, mLitterTypeModel.getTypemark());
			psql.setDouble(4, mLitterTypeModel.getPrice());
			psql.executeUpdate();
			psql.close();

			System.out.println(TAG + "insertLitterTypeData() success!" + "\n");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				mConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(TAG + "insertLitterTypeData() completed!" + "\n");
		}
		return false;
	}

	/**
	 * update litter type data
	 *
	 * @param mLitterTypeModel
	 * @return
	 */
	public boolean updateLitterTypeData(LitterTypeModel mLitterTypeModel) {

		try {
			PreparedStatement psql;
			psql = mConnection.prepareStatement("update" + CRWorkJDBC.LITTER_TYPE_TABLE
					+ "set typeName=?,typemark=?,price=? where littertypeID=? ");
			psql.setString(1, mLitterTypeModel.getTypeName());
			psql.setInt(2, mLitterTypeModel.getTypemark());
			psql.setDouble(3, mLitterTypeModel.getPrice());
			psql.setInt(4, mLitterTypeModel.getLittertypeID());
			psql.executeUpdate();
			psql.close();

			System.out.println(TAG + "updateLitterTypeData() success!" + "\n");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				mConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(TAG + "updateLitterTypeData() completed!" + "\n");
		}
		return false;
	}

	/**
	 * delete litter type data
	 *
	 * @param littertypeID
	 * @return
	 */
	public boolean deleteLitterTypeData(int littertypeID) {

		try {
			PreparedStatement psql;
			psql = mConnection
					.prepareStatement("delete from" + CRWorkJDBC.LITTER_TYPE_TABLE + " where littertypeID=? ");
			psql.setInt(1, littertypeID);
			psql.executeUpdate();
			psql.close();

			System.out.println(TAG + "deleteLitterTypeData()  success!" + "\n");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				mConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(TAG + "deleteLitterTypeData() completed!" + "\n");
		}
		return false;
	}
}
