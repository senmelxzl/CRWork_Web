package com.crwork.web.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.crwork.web.dbutil.CRWorkJDBC;
import com.crwork.web.model.LitterModel;
import com.crwork.web.util.DateUtil;

/**
 * @author xiezhenlin
 */
public class LitterDao {
	private final static String TAG = "LitterDao";

	private Connection mConnection = null;
	private CRWorkJDBC mCRWorkJDBC = null;

	public LitterDao() {
		super();
		// TODO Auto-generated constructor stub
		mCRWorkJDBC = new CRWorkJDBC();
		mConnection = mCRWorkJDBC.getCRWorkConn();
	}

	/**
	 * insert litter data
	 *
	 * @param mLitterModel
	 * @return
	 */
	public boolean insertLitterData(LitterModel mLitterModel) {

		try {
			PreparedStatement psql;
			psql = mConnection.prepareStatement("insert into " + CRWorkJDBC.LITTER_TABLE
					+ " (userId,littertypeID,weight,litterdate)" + "values(?,?,?,?)");
			psql.setInt(1, mLitterModel.getUserId());
			psql.setInt(2, mLitterModel.getLittertypeID());
			psql.setDouble(3, mLitterModel.getWeight());
			psql.setDate(4, mLitterModel.getLitterdate());
			psql.executeUpdate();
			psql.close();
			System.out.println("insertLitterData() insert data success!" + "\n");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("insertLitterData() function completed!" + "\n");
		}
		return false;
	}

	/**
	 * query litter data
	 *
	 * @param userID
	 * @return
	 */
	public ArrayList<LitterModel> queryLitterDataByUserID(int userId) {
		ArrayList<LitterModel> lmList = new ArrayList<LitterModel>();
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql.executeQuery(
					"SELECT * FROM " + CRWorkJDBC.LITTER_TABLE + (userId == 0 ? "" : "where userId=" + userId));
			LitterModel mLitterModel = null;
			while (rs.next()) {
				mLitterModel = new LitterModel();
				mLitterModel.setID(rs.getInt(1));
				mLitterModel.setUserId(rs.getInt(2));
				mLitterModel.setLittertypeID(rs.getInt(3));
				mLitterModel.setWeight(rs.getDouble(4));
				mLitterModel.setLitterdate(rs.getDate(5));
				lmList.add(mLitterModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lmList;
	}

	/**
	 * analysis txt content
	 *
	 * @param dataFile
	 * @return
	 */
	public ArrayList<LitterModel> readDatafromFile(File file) {
		// TODO Auto-generated method stub
		ArrayList<LitterModel> list = new ArrayList<LitterModel>();
		BufferedReader reader = null;
		String temp = null;
		int line = 1;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((temp = reader.readLine()) != null) {
				System.out.println(TAG + line + ":" + temp);
				LitterModel mLitterModel = new LitterModel();
				String[] list_temp = temp.split(" ");
				mLitterModel.setUserId(Integer.parseInt(list_temp[0]));
				mLitterModel.setLittertypeID(Integer.parseInt(list_temp[1]));
				mLitterModel.setWeight(Double.parseDouble(list_temp[2]));
				mLitterModel.setLitterdate(DateUtil.getCurrentDate());
				list.add(mLitterModel);
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * analysis txt content
	 *
	 * @param dataFile
	 * @return
	 */
	public ArrayList<LitterModel> readDatafromFilePath(String filePath) {
		// TODO Auto-generated method stub
		ArrayList<LitterModel> list = new ArrayList<LitterModel>();
		BufferedReader reader = null;
		String temp = null;
		int line = 1;
		try {
			reader = new BufferedReader(new FileReader(new File(filePath)));
			while ((temp = reader.readLine()) != null) {
				System.out.println(TAG + line + ":" + temp);
				LitterModel mLitterModel = new LitterModel();
				String[] list_temp = temp.split(" ");
				mLitterModel.setUserId(Integer.parseInt(list_temp[0]));
				mLitterModel.setLittertypeID(Integer.parseInt(list_temp[1]));
				mLitterModel.setWeight(Double.parseDouble(list_temp[2]));
				mLitterModel.setLitterdate(DateUtil.getCurrentDate());
				list.add(mLitterModel);
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * for upload litter data from local file
	 *
	 * @param mLitterModelList
	 * @return
	 */
	public boolean uploadLitterlistData(ArrayList<LitterModel> mLitterModelList) {
		for (int i = 0; i < mLitterModelList.size(); i++) {
			if (insertLitterData(mLitterModelList.get(i))) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public ArrayList<LitterModel> getLitterListByDate(Date mStartDate, Date mEndDate) {
		ArrayList<LitterModel> lmList = new ArrayList<LitterModel>();
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql.executeQuery(
					"SELECT * FROM " + CRWorkJDBC.LITTER_TABLE + (mStartDate == null || mEndDate == null ? ";"
							: " where litterdate >= '" + mStartDate + "' and litterdate <= '" + mEndDate + "';"));
			LitterModel mLitterModel = null;
			while (rs.next()) {
				mLitterModel = new LitterModel();
				mLitterModel.setID(rs.getInt(1));
				mLitterModel.setUserId(rs.getInt(2));
				mLitterModel.setLittertypeID(rs.getInt(3));
				mLitterModel.setWeight(rs.getDouble(4));
				mLitterModel.setLitterdate(rs.getDate(5));
				lmList.add(mLitterModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lmList;
	}

	public void CloseConnection() {
		try {
			mConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
