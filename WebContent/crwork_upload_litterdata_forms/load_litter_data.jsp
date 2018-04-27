<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="com.crwork.web.model.*"%>
<%@ page language="java" import="com.crwork.web.dao.*"%>
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/form-elements.css">
<link rel="stylesheet" href="assets/css/style.css">
<title>数据上传</title>
</head>
<body>
	<%
		String IsUploaded_message = (String) request.getAttribute("IsUploaded_message");
		String message = (String) request.getAttribute("message");
		ArrayList<LitterModel> mLitterModellist = (ArrayList<LitterModel>) request.getAttribute("mLitterModellist");
	%>

	<center>
		<h1>
			<a href="../index.jsp"><strong>一起分</strong></a>
		</h1>
	</center>

	<center>
		<h3>上传文件数据</h3>
	</center>

	<center>
		<table border="1">
			<tr>
				<th>数据加载结果：</th>
				<th><%=message%></th>
			</tr>
			<tr>
				<th>写入数据库结果：</th>
				<th><%=IsUploaded_message%></th>
			</tr>
			<tr>
				<th>文件数据大小如下</th>
				<th><%=mLitterModellist.size()%></th>
			</tr>
			<tr>
				<th>用户编号</th>
				<th>类型</th>
				<th>重量</th>
				<th>日期</th>
			</tr>
			<%
				for (int i = 0; i < mLitterModellist.size(); i++) {
			%>
			<tr>
				<td><%=mLitterModellist.get(i).getUserID()%></td>
				<td><%=mLitterModellist.get(i).getLittertypeID()%></td>
				<td><%=mLitterModellist.get(i).getWeight()%></td>
				<td><%=mLitterModellist.get(i).getLitterdate()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
</body>
</html>