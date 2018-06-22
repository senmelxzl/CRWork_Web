package com.crwork.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crwork.web.dao.UserDao;
import com.crwork.web.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class MUserAddServlet
 */
@WebServlet("/MUserAddServlet")
public class MUserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "MUserAddServlet";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MUserAddServlet() {
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
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String regionID = request.getParameter("regionID");
		String userType = request.getParameter("userType");
		String iscr = request.getParameter("iscr");
		UserModel mUserModel = new UserModel();
		mUserModel.setUserId(userId);
		mUserModel.setUserName(userName);
		mUserModel.setRegionID(Integer.parseInt(regionID));
		mUserModel.setUserType(Integer.parseInt(userType));
		mUserModel.setIscr(Integer.parseInt(iscr));

		System.out.println(TAG + "userId=" + userId);
		System.out.println(TAG + "userName=" + userName);
		System.out.println(TAG + "regionID=" + regionID);
		System.out.println(TAG + "userType=" + userType);
		System.out.println(TAG + "iscr=" + iscr);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		if (new UserDao().insertUser(mUserModel)) {
			result = "success";
		} else {
			result = "fail";
		}
		out.write(result);
		out.flush();
		out.close();
		System.out.println(TAG + "result=" + result);
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
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String regionID = request.getParameter("regionID");
		String userType = request.getParameter("userType");
		String iscr = request.getParameter("iscr");
		UserModel mUserModel = new UserModel();
		mUserModel.setUserId(userId);
		mUserModel.setUserName(userName);
		mUserModel.setRegionID(Integer.parseInt(regionID));
		mUserModel.setUserType(Integer.parseInt(userType));
		mUserModel.setIscr(Integer.parseInt(iscr));

		System.out.println(TAG + "userId=" + userId);
		System.out.println(TAG + "userName=" + userName);
		System.out.println(TAG + "regionID=" + regionID);
		System.out.println(TAG + "userType=" + userType);
		System.out.println(TAG + "iscr=" + iscr);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		if (new UserDao().insertUser(mUserModel)) {
			result = "success";
		} else {
			result = "fail";
		}
		out.write(result);
		out.flush();
		out.close();
		System.out.println(TAG + "result=" + result);
	}

}
