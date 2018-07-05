package com.crwork.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.crwork.web.dao.LitterDao;
import com.crwork.web.util.Arith;
import com.crwork.web.util.DateUtil;

/**
 * Servlet implementation class LitterServlet
 */
@WebServlet(description = "LitterServlet", urlPatterns = { "/LitterServlet" })
public class LitterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LD_EXPORT_DIRECTORY = "LDExcel";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LitterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		LitterDao mLitterDao = new LitterDao();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userName = request.getParameter("ld_username");
		String ld_region = request.getParameter("ld_region");
		String ld_start_date_str = request.getParameter("ld_start_date");
		String ld_end_date_str = request.getParameter("ld_end_date");
		String submit_ld_search = request.getParameter("ld_search");
		String submitbtn_ld_export = request.getParameter("ld_export");
		ArrayList<String[]> mLitterModelList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date ld_start_date = null;
		Date ld_end_date = null;
		String ld_mark = "";
		String Redirect_url = "/crwork_bsm_forms/query.jsp";

		Double totalWeight = 0.00;
		Double totalWeight_R = 0.00;
		Double totalWeight_UR = 0.00;
		Double totalWeight_K = 0.00;

		Double totalCost = 0.00;
		Double totalIncome = 0.00;
		Double totalKitchen = 0.00;
		Double totalEarnings = 0.00;

		Double[] total_strs = new Double[9];

		if (!ld_start_date_str.equals("") && !ld_end_date_str.equals("")) {
			try {
				ld_start_date = sdf.parse(ld_start_date_str);
				ld_end_date = sdf.parse(ld_end_date_str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Fetch litter data from:" + ld_start_date_str + " to " + ld_end_date_str + "start!!!");
			mLitterModelList = mLitterDao.exportLitterData(userName, ld_region,
					new java.sql.Date(ld_start_date.getTime()), new java.sql.Date(ld_end_date.getTime()));
			System.out.println("Fetch litter data end!!!" + "\n");

			if (mLitterModelList.size() != 0) {
				System.out.print("Litter data size:" + mLitterModelList.size() + "\n");
				for (int i = 0; i < mLitterModelList.size(); i++) {
					String littertype = mLitterModelList.get(i)[5];
					if (littertype.equals("0")) {
						totalCost = Arith.add(totalCost, Double.parseDouble(mLitterModelList.get(i)[6]));
						totalWeight_UR = Arith.add(totalWeight_UR, Double.parseDouble(mLitterModelList.get(i)[3]));
					} else if (littertype.equals("1")) {
						totalIncome = Arith.add(totalIncome, Double.parseDouble(mLitterModelList.get(i)[6]));
						totalWeight_R = Arith.add(totalWeight_R, Double.parseDouble(mLitterModelList.get(i)[3]));
					} else if (littertype.equals("2")) {
						totalKitchen = Arith.add(totalKitchen, Double.parseDouble(mLitterModelList.get(i)[6]));
						totalWeight_K = Arith.add(totalWeight_K, Double.parseDouble(mLitterModelList.get(i)[3]));
					}
					totalWeight = Arith.add(totalWeight, Double.parseDouble(mLitterModelList.get(i)[3]));

					System.out.print("userId:" + mLitterModelList.get(i)[0] + " userName:" + mLitterModelList.get(i)[1]
							+ " regionName:" + mLitterModelList.get(i)[2] + " weight:" + mLitterModelList.get(i)[3]
							+ " typeName:" + mLitterModelList.get(i)[4] + " littertypeID:" + mLitterModelList.get(i)[5]
							+ " price:" + mLitterModelList.get(i)[6] + " litterdate:" + mLitterModelList.get(i)[7]
							+ "\n");

				}
			}

			System.out.println("总重量:" + totalWeight + "\n");

			System.out.println("废弃物重量:" + totalWeight_UR + "\n");
			System.out.println("可回收重量:" + totalWeight_R + "\n");
			System.out.println("厨余重量:" + totalWeight_K + "\n");

			System.out.println("总费用:" + totalCost + "\n");
			System.out.println("可回收总收入:" + totalIncome + "\n");
			System.out.println("厨余总价值:" + totalKitchen + "\n");
			total_strs[0] = totalWeight;
			total_strs[1] = totalWeight_UR;
			total_strs[2] = totalWeight_R;
			total_strs[3] = totalWeight_K;
			total_strs[4] = totalCost;
			total_strs[5] = totalIncome;
			total_strs[6] = totalKitchen;
			if (totalCost > totalIncome) {
				total_strs[7] = Arith.sub(totalCost, totalIncome);
				total_strs[8] = 1.00;
			} else {
				total_strs[7] = Arith.sub(totalIncome, totalCost);
				total_strs[8] = 0.00;
			}
			// Search litter data
			if (submit_ld_search != null) {
				System.out.println("function is:" + submit_ld_search + "\n");
				ld_mark = "0";
			}

			// Export litter data
			if (submitbtn_ld_export != null) {
				System.out.println("function is:" + submitbtn_ld_export + "\n");
				FileSystemView fsv = FileSystemView.getFileSystemView();
				File com = fsv.getHomeDirectory();// 获取桌面目录文件路径
				String exportPath = com.getAbsolutePath() + File.separator + LD_EXPORT_DIRECTORY;
				File exportDir = new File(exportPath);
				if (!exportDir.exists()) {
					exportDir.mkdir();
				}
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("sheet1");
				HSSFRow rowtitle = sheet.createRow(0);
				HSSFCell celltitle = rowtitle.createCell(0);
				celltitle.setCellValue(
						ld_region + "的垃圾数据统计表" + "(日期：" + ld_start_date_str + " 至  " + ld_end_date_str + ")");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));

				HSSFRow rowTotalWeight_UR = sheet.createRow(1);
				HSSFCell cellTotalWeight_UR = rowTotalWeight_UR.createCell(0);
				cellTotalWeight_UR.setCellValue("废弃物重量:");
				HSSFCell cellTotalWeight_UR_val = rowTotalWeight_UR.createCell(1);
				cellTotalWeight_UR_val.setCellValue(totalWeight_UR);
				HSSFCell cellTotalWeight_UR_val_str = rowTotalWeight_UR.createCell(2);
				cellTotalWeight_UR_val_str.setCellValue("kg/公斤");

				HSSFRow rowTotalWeight_R = sheet.createRow(2);
				HSSFCell cellTotalWeight_R = rowTotalWeight_R.createCell(0);
				cellTotalWeight_R.setCellValue("可回收重量:");
				HSSFCell cellTotalWeight_R_val = rowTotalWeight_R.createCell(1);
				cellTotalWeight_R_val.setCellValue(totalWeight_R);
				HSSFCell cellTotalWeight_R_val_str = rowTotalWeight_R.createCell(2);
				cellTotalWeight_R_val_str.setCellValue("kg/公斤");

				HSSFRow rowTotalWeight_K = sheet.createRow(3);
				HSSFCell cellTotalWeight_K = rowTotalWeight_K.createCell(0);
				cellTotalWeight_K.setCellValue("厨余重量:");
				HSSFCell cellTotalWeight_K_val = rowTotalWeight_K.createCell(1);
				cellTotalWeight_K_val.setCellValue(totalWeight_K);
				HSSFCell cellTotalWeight_K_val_str = rowTotalWeight_K.createCell(2);
				cellTotalWeight_K_val_str.setCellValue("kg/公斤");

				HSSFRow rowTotalWeight = sheet.createRow(4);
				HSSFCell cellTotalWeight = rowTotalWeight.createCell(0);
				cellTotalWeight.setCellValue("总重量:");
				HSSFCell cellTotalWeight_val = rowTotalWeight.createCell(1);
				cellTotalWeight_val.setCellValue(totalWeight);
				HSSFCell cellTotalWeight_val_str = rowTotalWeight.createCell(2);
				cellTotalWeight_val_str.setCellValue("kg/公斤");

				HSSFCell cellTotalCost = rowTotalWeight_UR.createCell(3);
				cellTotalCost.setCellValue("总费用:");
				HSSFCell cellTotalCost_val = rowTotalWeight_UR.createCell(4);
				cellTotalCost_val.setCellValue(totalCost);
				HSSFCell cellTotalCost_val_str = rowTotalWeight_UR.createCell(5);
				cellTotalCost_val_str.setCellValue("元");

				HSSFCell cellTotalIncome = rowTotalWeight_R.createCell(3);
				cellTotalIncome.setCellValue("总收入:");
				HSSFCell cellTotalIncome_val = rowTotalWeight_R.createCell(4);
				cellTotalIncome_val.setCellValue(totalIncome);
				HSSFCell cellTotalIncome_val_str = rowTotalWeight_R.createCell(5);
				cellTotalIncome_val_str.setCellValue("元");

				HSSFCell cellTotalKitchen = rowTotalWeight_K.createCell(3);
				cellTotalKitchen.setCellValue("总价值:");
				HSSFCell cellTotalKitchen_val = rowTotalWeight_K.createCell(4);
				cellTotalKitchen_val.setCellValue(totalKitchen);
				HSSFCell cellTotalKitchen_val_str = rowTotalWeight_K.createCell(5);
				cellTotalKitchen_val_str.setCellValue("元");

				HSSFCell cellTotalEarnings = rowTotalWeight.createCell(3);
				if (totalCost > totalIncome) {
					totalEarnings = Arith.sub(totalCost, totalIncome);
					cellTotalEarnings.setCellValue("费用支出:");
					System.out.println("费用支出:" + Arith.sub(totalCost, totalIncome) + "\n");
				} else {
					totalEarnings = Arith.sub(totalIncome, totalCost);
					cellTotalEarnings.setCellValue("盈利收入:");
					System.out.println("盈利收入:" + Arith.sub(totalIncome, totalCost) + "\n");
				}
				HSSFCell cellTotalEarnings_val = rowTotalWeight.createCell(4);
				cellTotalEarnings_val.setCellValue(totalEarnings);
				HSSFCell cellTotalEarnings_val_str = rowTotalWeight.createCell(5);
				cellTotalEarnings_val_str.setCellValue("元");

				File xlsFile = new File(
						exportPath + File.separator + "LDExport-" + DateUtil.getCurrentDateTime().toString() + ".xls");
				System.out.println("导出数据名称：" + xlsFile.getName());
				FileOutputStream xlsStream = new FileOutputStream(xlsFile);
				workbook.write(xlsStream);
				workbook.close();
				ld_mark = "1";
			}
		} else {
			ld_mark = "2";
			total_strs = null;
		}
		session.setAttribute("total_strs", total_strs);
		session.setAttribute("ld_mark", ld_mark);

		response.sendRedirect(request.getContextPath() + Redirect_url);
	}

}
