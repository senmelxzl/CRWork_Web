package com.crwork.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crwork.web.dao.UserDao;
import com.crwork.web.model.UserModel;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String psw = request.getParameter("password");
		UserDao mUserDao = new UserDao();
		UserModel mUserModel = mUserDao.getUserInfor(userId);
		String Redirect_url = "/crwork_login_forms/form-1/index.jsp";
		if (mUserModel != null && mUserModel.getPsw().equals(psw)) {
			session.setAttribute("mUserModel", mUserModel);
			session.setAttribute("isSuccessed", "0");
			Redirect_url = "/crwork_bsm_forms/index.jsp";
		} else {
			session.setAttribute("isSuccessed", "1");
		}
		response.sendRedirect(request.getContextPath() + Redirect_url);
	}

}
