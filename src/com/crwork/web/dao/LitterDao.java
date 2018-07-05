package com.crwork.web.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.crwork.web.dbutil.CRWorkJDBC;
import com.crwork.web.model.LitterModel;
import com.crwork.web.util.Arith;
import com.crwork.web.util.DateUtil;
import com.crwork.web.util.StringUtil;

/**
 * @author xiezhenlin
 */
public class LitterDao {
	private final static String TAG = "LitterDao";

	private Connection mConnection = null;
	private CRWorkJDBC mCRWorkJDBC = null;
	LitterTypeDao mLitterTypeDao = null;

	public LitterDao() {
		super();
		// TODO Auto-generated constructor stub
		mCRWorkJDBC = new CRWorkJDBC();
		mConnection = mCRWorkJDBC.getCRWorkConn();
		mLitterTypeDao = new LitterTypeDao();
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
					+ " (userId,littertypeID,weight,tPrice,litterdate)" + "values(?,?,?,?,?)");
			psql.setString(1, mLitterModel.getUserId());
			psql.setInt(2, mLitterModel.getLittertypeID());
			psql.setDouble(3, mLitterModel.getWeight());
			psql.setDouble(4, mLitterModel.gettPrice());
			psql.setDate(5, mLitterModel.getLitterdate());
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
	public ArrayList<LitterModel> queryLitterDataByUserID(String userId) {
		ArrayList<LitterModel> lmList = new ArrayList<LitterModel>();
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql.executeQuery(
					"SELECT * FROM " + CRWorkJDBC.LITTER_TABLE + (userId.equals("0") ? "" : " where userId=" + userId));
			LitterModel mLitterModel = null;
			while (rs.next()) {
				mLitterModel = new LitterModel();
				mLitterModel.setID(rs.getInt(1));
				mLitterModel.setUserId(rs.getString(2));
				mLitterModel.setLittertypeID(rs.getInt(3));
				mLitterModel.setWeight(rs.getDouble(4));
				mLitterModel.settPrice(rs.getDouble(5));
				mLitterModel.setLitterdate(rs.getDate(6));
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
				mLitterModel.setUserId(list_temp[0]);
				mLitterModel.setLittertypeID(Integer.parseInt(list_temp[1]));
				mLitterModel.setWeight(Double.parseDouble(list_temp[2]));
				mLitterModel.settPrice(mLitterTypeDao.getTPriceByLitterTypeId(Integer.parseInt(list_temp[1]))
						* Double.parseDouble(list_temp[2]));
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
				mLitterModel.setUserId(list_temp[0]);
				mLitterModel.setLittertypeID(Integer.parseInt(list_temp[1]));
				mLitterModel.setWeight(Double.parseDouble(list_temp[2]));
				mLitterModel
						.settPrice(new BigDecimal(mLitterTypeDao.getTPriceByLitterTypeId(Integer.parseInt(list_temp[1]))
								* Double.parseDouble(list_temp[2])).setScale(2, BigDecimal.ROUND_HALF_UP)
										.doubleValue());
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

	/**
	 * get litter by date and usernameF
	 * 
	 * @param UserName
	 * @param mStartDate
	 * @param mEndDate
	 * @return
	 */
	public ArrayList<LitterModel> getLitterListByDate(String UserName, Date mStartDate, Date mEndDate) {
		UserDao mUserDao = new UserDao();
		int UserId = mUserDao.getUserIdByUserName(UserName);
		ArrayList<LitterModel> lmList = new ArrayList<LitterModel>();
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql.executeQuery("SELECT * FROM " + CRWorkJDBC.LITTER_TABLE
					+ (mStartDate == null || mEndDate == null ? ";"
							: " where litterdate >= '" + mStartDate + "' and litterdate <= '" + mEndDate
									+ "' and userId = '" + UserId + "';"));
			LitterModel mLitterModel = null;
			while (rs.next()) {
				mLitterModel = new LitterModel();
				mLitterModel.setID(rs.getInt(1));
				mLitterModel.setUserId(rs.getString(2));
				mLitterModel.setLittertypeID(rs.getInt(3));
				mLitterModel.setWeight(rs.getDouble(4));
				mLitterModel.settPrice(rs.getDouble(5));
				mLitterModel.setLitterdate(rs.getDate(6));
				lmList.add(mLitterModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lmList;
	}

	/**
	 * get litter data union
	 * 
	 * @return
	 */
	public ArrayList<String[]> exportLitterData() {
		ArrayList<String[]> mLitterList = new ArrayList<String[]>();
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql.executeQuery(
					"SELECT cu.userId,cu.userName,cc.city_name_zh,cl.weight,clt.typeName,cl.littertypeID,cl.tPrice,cl.litterdate FROM crwork_user cu INNER JOIN crwork_litter cl On cu.userId = cl.userId INNER JOIN crwork_litter_type clt On cl.littertypeID = clt.littertypeID INNER JOIN crwork_citys cc On cc.id = cu.regionID ;");
			String[] mLitter = null;
			while (rs.next()) {
				mLitter = new String[8];
				mLitter[0] = rs.getString(1);
				mLitter[1] = rs.getString(2);
				mLitter[2] = rs.getString(3);
				mLitter[3] = String.valueOf(rs.getDouble(4));
				mLitter[4] = rs.getString(5);
				mLitter[5] = String.valueOf(rs.getInt(6));
				mLitter[6] = String.valueOf(rs.getDouble(7));
				mLitter[7] = String.valueOf(rs.getDate(8));
				mLitterList.add(mLitter);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mLitterList;
	}

	/**
	 * get litter data union
	 * 
	 * @param UserName
	 * @param mStartDate
	 * @param mEndDate
	 * @return
	 */
	public ArrayList<String[]> exportLitterData(String UserName, String regionName, Date mStartDate, Date mEndDate) {
		ArrayList<String[]> mLitterList = new ArrayList<String[]>();
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql.executeQuery(
					"SELECT cu.userId,cu.userName,cc.city_name_zh,cl.weight,clt.typeName,cl.littertypeID,cl.tPrice,cl.litterdate FROM crwork_user cu INNER JOIN crwork_litter cl On cu.userId = cl.userId INNER JOIN crwork_litter_type clt On cl.littertypeID = clt.littertypeID INNER JOIN crwork_citys cc On cc.id = cu.regionID"
							+ (mStartDate == null || mEndDate == null ? ";"
									: " where cl.litterdate >= '" + mStartDate + "' and cl.litterdate <= '" + mEndDate
											+ (UserName == null || UserName.equals("") ? ""
													: "' and cu.userName = '" + UserName)
											+ (regionName == null || regionName.equals("") ? ""
													: "' and cc.city_name_zh = '" + regionName)
											+ "';"));
			String[] mLitter = null;
			while (rs.next()) {
				mLitter = new String[8];
				mLitter[0] = rs.getString(1);
				mLitter[1] = rs.getString(2);
				mLitter[2] = rs.getString(3);
				mLitter[3] = String.valueOf(rs.getDouble(4));
				mLitter[4] = rs.getString(5);
				mLitter[5] = String.valueOf(rs.getInt(6));
				mLitter[6] = String.valueOf(rs.getDouble(7));
				mLitter[7] = String.valueOf(rs.getDate(8));
				mLitterList.add(mLitter);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(TAG + e + "\n");
		}
		return mLitterList;
	}

	/**
	 * 获取重量信息
	 * 
	 * @param littertypeID
	 * @return
	 */
	public Double getLitterWeightTotal(int littertypeID) {
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql.executeQuery("select sum(weight) from " + CRWorkJDBC.LITTER_TABLE
					+ (littertypeID > 2 ? "" : " where littertypeID=" + littertypeID));
			while (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.00;

	}

	/**
	 * 获取重量信息集
	 * 
	 * @return
	 */
	public String[] getLitterWeight_I() {
		Double litter_weight_total_ur = getLitterWeightTotal(0);
		Double litter_weight_total_r = getLitterWeightTotal(1);
		Double litter_weight_total_k = getLitterWeightTotal(2);
		Double litter_weight_total = getLitterWeightTotal(3);
		Double ur_percent = Arith.div(litter_weight_total_ur, litter_weight_total, 4);
		Double r_percent = Arith.div(litter_weight_total_r, litter_weight_total, 4);
		Double k_percent = Arith.div(litter_weight_total_k, litter_weight_total, 4);
		String[] litterweight_i = new String[7];
		litterweight_i[0] = litter_weight_total_ur > 1000
				? String.valueOf(Arith.div(litter_weight_total_ur, 1000.00, 5)) + StringUtil.WEIGHT_TON_TIP
				: String.valueOf(litter_weight_total_ur) + StringUtil.WEIGHT_KG_TIP;
		litterweight_i[1] = litter_weight_total_r > 1000
				? String.valueOf(Arith.div(litter_weight_total_r, 1000.00, 5)) + StringUtil.WEIGHT_TON_TIP
				: String.valueOf(litter_weight_total_r) + StringUtil.WEIGHT_KG_TIP;
		litterweight_i[2] = Arith.div(litter_weight_total_k, 1000.00, 5) > 1000
				? String.valueOf(litter_weight_total_k) + StringUtil.WEIGHT_TON_TIP
				: String.valueOf(litter_weight_total_k) + StringUtil.WEIGHT_KG_TIP;
		litterweight_i[3] = litter_weight_total > 1000
				? String.valueOf(Arith.div(litter_weight_total, 1000.00, 5)) + StringUtil.WEIGHT_TON_TIP
				: String.valueOf(litter_weight_total) + StringUtil.WEIGHT_KG_TIP;
		litterweight_i[4] = String.valueOf(String.format("%.2f", ur_percent * 100));
		litterweight_i[5] = String.valueOf(String.format("%.2f", r_percent * 100));
		litterweight_i[6] = String.valueOf(String.format("%.2f", k_percent * 100));
		return litterweight_i;
	}

	/**
	 * 获取费用收入信息
	 * 
	 * @param littertypeID
	 * @return
	 */
	public Double getLitterPriceTotal(int littertypeID) {
		try {
			Statement sql = mConnection.createStatement();
			ResultSet rs = sql.executeQuery("select sum(tPrice) from " + CRWorkJDBC.LITTER_TABLE
					+ (littertypeID > 2 ? "" : " where littertypeID=" + littertypeID));
			while (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.00;

	}

	/**
	 * 获取费用信息集
	 * 
	 * @return
	 */
	public String[] getLitterPrice_I() {
		Double litter_weight_price_ur = getLitterPriceTotal(0);
		Double litter_weight_price_r = getLitterPriceTotal(1);
		Double litter_weight_price_k = getLitterPriceTotal(2);
		String litter_weight_price_io;
		String litter_weight_price_io_tip;
		String[] litterprice_i = new String[5];
		if (litter_weight_price_ur > litter_weight_price_r) {
			litter_weight_price_io_tip = StringUtil.OUTCOME_TIP;
			litter_weight_price_io = String.valueOf(Arith.sub(litter_weight_price_ur, litter_weight_price_r));
		} else {
			litter_weight_price_io_tip = StringUtil.INCOME_TIP;
			litter_weight_price_io = String.valueOf(Arith.sub(litter_weight_price_r, litter_weight_price_ur));
		}
		litterprice_i[0] = String.valueOf(litter_weight_price_ur) + StringUtil.RMB_TIP;
		litterprice_i[1] = String.valueOf(litter_weight_price_r) + StringUtil.RMB_TIP;
		litterprice_i[2] = String.valueOf(litter_weight_price_k) + StringUtil.RMB_TIP;
		litterprice_i[3] = String.valueOf(litter_weight_price_io) + StringUtil.RMB_TIP;
		litterprice_i[4] = litter_weight_price_io_tip;
		return litterprice_i;
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
