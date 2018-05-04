<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="com.crwork.web.model.*"%>
<%@ page language="java" import="com.crwork.web.dao.*"%>
<title>后台管理</title>
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
	request.setCharacterEncoding("utf-8");
	String action = request.getParameter("action");
	LitterTypeDao mLitterTypeDao = new LitterTypeDao();
	if (action != null && action.trim().equals("addlittertype")) {
		LitterTypeModel mLitterTypeModel = new LitterTypeModel();
		mLitterTypeModel.setLittertypeID(Integer.parseInt(request.getParameter("littertypeID")));
		mLitterTypeModel.setTypeName(request.getParameter("typeName"));
		mLitterTypeModel.setTypemark(Integer.parseInt(request.getParameter("typemark")));
		mLitterTypeModel.setPrice(Double.parseDouble(request.getParameter("price")));
		mLitterTypeDao.insertLitterTypeData(mLitterTypeModel);
	} else if (action != null && action.trim().equals("addvillage")) {

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
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-messages">
					<li><a href="#">
							<div>
								<strong>John Doe</strong> <span class="pull-right text-muted">
									<em>Today</em>
								</span>
							</div>
							<div>Lorem Ipsum has been the industry's standard dummy
								text ever since the 1500s...</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem Ipsum has been the industry's standard dummy
								text ever since an kwilnw...</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem Ipsum has been the industry's standard dummy
								text ever since the...</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>Read
								All Messages</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-messages --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-tasks">
					<li><a href="#">
							<div>
								<p>
									<strong>Task 1</strong> <span class="pull-right text-muted">60%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="60" aria-valuemin="0"
										aria-valuemax="100" style="width: 60%">
										<span class="sr-only">60% Complete (success)</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<p>
									<strong>Task 2</strong> <span class="pull-right text-muted">28%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-info" role="progressbar"
										aria-valuenow="28" aria-valuemin="0" aria-valuemax="100"
										style="width: 28%">
										<span class="sr-only">28% Complete</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<p>
									<strong>Task 3</strong> <span class="pull-right text-muted">60%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-warning"
										role="progressbar" aria-valuenow="60" aria-valuemin="0"
										aria-valuemax="100" style="width: 60%">
										<span class="sr-only">60% Complete (warning)</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<p>
									<strong>Task 4</strong> <span class="pull-right text-muted">85%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-danger"
										role="progressbar" aria-valuenow="85" aria-valuemin="0"
										aria-valuemax="100" style="width: 85%">
										<span class="sr-only">85% Complete (danger)</span>
									</div>
								</div>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>See
								All Tasks</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-tasks --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-alerts">
					<li><a href="#">
							<div>
								<i class="fa fa-comment fa-fw"></i> New Comment <span
									class="pull-right text-muted small">4 min</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span
									class="pull-right text-muted small">12 min</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-envelope fa-fw"></i> Message Sent <span
									class="pull-right text-muted small">4 min</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-tasks fa-fw"></i> New Task <span
									class="pull-right text-muted small">4 min</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-upload fa-fw"></i> Server Rebooted <span
									class="pull-right text-muted small">4 min</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>See
								All Alerts</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> <!-- /.dropdown-alerts --></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#"><i class="fa fa-user fa-fw"></i> User
							Profile</a></li>
					<li><a href="#"><i class="fa fa-gear fa-fw"></i> 设置</a></li>
					<li class="divider"></li>
					<li><a href="#"><i class="fa fa-sign-out fa-fw"></i> 退出</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		</nav>
		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">

				<li><a href="index.jsp"><i class="fa fa-dashboard"></i> 总览</a>
				</li>
				<li><a href="ui-elements.jsp"><i class="fa fa-desktop"></i>
						UI Elements</a></li>
				<li><a href="chart.jsp"><i class="fa fa-bar-chart-o"></i>
						图表</a></li>
				<li><a href="tab-panel.jsp"><i class="fa fa-qrcode"></i>
						Tabs & Panels</a></li>

				<li><a href="table.jsp"><i class="fa fa-table"></i> 查询</a></li>
				<li><a href="form.jsp" class="active-menu"><i
						class="fa fa-edit"></i> 表单 </a></li>


				<li><a href="#"><i class="fa fa-sitemap"></i> Multi-Level
						Dropdown<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="#">Second Level Link</a></li>
						<li><a href="#">Second Level Link</a></li>
						<li><a href="#">Second Level Link<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level">
								<li><a href="#">Third Level Link</a></li>
								<li><a href="#">Third Level Link</a></li>
								<li><a href="#">Third Level Link</a></li>

							</ul></li>
					</ul></li>
				<li><a href="empty.jsp"><i class="fa fa-fw fa-file"></i> 上传</a>
				</li>

				<li><a href="user.jsp"><i class="fa fa-edit"></i> 用户管理 </a></li>
			</ul>

		</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">
					Forms Page <small>Best form elements.</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">表单</a></li>
					<li class="active">Data</li>
				</ol>

			</div>
			<!-- litter type and region add -->
			<div class="row">
				<div class="col-lg-6">
					<form action="form.jsp" method="get">
						<input type="hidden" name="action" value="addlittertype"
							id="addlittertype" />

						<div class="form-group">
							<center>
								<label>类型增加</label>
							</center>
						</div>
						<div class="form-group">
							<label>编码</label> <input class="form-control" name="typeID"
								id="typeID">
						</div>
						<div class="form-group">
							<label>类名</label> <input class="form-control" name="typeName"
								id="typeName">
						</div>
						<div class="form-group">
							<label>标识</label> <input class="form-control" name="typemark"
								id="typemark">
						</div>
						<div class="form-group">
							<label>价格</label> <input class="form-control" name="price"
								id="price">
						</div>

						<div class="form-group">
							<center>
								<label><a href="javascript:document.forms[0].submit()">提交</a></label>
							</center>
						</div>
					</form>
				</div>
				<div class="col-lg-6">
					<form action="form.jsp" method="get">
						<input type="hidden" name="action" value="addvillage"
							id="addvillage" />

						<div class="form-group">
							<center>
								<label>村庄增加</label>
							</center>
						</div>
						<div class="form-group">
							<label>编码</label> <input class="form-control" name="typeID"
								id="littertypeID">
						</div>
						<div class="form-group">
							<label>类名</label> <input class="form-control" name="typeName"
								id="typeName">
						</div>
						<div class="form-group">
							<label>标识</label> <input class="form-control" name="typemark"
								id="typemark">
						</div>
						<div class="form-group">
							<label>价格</label> <input class="form-control" name="price"
								id="price">
						</div>

						<div class="form-group">
							<center>
								<label><a href="javascript:document.forms[0].submit()">提交</a></label>
							</center>
						</div>
					</form>
				</div>
				<!-- user add-->
				<div class="row">
					<div class="col-lg-6">
						<form action="form.jsp" method="get">
							<input type="hidden" name="action" value="addlittertype"
								id="addlittertype" />

							<div class="form-group">
								<center>
									<label>类型增加</label>
								</center>
							</div>
							<div class="form-group">
								<label>编码</label> <input class="form-control" name="typeID"
									id="typeID">
							</div>
							<div class="form-group">
								<label>类名</label> <input class="form-control" name="typeName"
									id="typeName">
							</div>
							<div class="form-group">
								<label>标识</label> <input class="form-control" name="typemark"
									id="typemark">
							</div>
							<div class="form-group">
								<label>价格</label> <input class="form-control" name="price"
									id="price">
							</div>

							<div class="form-group">
								<center>
									<label><a href="javascript:document.forms[0].submit()">提交</a></label>
								</center>
							</div>
						</form>
					</div>
				</div>
				<jsp:include page="company_bottom.jsp" flush="true" />
			</div>
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