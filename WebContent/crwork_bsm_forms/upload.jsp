<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="com.crwork.web.model.*"%>
<%@ page language="java" import="com.crwork.web.dao.*"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>一起分 数据上传</title>
<!-- Bootstrap Styles-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="assets/css/custom-styles.css" rel="stylesheet" />
<!-- Google Fonts-->
<link href='https://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
</head>
<%
	HttpSession mHttpSession = request.getSession();
	String isSuccessed = null;
	UserModel mUserModel = new UserModel();
	ArrayList<LitterModel> mLitterModellist = null;
	String IsUploaded_message = "";
	String message = null;
	if (mHttpSession != null) {
		isSuccessed = (String) mHttpSession.getAttribute("isSuccessed");
		mUserModel = (UserModel) mHttpSession.getAttribute("mUserModel");
		IsUploaded_message = (String) mHttpSession.getAttribute("IsUploaded_message");
		message = (String) mHttpSession.getAttribute("message");
		mLitterModellist = (ArrayList<LitterModel>) mHttpSession.getAttribute("mLitterModellist");
		session.setMaxInactiveInterval(1800);
	}
	UserDao mUserDao = new UserDao();
%>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="../index.jsp"><strong>一起分</strong></a>
		</div>

		<ul class="nav navbar-top-links navbar-right">
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<%
						if (mUserModel != null) {
					%>
					<li><a href="#"><i class="fa fa-user fa-fw"></i> <%=mUserModel.getUserName()%></a></li>
					<%
						} else {
					%>
					<li><a href="../crwork_login_forms/form-1/index.jsp"><i
							class="fa fa-user fa-fw"></i>请登录</a></li>
					<%
						}
					%>
					<li><a href="#"><i class="fa fa-gear fa-fw"></i> 设置</a></li>
					<li class="divider"></li>
					<li><a href="../index.jsp"><i class="fa fa-sign-out fa-fw"></i>
							退出</a></li>
					<!-- /.dropdown -->
				</ul>
		</nav>
		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">

				<li><a href="index.jsp"><i class="fa fa-dashboard"></i> 总览</a></li>

				<li><a href="chart.jsp"><i class="fa fa-bar-chart-o"></i>
						图表</a></li>

				<li><a href="query.jsp"><i class="fa fa-table"></i> 查询</a></li>

				<li><a class="active-menu" href="#"><i
						class="fa fa-fw fa-file"></i> 上传</a></li>

				<li><a href="user.jsp"><i class="fa fa-edit"></i> 用户管理 </a></li>
			</ul>

		</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">上传</h1>
			</div>

			<div id="page-inner">

				<div class="row">
					<div class="col-md-12">

						<!--Start Advanced Tables -->
						<div class="panel panel-default">
							<div class="panel-heading">数据上传</div>
							<div class="panel-body">
								<form method="post"
									action="${pageContext.request.contextPath}/servlet/LoadFileServlet"
									enctype="multipart/form-data">
									<center>
										<table>
											<tr>
												<th><input id="ld_file_path_id" type="file"
													name="ld_file_path"></th>
												<th><input id="ld_upload" type="submit"
													class="btn btn-primary" name="ld_upload" value="上传"></th>
											</tr>
										</table>
									</center>
								</form>
								<div class="table-responsive">
									<%
										if (mLitterModellist != null && mLitterModellist.size() != 0) {
									%>
									<div align="right">
										<label><%=IsUploaded_message%> 共<%=mLitterModellist.size()%>条数据
										</label>
									</div>
									<table class="table table-striped table-bordered table-hover">
										<tr>
											<td align="center">编号</td>
											<td align="center">姓名</td>
											<td align="center">重量</td>
											<td align="center">类型</td>
											<td align="center">费用-/收入+(元)</td>
											<td align="center">日期</td>
										</tr>
										<%
											for (int i = 0; i < mLitterModellist.size(); i++) {
										%>
										<tr class="even gradeC">
											<td align="center"><%=mLitterModellist.get(i).getUserId()%></td>
											<td align="center"><%=mUserDao.getUserNameByUserId(mLitterModellist.get(i).getUserId())%></td>
											<td align="center"><%=mLitterModellist.get(i).getWeight()%>公斤</td>
											<td align="center"><%=(mLitterModellist.get(i).getLittertypeID() == 0 ? "综合垃圾" : "可回收")%></td>
											<td align="center"><%=(double) Math.round(mLitterModellist.get(i).gettPrice() * 100) / 100%></td>
											<td align="center"><%=mLitterModellist.get(i).getLitterdate().toString()%></td>
										</tr>
										<%
											}
												mUserDao.CloseConnection();
										%>
									</table>
									<%
										}
									%>
								</div>
							</div>
						</div>
						<!--End Advanced Tables -->
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">用户上传</div>
							<div class="panel-body">
								<form method="post"
									action="${pageContext.request.contextPath}/servlet/LoadFileServlet"
									enctype="multipart/form-data">
									<center>
										<table>
											<tr>
												<th><input id="ld_file_path_id" type="file"
													name="ld_file_path"></th>
												<th><input id="ld_upload" type="submit"
													class="btn btn-primary" name="ld_upload" value="上传"></th>
											</tr>
										</table>
									</center>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="footer"><jsp:include page="company_bottom.jsp"
						flush="true" /></div>
			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->
	<!-- JS Scripts-->
	<!-- jQuery Js -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!-- Bootstrap Js -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- Metis Menu Js -->
	<script src="assets/js/jquery.metisMenu.js"></script>
	<!-- Custom Js -->
	<script src="assets/js/custom-scripts.js"></script>
</body>
</html>