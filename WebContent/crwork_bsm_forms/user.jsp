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
<title>一起分 用户管理</title>
<!-- Bootstrap Styles-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="assets/css/custom-styles.css" rel="stylesheet" />
<!-- Google Fonts-->
<link href='https://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<!-- TABLE STYLES-->
<link href="assets/js/dataTables/dataTables.bootstrap.css"
	rel="stylesheet" />
</head>
<%
	HttpSession mHttpSession = request.getSession();
	String isSuccessed = null;
	String[] Users_I = null;
	ArrayList<String[]> mUserModellist = null;
	UserModel mUserModel = new UserModel();
	if (mHttpSession != null) {
		isSuccessed = (String) mHttpSession.getAttribute("isSuccessed");
		mUserModel = (UserModel) mHttpSession.getAttribute("mUserModel");
		Users_I = (String[]) mHttpSession.getAttribute("Users_I");
		mUserModellist = (ArrayList<String[]>) mHttpSession.getAttribute("mUserModellist");
		if (mUserModellist == null || Users_I == null) {
			mUserModellist = new UserDao().getUserList();
			UserDao iUserDao = new UserDao();
			Users_I = iUserDao.getUsers_I();
			mHttpSession.setAttribute("mUserModellist", mUserModellist);
			mHttpSession.setAttribute("Users_I", Users_I);
			iUserDao.CloseConnection();
		}
		mHttpSession.setMaxInactiveInterval(1800);
	}
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

				<li><a href="upload.jsp"><i class="fa fa-fw fa-file"></i>
						上传</a></li>

				<li><a href="#" class="active-menu"><i class="fa fa-edit"></i>
						用户管理 </a></li>
			</ul>

		</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">用户管理</h1>
			</div>

			<div id="page-inner">
				<%
					if (Users_I != null && Users_I.length > 0) {
				%>
				<!-- user summary start -->
				<div class="row">
					<div class="col-xs-6 col-md-3">
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>总用户</h4>
								<h5><%=Users_I[0]%></h5>
								<div class="easypiechart" id="easypiechart-blue"
									data-percent="100">
									<span class="percent">100%</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>未分类</h4>
								<h5><%=Users_I[1]%></h5>
								<div class="easypiechart" id="easypiechart-orange"
									data-percent=<%=Users_I[3]%>>
									<span class="percent"><%=Users_I[3]%>%</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>已分类</h4>
								<h5><%=Users_I[2]%></h5>
								<div class="easypiechart" id="easypiechart-teal"
									data-percent=<%=Users_I[4]%>>
									<span class="percent"><%=Users_I[4]%>%</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- user summary end -->
				<%
					}
				%>
				<div class="row">
					<div class="col-md-12">
						<!-- Advanced Tables -->
						<div class="panel panel-default">
							<div class="panel-heading">用户列表</div>
							<div class="panel-body">
								<div class="table-responsive">
									<%
										if (mUserModellist != null && mUserModellist.size() > 0) {
									%>
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<tr>
												<td align="center">用户编号</td>
												<td align="center">姓名</td>
												<td align="center">区域</td>
												<td align="center">权限</td>
												<td align="center">注册日期</td>
												<td align="center">操作</td>
											</tr>
										</thead>
										<tbody>
											<%
												int userType = 0;
													for (int i = 0; i < mUserModellist.size(); i++) {
											%>
											<tr class="even gradeC">
												<td class="center"><center><%=mUserModellist.get(i)[1]%></center></td>
												<td class="center"><center><%=mUserModellist.get(i)[2]%></center></td>
												<td class="center"><center><%=mUserModellist.get(i)[3]%></center></td>
												<%
													userType = Integer.parseInt(mUserModellist.get(i)[5]);
															if (userType == 2) {
												%>
												<td class="center"><center>普通用户</center></td>
												<%
													} else if (userType == 1) {
												%><td class="center"><center>管理员</center></td>
												<%
													} else {
												%>
												<td class="center"><center>超级管理员</center></td>
												<%
													}
												%>
												<td class="center"><center><%=mUserModellist.get(i)[6]%></center></td>

												<td class="center"><center>
														<label><a
															href="javascript:document.forms[0].submit()">修改</a></label> <label><a
															href="javascript:document.forms[1].submit()">删除 </a></label>
													</center></td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
									<%
										} else {
									%>
									<div class="panel-heading">暂无数据</div>
									<%
										}
									%>
								</div>

							</div>
						</div>
						<!--End Advanced Tables -->
					</div>
				</div>
				<div class="footer"><jsp:include page="company_bottom.jsp"
						flush="true" /></div>
			</div>
		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
	<!-- /. WRAPPER  -->
	<!-- JS Scripts-->
	<!-- jQuery Js -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!-- Bootstrap Js -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- Metis Menu Js -->
	<script src="assets/js/jquery.metisMenu.js"></script>
	<!-- Morris Chart Js -->
	<script src="assets/js/morris/raphael-2.1.0.min.js"></script>
	<script src="assets/js/morris/morris.js"></script>


	<script src="assets/js/easypiechart.js"></script>
	<script src="assets/js/easypiechart-data.js"></script>

	<script src="assets/js/Lightweight-Chart/jquery.chart.js"></script>
	<!-- DATA TABLE SCRIPTS -->
	<script src="assets/js/dataTables/jquery.dataTables.js"></script>
	<script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
	<script>
		$(document).ready(function() {
			$('#dataTables-example').dataTable();
		});
	</script>
	<!-- Custom Js -->
	<script src="assets/js/custom-scripts.js"></script>
</body>
</html>