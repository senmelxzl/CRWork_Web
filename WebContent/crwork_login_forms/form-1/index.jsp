<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="com.crwork.web.model.*"%>
<%@ page language="java" import="com.crwork.web.dao.*"%>
<%@ page language="java" import="com.crwork.web.util.*"%>
<title>用户登录</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/form-elements.css">
<link rel="stylesheet" href="assets/css/style.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">

</head>
<%
	HttpSession mHttpSession = request.getSession();
	String isSuccessed = null;
	if (mHttpSession != null) {
		isSuccessed = (String) mHttpSession.getAttribute("isSuccessed");
		mHttpSession.invalidate();
	}
%>
<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>
									<a href="../../index.jsp"><strong>一起分吧</strong></a>
								</h3>
								<%
									if (isSuccessed == null) {
								%>
								<h6>请输入账号密码登录</h6>
								<%
									}
								%>
								<%
									if (isSuccessed != null && isSuccessed.equals("1")) {
								%>
								<h6>登录失败</h6>
								<%
									}
								%>
							</div>
							<div class="form-top-right">
								<i class="fa fa-key"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form"
								action="${pageContext.request.contextPath}/servlet/UserServlet"
								method="post" class="login-form" onsubmit="return logincheck();">
								<div class="form-group">
									<label class="sr-only" for="form-username">userID</label> <input
										type="text" name="userID" placeholder="用户帐号..."
										class="form-username form-control" id="userID">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">password</label> <input
										type="password" name="password" placeholder="密码..."
										class="form-password form-control" id="password">
								</div>
								<button type="submit" class="btn" onclick="return logincheck()">进入!</button>
							</form>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 social-login">
						<h3>...其他登录方式</h3>
						<div class="social-login-buttons">
							<a class="btn btn-link-1 btn-link-1-facebook" href="#"> <i
								class="fa fa-facebook"></i> Facebook
							</a> <a class="btn btn-link-1 btn-link-1-twitter" href="#"> <i
								class="fa fa-twitter"></i> Twitter
							</a> <a class="btn btn-link-1 btn-link-1-google-plus" href="#"> <i
								class="fa fa-google-plus"></i> WeChat
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<div class="copyrights">
		Collect from <a href="http://www.cssmoban.com/" title="网站模板">网站模板</a>
	</div>


	<!-- Javascript -->
	<script src="assets/js/jquery-1.11.1.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/scripts.js"></script>

	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
	<script type="text/javascript">
		function logincheck() {
			var userName = $("#userID").val();
			var password = $("#password").val();
			if (userName == "" || password == "") {
				alert("用户名或密码不能为空")
				return false;
			}
			return true;
		}
	</script>
</body>

</html>