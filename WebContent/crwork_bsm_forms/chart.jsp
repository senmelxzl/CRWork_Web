<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="com.crwork.web.model.*"%>
<%@ page language="java" import="com.crwork.web.dao.*"%>
<%@ page language="java" import="com.crwork.web.util.*"%>
<%@ page language="java" import="java.math.BigDecimal"%>
<%@ page language="java" import="java.lang.Double"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>后台管理</title>
<!-- Bootstrap Styles-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- Morris Chart Styles-->
<link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
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
	if (mHttpSession != null) {
		isSuccessed = (String) mHttpSession.getAttribute("isSuccessed");
		mUserModel = (UserModel) mHttpSession.getAttribute("mUserModel");
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
			<!-- /.dropdown-user -->
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
				</ul></li>
			<!-- /.dropdown-user -->
		</ul>
		</nav>
		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">

				<li><a href="index.jsp"><i class="fa fa-dashboard"></i> 总览</a></li>

				<li><a href="#" class="active-menu"><i
						class="fa fa-bar-chart-o"></i> 图表</a></li>

				<li><a href="query.jsp"><i class="fa fa-table"></i> 查询</a></li>

				<li><a href="upload.jsp"><i class="fa fa-fw fa-file"></i>
						上传</a></li>

				<li><a href="user.jsp"><i class="fa fa-edit"></i> 用户管理 </a></li>
			</ul>

		</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">图表</h1>
			</div>
			<div id="page-inner">

				<div class="row">


					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="panel panel-default">
							<div class="panel-heading">Bar Chart</div>
							<div class="panel-body">
								<div id="morris-bar-chart"></div>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="panel panel-default">
							<div class="panel-heading">Area Chart</div>
							<div class="panel-body">
								<div id="morris-area-chart"></div>
							</div>
						</div>
					</div>

				</div>
				<!-- /. ROW  -->
				<div class="row">

					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="panel panel-default">
							<div class="panel-heading">Line Chart</div>
							<div class="panel-body">
								<div id="morris-line-chart"></div>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="panel panel-default">
							<div class="panel-heading">Donut Chart</div>
							<div class="panel-body">
								<div id="morris-donut-chart"></div>
							</div>
						</div>
					</div>

				</div>
				<!-- /. ROW  -->
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
	<!-- Morris Chart Js -->
	<script src="assets/js/morris/raphael-2.1.0.min.js"></script>
	<script src="assets/js/morris/morris.js"></script>
	<!-- Custom Js -->
	<script src="assets/js/custom-scripts.js"></script>


</body>
</html>