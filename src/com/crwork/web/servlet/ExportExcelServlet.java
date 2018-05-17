package com.crwork.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.crwork.web.dao.LitterDao;
import com.crwork.web.util.DataFormatUtil;
import com.crwork.web.util.DateUtil;

/**
 * Servlet implementation class ExportExcelServlet
 */
@WebServlet("/ExportExcelServlet")
public class ExportExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LD_EXPORT_DIRECTORY = "LDExcel";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExportExcelServlet() {
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

		// init dir for export LD excel file
		String exportPath = getServletContext().getRealPath("/") + File.separator + LD_EXPORT_DIRECTORY;
		File exportDir = new File(exportPath);
		if (!exportDir.exists()) {
			exportDir.mkdir();
		}

		// init data for excel
		LitterDao mLitterDao = new LitterDao();
		ArrayList<String[]> mlitterList = mLitterDao.exportLitterData();

		// init hwb
		String[] ldTitle = { "编号", "姓名", "区域", "重量(kg)", "类型", "类型标号", "费用-/收入+(元)", "日期" };
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		HSSFRow rowtitle = sheet.createRow(0);
		HSSFCell celltitle = rowtitle.createCell(0);
		celltitle.setCellValue("垃圾数据统计表");
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
		for (int row = 2; row < mlitterList.size() + 2; row++) {
			HSSFRow rows = sheet.createRow(row);
			if (mlitterList.get(row - 2)[5].equals("0")) {
				totalCost += Double.parseDouble(mlitterList.get(row - 2)[6]);
				totalWeight_UR += Double.parseDouble(mlitterList.get(row - 2)[3]);
			} else {
				totalIncome += Double.parseDouble(mlitterList.get(row - 2)[6]);
				totalWeight_R += Double.parseDouble(mlitterList.get(row - 2)[3]);
			}
			totalWeight += Double.parseDouble(mlitterList.get(row - 2)[3]);
			for (int col = 0; col < mlitterList.get(row - 2).length; col++) {
				// add data
				rows.createCell(col).setCellValue(mlitterList.get(row - 2)[col]);
			}
		}
		System.out.println("总重量:" + DataFormatUtil.getTwoDecimal(totalWeight) + "\n");
		System.out.println("废弃物重量:" + DataFormatUtil.getTwoDecimal(totalWeight_UR) + "\n");
		System.out.println("可回收重量:" + DataFormatUtil.getTwoDecimal(totalWeight_R) + "\n");
		System.out.println("总费用:" + DataFormatUtil.getTwoDecimal(totalCost) + "\n");
		System.out.println("总收入:" + DataFormatUtil.getTwoDecimal(totalIncome) + "\n");
		if (totalCost > totalIncome) {
			System.out.println("费用支出:" + DataFormatUtil.getTwoDecimal(totalCost - totalIncome) + "\n");
		} else {
			System.out.println("盈利收入:" + DataFormatUtil.getTwoDecimal(totalIncome - totalCost) + "\n");
		}
		File xlsFile = new File(
				exportPath + File.separator + "LDExport-" + DateUtil.getCurrentDate().toString() + ".xls");
		FileOutputStream xlsStream = new FileOutputStream(xlsFile);
		workbook.write(xlsStream);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
