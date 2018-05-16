package com.crwork.web.servlet;

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

import com.crwork.web.dao.LitterDao;
import com.crwork.web.model.LitterModel;

/**
 * Servlet implementation class LitterServlet
 */
@WebServlet(description = "LitterServlet", urlPatterns = { "/LitterServlet" })
public class LitterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
						+ " typeName:" + mLitterModelList.get(i)[4] + " price:" + mLitterModelList.get(i)[5]
						+ " litterdate:" + mLitterModelList.get(i)[6] + "\n");
			}
			// Search litter data
			if (submit_ld_search != null) {
				System.out.println("function is:" + submit_ld_search);
				ld_mark = "0";
			}

			// Export litter data
			if (submitbtn_ld_export != null) {
				System.out.println("function is:" + submitbtn_ld_export);
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
