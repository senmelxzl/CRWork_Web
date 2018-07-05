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
<link rel="stylesheet" href="assets/js/Lightweight-Chart/cssCharts.css">
</head>
<%
	HttpSession mHttpSession = request.getSession();
	String isSuccessed = null;
	String[] litterweight_i = null;
	String[] litterprice_i = null;
	UserModel mUserModel = new UserModel();
	if (mHttpSession != null) {
		isSuccessed = (String) mHttpSession.getAttribute("isSuccessed");
		mUserModel = (UserModel) mHttpSession.getAttribute("mUserModel");
		litterweight_i = (String[]) mHttpSession.getAttribute("litterweight_i");
		litterprice_i = (String[]) mHttpSession.getAttribute("litterprice_i");
		if (litterweight_i == null && litterprice_i == null) {
			LitterDao mLitterDao = new LitterDao();
			litterweight_i = mLitterDao.getLitterWeight_I();
			litterprice_i = mLitterDao.getLitterPrice_I();
			mHttpSession.setAttribute("litterweight_i", litterweight_i);
			mHttpSession.setAttribute("litterprice_i", litterprice_i);
			mLitterDao.CloseConnection();
		}
		mHttpSession.setMaxInactiveInterval(1800);
	}
%>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">

		<div class="navbar-header">
			<a class="navbar-brand" href="../index.jsp"><strong>一起分</strong></a>

			<div id="sideNav" href="">
				<i class="fa fa-caret-right"></i>
			</div>
		</div>

		<ul class="nav navbar-top-links navbar-right">
			<!-- /.dropdown user start-->
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
			<!-- /.dropdown user start -->
		</ul>

		</nav>
		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">

				<li><a class="active-menu" href="#"><i
						class="fa fa-dashboard"></i> 总览</a></li>

				<li><a href="chart.jsp"><i class="fa fa-bar-chart-o"></i>
						图表</a></li>

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
				<h1 class="page-header">
					总览 <small>一起分</small>
				</h1>
			</div>
			<div id="page-inner">

				<!-- /. ROW  -->
				<%
					if (litterweight_i != null && litterweight_i.length > 0) {
				%>
				<div class="row">
					<div class="col-xs-6 col-md-3">
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>总量</h4>
								<h5><%=litterweight_i[3]%></h5>
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
								<h4>综合垃圾</h4>
								<h5><%=litterweight_i[0]%></h5>
								<div class="easypiechart" id="easypiechart-orange"
									data-percent=<%=litterweight_i[4]%>>
									<span class="percent"><%=litterweight_i[4]%>%</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>可回收</h4>
								<h5><%=litterweight_i[1]%></h5>
								<div class="easypiechart" id="easypiechart-teal"
									data-percent=<%=litterweight_i[5]%>>
									<span class="percent"><%=litterweight_i[5]%>%</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>厨余</h4>
								<h5><%=litterweight_i[2]%></h5>
								<div class="easypiechart" id="easypiechart-red"
									data-percent=<%=litterweight_i[6]%>>
									<span class="percent"><%=litterweight_i[6]%>%</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%
					}
				%>
				<%
					if (litterprice_i != null && litterprice_i.length > 0) {
				%>
				<!--/.row-->
				<div class="row">
					<div class="col-xs-6 col-md-3">
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<strong><%=litterprice_i[4]%></strong>
								<h3><%=litterprice_i[3]%></h3>
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<strong>费用</strong>
								<h3><%=litterprice_i[0]%></h3>
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<strong>收入</strong>
								<h3><%=litterprice_i[1]%></h3>
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-md-3">
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<strong>价值</strong>
								<h3><%=litterprice_i[2]%></h3>
							</div>
						</div>
					</div>
				</div>
				<!--/.row-->
				<%
					}
				%>
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
	<script src="assets/js/easypiechart.js"></script>
	<script src="assets/js/easypiechart-data.js"></script>
	<script src="assets/js/Lightweight-Chart/jquery.chart.js"></script>
	<!-- Custom Js -->
	<script src="assets/js/custom-scripts.js"></script>
</body>

</html>