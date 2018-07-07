package com.crwork.web.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UsersExcelUtil {

	public static List<String[]> getExcelData(String filePath) throws IOException {
		checkFile(new File(filePath));
		// ���Workbook����������
		Workbook workbook = getWorkBook(filePath);
		// �������ض��󣬰�ÿ���е�ֵ��Ϊһ�����飬��������Ϊһ�����Ϸ���
		List<String[]> list = new ArrayList<String[]>();
		if (workbook != null) {
			for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
				// ��õ�ǰsheet������
				Sheet sheet = workbook.getSheetAt(sheetNum);
				if (sheet == null || sheetNum == 0 || sheetNum == 1) {
					continue;
				}
				// ��õ�ǰsheet�Ŀ�ʼ��
				int firstRowNum = sheet.getFirstRowNum();
				// ��õ�ǰsheet�Ľ�����
				int lastRowNum = sheet.getLastRowNum()-1;
				// ѭ�����˵�һ�е�������
				for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
					// ��õ�ǰ��
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					// ��õ�ǰ�еĿ�ʼ��
					int firstCellNum = row.getFirstCellNum();
					// ��õ�ǰ�е�����
					int lastCellNum = row.getLastCellNum();
					String[] cells = new String[row.getLastCellNum()];
					// ѭ����ǰ��
					for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
						Cell cell = row.getCell(cellNum);
						cells[cellNum] = getCellValue(cell);
					}
					list.add(cells);
				}
			}
		}
		return list;
	}

	/**
	 * ����ļ�
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void checkFile(File file) throws IOException {
		// �ж��ļ��Ƿ����
		if (null == file) {
			System.out.println("�ļ������ڣ�");
		}
		// ����ļ���
		String fileName = file.getName();
		// �ж��ļ��Ƿ���excel�ļ�
		if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
			System.out.println(fileName + "����excel�ļ�");
		}
	}

	public static Workbook getWorkBook(String filePath) {
		// ����ļ���
		String fileName = new File(filePath).getName();
		// ����Workbook���������󣬱�ʾ����excel
		Workbook workbook = null;
		try {
			// ��ȡexcel�ļ���io��
			InputStream is = new FileInputStream(filePath);
			// �����ļ���׺����ͬ(xls��xlsx)��ò�ͬ��Workbookʵ�������
			if (fileName.endsWith("xls")) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith("xlsx")) {
				// 2007 ��2007����
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return workbook;
	}

	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// �ж����ݵ�����
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // ����
			cellValue = stringDateProcess(cell);
			break;
		case Cell.CELL_TYPE_STRING: // �ַ���
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // ��ʽ
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // ��ֵ
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // ����
			cellValue = "�Ƿ��ַ�";
			break;
		default:
			cellValue = "δ֪����";
			break;
		}
		return cellValue;
	}

	/**
	 * ʱ���ʽ����
	 * 
	 * @return
	 * @author Liu Xin Nan
	 * @data 2017��11��27��
	 */
	public static String stringDateProcess(Cell cell) {
		String result = new String();
		if (HSSFDateUtil.isCellDateFormatted(cell)) {// �������ڸ�ʽ��ʱ���ʽ
			SimpleDateFormat sdf = null;
			if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
				sdf = new SimpleDateFormat("HH:mm");
			} else {// ����
				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			}
			Date date = cell.getDateCellValue();
			result = sdf.format(date);
		} else if (cell.getCellStyle().getDataFormat() == 58) {
			// �����Զ������ڸ�ʽ��m��d��(ͨ���жϵ�Ԫ��ĸ�ʽid�����id��ֵ��58)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			double value = cell.getNumericCellValue();
			Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
			result = sdf.format(date);
		} else {
			double value = cell.getNumericCellValue();
			CellStyle style = cell.getCellStyle();
			DecimalFormat format = new DecimalFormat();
			String temp = style.getDataFormatString();
			// ��Ԫ�����óɳ���
			if (temp.equals("General")) {
				format.applyPattern("#");
			}
			result = format.format(value);
		}

		return result;
	}

}
