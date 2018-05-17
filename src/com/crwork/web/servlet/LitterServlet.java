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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.crwork.web.dao.LitterDao;
import com.crwork.web.util.DataFormatUtil;
import com.crwork.web.util.DateUtil;

/**
 * Servlet implementation class LitterServlet
 */
@WebServlet(description = "LitterServlet", urlPatterns = { "/LitterServlet" })
public class LitterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LD_EXPORT_DIRECTORY = "LDExcel";
	private String userName = "";

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
		userName = request.getParameter("ld_username");
		String ld_start_date_str = request.getParameter("ld_start_date");
		String ld_end_date_str = request.getParameter("ld_end_date");
		String submit_ld_search = request.getParameter("ld_search");
		String submitbtn_ld_export = request.getParameter("ld_export");
		ArrayList<String[]> mLitterModelList = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date ld_start_date = null;
		Date ld_end_date = null;
		String ld_mark = "";
		String Redirect_url = "/crwork_bsm_forms/table.jsp";
		if (!ld_start_date_str.equals("") && !ld_end_date_str.equals("") && !userName.equals("")) {
			try {
				ld_start_date = sdf.parse(ld_start_date_str);
				ld_end_date = sdf.parse(ld_end_date_str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Fetch litter data from:" + ld_start_date_str + " to " + ld_end_date_str + "start!!!");
			mLitterModelList = mLitterDao.exportLitterData(userName, new java.sql.Date(ld_start_date.getTime()),
					new java.sql.Date(ld_end_date.getTime()));
			System.out.println("Fetch litter data end!!!");

			if (mLitterModelList.size() == 0) {
				System.out.print("Litter data size:" + mLitterModelList.size());
			}
			for (int i = 0; i < mLitterModelList.size(); i++) {
				System.out.print("userId:" + mLitterModelList.get(i)[0] + " userName:" + mLitterModelList.get(i)[1]
						+ " regionName:" + mLitterModelList.get(i)[2] + " weight:" + mLitterModelList.get(i)[3]
						+ " typeName:" + mLitterModelList.get(i)[4] + " littertypeID:" + mLitterModelList.get(i)[5]
						+ " price:" + mLitterModelList.get(i)[6] + " litterdate:" + mLitterModelList.get(i)[7] + "\n");
			}
			// Search litter data
			if (submit_ld_search != null) {
				System.out.println("function is:" + submit_ld_search);
				ld_mark = "0";
			}

			// Export litter data
			if (submitbtn_ld_export != null) {
				System.out.println("function is:" + submitbtn_ld_export);
				String exportPath = getServletContext().getRealPath("/") + File.separator + LD_EXPORT_DIRECTORY;
				File exportDir = new File(exportPath);
				if (!exportDir.exists()) {
					exportDir.mkdir();
				}

				// init hwb
				String[] ldTitle = { "���", "����", "����", "����(kg)", "����", "���ͱ��", "����-/����+(Ԫ)", "����" };
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("sheet1");
				HSSFRow rowtitle = sheet.createRow(0);
				HSSFCell celltitle = rowtitle.createCell(0);
				celltitle.setCellValue("��������ͳ�Ʊ�");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
				HSSFRow rowColume = sheet.createRow(1);
				for (int i = 0; i < ldTitle.length; i++) {
					rowColume.createCell(i).setCellValue(ldTitle[i]);
				}
				Double totalWeight = 0.00;
				Double totalWeight_R = 0.00;
				Double totalWeight_UR = 0.00;
				Double totalCost = 0.00;
				Double totalIncome = 0.00;
				Double totalEarnings = 0.00;
				for (int row = 2; row < mLitterModelList.size() + 2; row++) {
					HSSFRow rows = sheet.createRow(row);
					if (mLitterModelList.get(row - 2)[5].equals("0")) {
						totalCost += Double.parseDouble(mLitterModelList.get(row - 2)[6]);
						totalWeight_UR += Double.parseDouble(mLitterModelList.get(row - 2)[3]);
					} else {
						totalIncome += Double.parseDouble(mLitterModelList.get(row - 2)[6]);
						totalWeight_R += Double.parseDouble(mLitterModelList.get(row - 2)[3]);
					}
					totalWeight += Double.parseDouble(mLitterModelList.get(row - 2)[3]);
					for (int col = 0; col < mLitterModelList.get(row - 2).length; col++) {
						// add data
						rows.createCell(col).setCellValue(mLitterModelList.get(row - 2)[col]);
					}
				}

				System.out.println("������:" + DataFormatUtil.getTwoDecimal(totalWeight) + "\n");
				System.out.println("����������:" + DataFormatUtil.getTwoDecimal(totalWeight_UR) + "\n");
				System.out.println("�ɻ�������:" + DataFormatUtil.getTwoDecimal(totalWeight_R) + "\n");
				System.out.println("�ܷ���:" + DataFormatUtil.getTwoDecimal(totalCost) + "\n");
				System.out.println("������:" + DataFormatUtil.getTwoDecimal(totalIncome) + "\n");

				HSSFRow rowTotalWeight = sheet.createRow(mLitterModelList.size() + 2);
				HSSFCell cellTotalWeight = rowTotalWeight.createCell(2);
				cellTotalWeight.setCellValue("������:");
				HSSFCell cellTotalWeight_val = rowTotalWeight.createCell(3);
				cellTotalWeight_val.setCellValue(totalWeight);

				HSSFRow rowTotalWeight_UR = sheet.createRow(mLitterModelList.size() + 3);
				HSSFCell cellTotalWeight_UR = rowTotalWeight_UR.createCell(2);
				cellTotalWeight_UR.setCellValue("����������:");
				HSSFCell cellTotalWeight_UR_val = rowTotalWeight_UR.createCell(3);
				cellTotalWeight_UR_val.setCellValue(totalWeight_UR);

				HSSFRow rowTotalWeight_R = sheet.createRow(mLitterModelList.size() + 4);
				HSSFCell cellTotalWeight_R = rowTotalWeight_R.createCell(2);
				cellTotalWeight_R.setCellValue("�ɻ�������:");
				HSSFCell cellTotalWeight_R_val = rowTotalWeight_R.createCell(3);
				cellTotalWeight_R_val.setCellValue(totalWeight_R);

				//HSSFRow rowTotalCost = sheet.createRow(mLitterModelList.size() + 2);
				HSSFCell cellTotalCost = rowTotalWeight.createCell(5);
				cellTotalCost.setCellValue("�ܷ���:");
				HSSFCell cellTotalCost_val = rowTotalWeight.createCell(6);
				cellTotalCost_val.setCellValue(totalCost);

				//HSSFRow rowTotalIncome = sheet.createRow(mLitterModelList.size() + 3);
				HSSFCell cellTotalIncome = rowTotalWeight_UR.createCell(5);
				cellTotalIncome.setCellValue("������:");
				HSSFCell cellTotalIncome_val = rowTotalWeight_UR.createCell(6);
				cellTotalIncome_val.setCellValue(totalIncome);

				//HSSFRow rowTotalEarnings = sheet.createRow(mLitterModelList.size() + 4);
				HSSFCell cellTotalEarnings = rowTotalWeight_R.createCell(5);
				if (totalCost > totalIncome) {
					totalEarnings = DataFormatUtil.getTwoDecimal(totalCost - totalIncome);
					cellTotalEarnings.setCellValue("����֧��:");
					System.out.println("����֧��:" + DataFormatUtil.getTwoDecimal(totalCost - totalIncome) + "\n");
				} else {
					totalEarnings = DataFormatUtil.getTwoDecimal(totalIncome - totalCost);
					cellTotalEarnings.setCellValue("ӯ������:");
					System.out.println("ӯ������:" + DataFormatUtil.getTwoDecimal(totalIncome - totalCost) + "\n");
				}
				HSSFCell cellTotalEarnings_val = rowTotalWeight_R.createCell(6);
				cellTotalEarnings_val.setCellValue(totalEarnings);

				File xlsFile = new File(
						exportPath + File.separator + "LDExport-" + DateUtil.getCurrentDate().toString() + ".xls");
				FileOutputStream xlsStream = new FileOutputStream(xlsFile);
				workbook.write(xlsStream);
				ld_mark = "1";
			}
		} else {
			ld_mark = "2";
		}
		session.setAttribute("mLitterModelList", mLitterModelList);
		session.setAttribute("ld_mark", ld_mark);

		response.sendRedirect(request.getContextPath() + Redirect_url);
	}

}
