<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	<form method="post"
		action="${pageContext.request.contextPath}/servlet/LoadFileServlet"
		enctype="multipart/form-data">
		<center>
			<h3>请选择你要上传的文件</h3>
		</center>
		<table align="center">
			<tr>
				<td>
					<center>
						<input id="ld_file_path_id" type="file" name="ld_file_path">
					</center>
				<td>
				<td>
					<center>
						<input id="ld_upload_btn_id" type="submit" class="btn" value="上传"
							name="upload">
					</center>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>