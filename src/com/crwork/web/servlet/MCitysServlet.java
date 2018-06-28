package com.crwork.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crwork.web.dao.CitysDao;
import com.crwork.web.model.CitysModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetCitysServlet
 */
@WebServlet("/MCitysServlet")
public class MCitysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "MCitysServlet";

	private static final String ACTION_GET_PRE_CITY_LIST = "getprecitylist";
	private static final String ACTION_GET_CITY_LIST = "getcitylist";
	private static final String ACTION_GET_CITY = "getcity";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MCitysServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		int parent_id = Integer.parseInt(request.getParameter("parent_id"));
		int id = Integer.parseInt(request.getParameter("id"));
		String citys_action = request.getParameter("citys_action");

		System.out.println(TAG + "parent_id=" + parent_id);
		System.out.println(TAG + "citys_action=" + citys_action);

		CitysDao mCitysDao = new CitysDao();
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		if (citys_action.equals(ACTION_GET_CITY_LIST)) {
			ArrayList<CitysModel> listcitys = mCitysDao.getCitys(parent_id);
			if (listcitys != null && listcitys.size() > 0) {
				result = gs.toJson(listcitys);
			} else {
				result = "fail";
			}
		} else if (citys_action.equals(ACTION_GET_PRE_CITY_LIST)) {
			ArrayList<CitysModel> listcitys = mCitysDao.getPreCitys(parent_id);
			if (listcitys != null && listcitys.size() > 0) {
				result = gs.toJson(listcitys);
			} else {
				result = "fail";
			}
		} else if (citys_action.equals(ACTION_GET_CITY)) {
			CitysModel mCitysModel = mCitysDao.getCity(id);
			if (mCitysModel != null) {
				result = gs.toJson(mCitysModel);
			} else {
				result = "fail";
			}
		}
		System.out.println(TAG + "result=" + result);
		out.write(result);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		int parent_id = Integer.parseInt(request.getParameter("parent_id"));
		int id = Integer.parseInt(request.getParameter("id"));
		String citys_action = request.getParameter("citys_action");

		System.out.println(TAG + "parent_id=" + parent_id);
		System.out.println(TAG + "id=" + id);
		System.out.println(TAG + "citys_action=" + citys_action);

		CitysDao mCitysDao = new CitysDao();
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		if (citys_action.equals(ACTION_GET_CITY_LIST)) {
			ArrayList<CitysModel> listcitys = mCitysDao.getCitys(parent_id);
			if (listcitys != null && listcitys.size() > 0) {
				result = gs.toJson(listcitys);
			} else {
				result = "fail";
			}
		} else if (citys_action.equals(ACTION_GET_PRE_CITY_LIST)) {
			ArrayList<CitysModel> listcitys = mCitysDao.getPreCitys(parent_id);
			if (listcitys != null && listcitys.size() > 0) {
				result = gs.toJson(listcitys);
			} else {
				result = "fail";
			}
		} else if (citys_action.equals(ACTION_GET_CITY)) {
			CitysModel mCitysModel = mCitysDao.getCity(id);
			if (mCitysModel != null) {
				result = gs.toJson(mCitysModel);
			} else {
				result = "fail";
			}
		}
		System.out.println(TAG + " result= " + result);
		out.write(result);
		out.flush();
		out.close();
	}

}
