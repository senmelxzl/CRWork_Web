<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="com.crwork.web.model.*"%>
<%@ page language="java" import="com.crwork.web.dao.*"%>
<%@ page language="java" import="com.crwork.web.util.*"%>
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet"
	href="crwork_login_forms/form-1/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="crwork_login_forms/form-1/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="crwork_login_forms/form-1/assets/css/form-elements.css">
<link rel="stylesheet"
	href="crwork_login_forms/form-1/assets/css/style.css">
<title>一起分</title>
</head>
<body>
	<div>
		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<a href="#"><strong>一起分吧</strong></a>
						</h1>
						<div class="description">
							<p>垃圾分一分，环境满满分</p>
							<p>共建美好家园</p>
						</div>
						<div>
							<button type="submit" class="btn"
								onclick="location='crwork_login_forms/form-1/index.jsp'">用户登录</button>
						</div>
						<!-- 
						<p></p>
						<div>
							<button type="submit" class="btn"
								onclick="location='crwork_bsm_forms/index.html'">后台管理</button>
						</div>
						<p></p>
						<div>
							<button type="submit" class="btn"
								onclick="location='crwork_upload_litterdata_forms/index.jsp'">数据上传</button>
						</div>
						-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<jsp:include page="crwork_bsm_forms/company_bottom.jsp" flush="true" /></div>
</body>
</html>