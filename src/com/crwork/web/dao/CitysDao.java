package com.crwork.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.crwork.web.model.CitysModel;
import com.crwork.web.dbutil.CRWorkJDBC;

public class CitysDao {
	private final static String TAG = "CitysDao";

	private Connection mConnection = null;
	private CRWorkJDBC mCRWorkJDBC = null;

	public CitysDao() {
		super();
		// TODO Auto-generated constructor stub
		mCRWorkJDBC = new CRWorkJDBC();
		mConnection = mCRWorkJDBC.getCRWorkConn();
		if (mConnection != null) {
			System.out.print(TAG + " not null database!");
		} else {
			System.out.print(TAG + " is null database!");
		}
	}

	/**
	 * 获取区域对象集
	 * 
	 * @param parent_id
	 * @return
	 */
	public ArrayList<CitysModel> getCitys(int parent_id) {
		ArrayList<CitysModel> lmList = new ArrayList<CitysModel>();
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql
					.executeQuery("SELECT * FROM " + CRWorkJDBC.CITY_TABLE + " where parent_id = " + parent_id);
			CitysModel mCitysModel = null;
			while (rs.next()) {
				mCitysModel = new CitysModel();
				mCitysModel.setId(rs.getInt(1));
				mCitysModel.setParent_id(rs.getInt(2));
				mCitysModel.setCity_name_zh(rs.getString(3));
				mCitysModel.setCity_name_en(rs.getString(4));
				mCitysModel.setCity_level(rs.getInt(5));
				mCitysModel.setCity_code(rs.getString(6));
				mCitysModel.setCity_status_cr(rs.getInt(7));
				lmList.add(mCitysModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				mConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lmList;
	}

	/**
	 * 新增区域
	 * 
	 * @param mCitysModel
	 * @return
	 */
	public boolean AddCitys(CitysModel mCitysModel) {
		try {
			String sql = "insert into " + CRWorkJDBC.CITY_TABLE
					+ " (parent_id,city_name_zh,city_level,city_code,city_status_cr)" + "values(?,?,?,?,?)";
			PreparedStatement pst = mConnection.prepareStatement(sql);
			pst.setInt(1, mCitysModel.getParent_id());
			pst.setString(2, mCitysModel.getCity_name_zh());
			pst.setInt(3, mCitysModel.getCity_level());
			pst.setString(4, mCitysModel.getCity_code());
			pst.setInt(5, mCitysModel.getCity_status_cr());
			pst.executeUpdate();
			pst.close();
			System.out.println("AddCitys()  success!" + "\n");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				mConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("AddCitys() completed!" + "\n");
		}
		return false;
	}

	/**
	 * 获取区域对象
	 * 
	 * @param id
	 * @return
	 */
	public CitysModel getCity(int id) {
		// TODO Auto-generated method stub
		CitysModel mCitysModel = null;
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql.executeQuery("SELECT * FROM " + CRWorkJDBC.CITY_TABLE + " where id = " + id);
			while (rs.next()) {
				mCitysModel = new CitysModel();
				mCitysModel.setId(rs.getInt(1));
				mCitysModel.setParent_id(rs.getInt(2));
				mCitysModel.setCity_name_zh(rs.getString(3));
				mCitysModel.setCity_name_en(rs.getString(4));
				mCitysModel.setCity_level(rs.getInt(5));
				mCitysModel.setCity_code(rs.getString(6));
				mCitysModel.setCity_status_cr(rs.getInt(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				mConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mCitysModel;
	}

	/**
	 * 获取自身父节点相同的集合
	 * 
	 * @param parent_id
	 * @return
	 */
	public ArrayList<CitysModel> getPreCitys(int parent_id) {
		return getCitys(getPreParent_Id(parent_id));
	}

	/**
	 * 获取自身父节点的父节点ID
	 * 
	 * @param parent_id
	 * @return
	 */
	public int getPreParent_Id(int parent_id) {
		int pre_parent_id = 0;
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql
					.executeQuery("SELECT parent_id FROM " + CRWorkJDBC.CITY_TABLE + " where id = " + parent_id);
			while (rs.next()) {
				pre_parent_id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pre_parent_id;

	}
}
