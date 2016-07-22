<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />

</head>
<body>
<div id="header" class="wrap">
	<div id="logo">网上书城</div>
	<div id="navbar">
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="login">
	<h2>用户登陆</h2>
	<form method="post" action="user_login.action">
	<input type = "hidden" name = "flag" value="user_login"/>
		<dl>
			<dt>用户名：</dt>
			<dd><input class="input-text" type="text" name="userName" /></dd>
			<dt>密　码：</dt>
			<dd><input class="input-text" type="password" name="passWord" /></dd>
			<dt></dt>
			<dd class="button"><input class="input-btn" type="submit" name="submit" value="" /><input class="input-reg" type="button" name="register" value="" onclick="window.location='register.jsp';" /></dd>
		</dl>
	</form>
</div>
<div id="footer" class="wrap">
	网上书城 &copy; 版权所有</div>
	${errors.p_message}
</body>
</html>
    