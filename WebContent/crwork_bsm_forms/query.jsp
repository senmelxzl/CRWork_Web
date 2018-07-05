<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="com.crwork.web.model.*"%>
<%@ page language="java" import="com.crwork.web.dao.*"%>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>一起分 数据查询</title>
<!-- Bootstrap Styles-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- Morris Chart Styles-->

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
	UserModel mUserModel = new UserModel();
	String ld_mark = null;
	Double[] total_strs = null;
	if (mHttpSession != null) {
		isSuccessed = (String) mHttpSession.getAttribute("isSuccessed");
		mUserModel = (UserModel) mHttpSession.getAttribute("mUserModel");
		ld_mark = (String) mHttpSession.getAttribute("ld_mark");
		total_strs = (Double[]) mHttpSession.getAttribute("total_strs");
		mHttpSession.setMaxInactiveInterval(1800);
	}
%>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
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

				<li><a href="chart.jsp"><i class="fa fa-bar-chart-o"></i>
						图表</a></li>

				<li><a href="#" class="active-menu"><i
						class="fa fa-table"></i> 查询</a></li>

				<li><a href="upload.jsp"><i class="fa fa-fw fa-file"></i>
						上传</a></li>

				<li><a href="user.jsp"><i class="fa fa-edit"></i> 用户管理 </a></li>
			</ul>

		</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">查询</h1>
			</div>

			<div id="page-inner">

				<div class="row">
					<div class="col-md-12">
						<!-- Advanced Tables -->
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4>数据列表</h4>
								<form method="post"
									action="${pageContext.request.contextPath}/servlet/LitterServlet">
									<table>
										<tr>
											<th><h5>用户名：</h5></th>
											<th><input type="text" name="ld_username"
												id="ld_username" /></th>
											<th><h5>区域：</h5></th>
											<th><input type="text" name="ld_region" id="ld_region" /></th>
											<%
												if (ld_mark != null && !ld_mark.equals("")) {

													if (ld_mark.equals("0")) {
											%>

											<th><h5>查询完成</h5>
											<th>
												<%
													} else if (ld_mark.equals("1")) {
												%>
											
											<th><h5>导出成功</h5>
											<th>
												<%
													} else if (ld_mark.equals("2")) {
												%>
											
											<th><h5>请填写查询条件</h5>
											<th>
												<%
													}
													}
												%>
											
										</tr>
										<tr>
											<th><h5>起始日期：</h5></th>
											<th><input type="date" name="ld_start_date"
												id="ld_start_date" /></th>
											<th><h5>结束日期：</h5></th>
											<th><input type="date" name="ld_end_date"
												id="ld_end_date" /></th>
											<th><input type="submit" class="btn btn-primary"
												name="ld_search" id="" value="查询">
											<th>
											<th><input type="submit" class="btn btn-primary"
												name="ld_export" id="" value="导出">
											<th>
										</tr>
									</table>

								</form>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<%
										if (total_strs != null && total_strs.length > 0) {
									%>
									<center>
										<div class="panel-heading">数据汇总</div>
									</center>
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<tr>
											<td align="center"></td>
											<td align="center">(kg/公斤)</td>
											<td align="center"></td>
											<td align="center">(RMB/元)</td>
										</tr>
										<tr>
											<td align="center">综合垃圾：</td>
											<td align="center"><%=total_strs[1]%></td>
											<td align="center">费用：</td>
											<td align="center"><%=total_strs[4]%></td>
										</tr>
										<tr>
											<td align="center">可回收：</td>
											<td align="center"><%=total_strs[2]%></td>
											<td align="center">收入：</td>
											<td align="center"><%=total_strs[5]%></td>
										</tr>
										<tr>
											<td align="center">厨余：</td>
											<td align="center"><%=total_strs[3]%></td>
											<td align="center">价值：</td>
											<td align="center"><%=total_strs[6]%></td>
										</tr>
										<tr>
											<td align="center">总和：</td>
											<td align="center"><%=total_strs[0]%></td>
											<%
												if (total_strs[8] != 1.00) {
											%>
											<td align="center">盈利：</td>
											<%
												} else {
											%>
											<td align="center">支出：</td>
											<%
												}
											%>
											<td align="center"><%=total_strs[7]%></td>
										</tr>
									</table>
									<%
										} else {
									%>

									<center>
										<div class="panel-heading">暂无数据</div>
									</center>
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