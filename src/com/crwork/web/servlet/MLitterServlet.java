package com.crwork.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crwork.web.dao.LitterDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class MExportLitterServlet
 */
@WebServlet("/MExportLitterServlet")
public class MLitterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "MLitterServlet";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MLitterServlet() {
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
		LitterDao mLitterDao = new LitterDao();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
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

			if (mLitterModelList.size() == 0) {
				System.out.print("Litter data size:" + mLitterModelList.size() + "\n");
			}
			for (int i = 0; i < mLitterModelList.size(); i++) {
				System.out.print("userId:" + mLitterModelList.get(i)[0] + " userName:" + mLitterModelList.get(i)[1]
						+ " regionName:" + mLitterModelList.get(i)[2] + " weight:" + mLitterModelList.get(i)[3]
						+ " typeName:" + mLitterModelList.get(i)[4] + " littertypeID:" + mLitterModelList.get(i)[5]
						+ " price:" + mLitterModelList.get(i)[6] + " litterdate:" + mLitterModelList.get(i)[7] + "\n");
			}
			// Search litter data
			if (submit_ld_search != null) {
				System.out.println("function is:" + submit_ld_search + "\n");
			}

			// Export litter data
			if (submitbtn_ld_export != null) {
				System.out.println("function is:" + submitbtn_ld_export + "\n");
				Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				if (mLitterModelList != null && mLitterModelList.size() > 0) {
					result = gs.toJson(mLitterModelList);
				} else {
					result = "fail";
				}

				System.out.println(TAG + result + "\n");

				out.write(result);
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		LitterDao mLitterDao = new LitterDao();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
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

			if (mLitterModelList.size() == 0) {
				System.out.print("Litter data size:" + mLitterModelList.size() + "\n");
			}
			for (int i = 0; i < mLitterModelList.size(); i++) {
				System.out.print("userId:" + mLitterModelList.get(i)[0] + " userName:" + mLitterModelList.get(i)[1]
						+ " regionName:" + mLitterModelList.get(i)[2] + " weight:" + mLitterModelList.get(i)[3]
						+ " typeName:" + mLitterModelList.get(i)[4] + " littertypeID:" + mLitterModelList.get(i)[5]
						+ " price:" + mLitterModelList.get(i)[6] + " litterdate:" + mLitterModelList.get(i)[7] + "\n");
			}
			// Search litter data
			if (submit_ld_search != null) {
				System.out.println("function is:" + submit_ld_search + "\n");
			}

			// Export litter data
			if (submitbtn_ld_export != null) {
				System.out.println("function is:" + submitbtn_ld_export + "\n");
				Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				if (mLitterModelList != null && mLitterModelList.size() > 0) {
					result = gs.toJson(mLitterModelList);
				} else {
					result = "fail";
				}
				out.write(result);
				out.flush();
				out.close();
			}
		}
	}

}
