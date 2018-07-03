package com.crwork.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
 * Servlet implementation class MUserServlet
 */
@WebServlet("/MUserServlet")
public class MUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "MUserServlet";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MUserServlet() {
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
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		UserDao mUserDao = new UserDao();
		boolean isSuccess = false;
		String result = "";
		String useraction = request.getParameter("useraction");
		if (useraction.equals("user_add") || useraction.equals("user_modify")) {
			String userId = request.getParameter("userId");
			String userName = new String(request.getParameter("userName").getBytes("iso-8859-1"), "utf-8");
			String regionID = request.getParameter("regionID");
			String userType = request.getParameter("userType");
			String iscr = request.getParameter("iscr");
			UserModel mUserModel = new UserModel();
			if (useraction.equals("user_modify")) {
				mUserModel.setID(Integer.parseInt(request.getParameter("ID")));
			}
			if (useraction.equals("user_add")) {
				mUserModel.setPsw("12345678");
			}
			mUserModel.setUserId(userId);
			mUserModel.setUserName(userName);
			mUserModel.setRegionID(Integer.parseInt(regionID));
			mUserModel.setUserType(Integer.parseInt(userType));
			mUserModel.setIscr(Integer.parseInt(iscr));

			System.out.println(TAG + "useraction=" + useraction);
			if (useraction.equals("user_modify")) {
				System.out.println(TAG + "ID=" + request.getParameter("ID"));
			}
			System.out.println(TAG + "userId=" + userId);
			System.out.println(TAG + "userName=" + userName);
			System.out.println(TAG + "regionID=" + regionID);
			System.out.println(TAG + "userType=" + userType);
			System.out.println(TAG + "iscr=" + iscr);
			if (useraction.equals("user_add")) {
				if (!new UserDao().IsUserExist(userId)) {
					isSuccess = mUserDao.insertUser(mUserModel);
				}
			} else if (useraction.equals("user_modify")) {
				isSuccess = mUserDao.updateUser(mUserModel);
			}
			if (isSuccess) {
				result = "success";
			} else {
				result = "fail";
			}
		} else if (useraction.equals("user_list")) {
			Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			ArrayList<String[]> userlist = mUserDao.getUserList();
			if (userlist != null && userlist.size() > 0) {
				result = gs.toJson(userlist);
			} else {
				result = "fail";
			}
		} else if (useraction.equals("user_delete")) {
			if (mUserDao.deleteUser(request.getParameter("userId"))) {
				result = "success";
			} else {
				result = "fail";
			}
		} else if (useraction.equals("userIds_list")) {
			Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			ArrayList<String> userIds_list = mUserDao
					.GetUserIdsByRegionID(Integer.parseInt(request.getParameter("regionID")));
			if (userIds_list != null && userIds_list.size() > 0) {
				result = gs.toJson(userIds_list);
			} else {
				result = "fail";
			}
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
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		UserDao mUserDao = new UserDao();
		boolean isSuccess = false;
		String result = "";
		String useraction = request.getParameter("useraction");
		if (useraction.equals("user_add") || useraction.equals("user_modify")) {
			String userId = request.getParameter("userId");
			String userName = new String(request.getParameter("userName").getBytes("iso-8859-1"), "utf-8");
			String regionID = request.getParameter("regionID");
			String userType = request.getParameter("userType");
			String iscr = request.getParameter("iscr");
			UserModel mUserModel = new UserModel();
			if (useraction.equals("user_modify")) {
				mUserModel.setID(Integer.parseInt(request.getParameter("ID")));
			}
			if (useraction.equals("user_add")) {
				mUserModel.setPsw("12345678");
			}
			mUserModel.setUserId(userId);
			mUserModel.setUserName(userName);
			mUserModel.setRegionID(Integer.parseInt(regionID));
			mUserModel.setUserType(Integer.parseInt(userType));
			mUserModel.setIscr(Integer.parseInt(iscr));

			System.out.println(TAG + "useraction=" + useraction);
			if (useraction.equals("user_modify")) {
				System.out.println(TAG + "ID=" + request.getParameter("ID"));
			}
			System.out.println(TAG + "userId=" + userId);
			System.out.println(TAG + "userName=" + userName);
			System.out.println(TAG + "regionID=" + regionID);
			System.out.println(TAG + "userType=" + userType);
			System.out.println(TAG + "iscr=" + iscr);
			if (useraction.equals("user_add")) {
				if (!new UserDao().IsUserExist(userId)) {
					isSuccess = mUserDao.insertUser(mUserModel);
				}
			} else if (useraction.equals("user_modify")) {
				isSuccess = mUserDao.updateUser(mUserModel);
			}
			if (isSuccess) {
				result = "success";
			} else {
				result = "fail";
			}
		} else if (useraction.equals("user_list")) {
			Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			ArrayList<String[]> userlist = mUserDao.getUserList();
			if (userlist != null && userlist.size() > 0) {
				result = gs.toJson(userlist);
			} else {
				result = "fail";
			}
		} else if (useraction.equals("user_delete")) {
			if (mUserDao.deleteUser(request.getParameter("userId"))) {
				result = "success";
			} else {
				result = "fail";
			}
		} else if (useraction.equals("userIds_list")) {
			Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			ArrayList<String> userIds_list = mUserDao
					.GetUserIdsByRegionID(Integer.parseInt(request.getParameter("regionID")));
			if (userIds_list != null && userIds_list.size() > 0) {
				result = gs.toJson(userIds_list);
			} else {
				result = "fail";
			}
		}
		System.out.println(TAG + " result=" + result);
		out.write(result);
		out.flush();
		out.close();
	}

}
