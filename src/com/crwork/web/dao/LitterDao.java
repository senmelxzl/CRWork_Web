package com.crwork.web.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.crwork.web.model.LitterModel;
import com.crwork.web.util.DateUtil;

/**
 * @author xiezhenlin
 */
public class LitterDao {
	private final static String TAG = "LitterDao";

	public LitterDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * insert litter data
	 *
	 * @param mLitterModel
	 * @return
	 */
	public boolean insertLitterData(LitterModel mLitterModel) {
		return true;
	}

	/**
	 * query litter data
	 *
	 * @param userID
	 * @return
	 */
	public ArrayList<LitterModel> queryLitterData(int userID) {
		return null;
	}

	/**
	 * analysis txt content
	 *
	 * @param dataFile
	 * @return
	 */
	public ArrayList<LitterModel> readDatafromFile(String filePath) {
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
				mLitterModel.setUserID(Integer.parseInt(list_temp[0]));
				mLitterModel.setLittertypeID(Integer.parseInt(list_temp[1]));
				mLitterModel.setWeight(Double.parseDouble(list_temp[2]));
				mLitterModel.setLitterdate(DateUtil.getLitterDate());
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
}
