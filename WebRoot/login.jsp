<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>光影在线用户登录</title>
</head>
<style>
.s1 {font-family:微软雅黑;font-weight:bolder;}
.s2 {font-family:宋体;font-size:12;}
.s3 {font-family:宋体;font-size:14;}
a:link {COLOR: #000000;text-decoration : none;}
a:visited {COLOR: #000000;text-decoration : none;}
a:active {TEXT-DECORATION: none}
a:hover {text-decoration : none; position : relative; top : 1px; left : 1px}

.contant { margin: 20px auto 0; width:300px; height:220px; border:solid 1px #09F}
dl { margin:0}
p { font-size:14px}
dd input { margin: 0 0 0 20px}
.sign { margin: 0 0 0 86px}
.return { margin: 0 0 0 50px}
hr { margin:40px 0 8px 0}
dt a { margin: 0 0 50px 0;padding:0 0 0 50px; text-decoration:none; color:#000}
dt a:hover { text-decoration:underline}
</style>

<script type="text/javascript">
function validate_form() {

	if (document.login.username.value == ""){
		alert("帐号不能为空！");
		return false;
	}
	if (document.login.password.value == ""){
		alert("密码不能为空！");
		return false;
	}
	return true;
}
function reg() {
	if (validate_form() == false) {
		return;
	}
	this.login.submit();
}
</script>

<body background="image/bg.gif">

<h2 align="center" class="s1">光影在线用户登录</h2>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

<form name="login" action="controlservlet" method="post">
<table align="center" border='0'>
	<tr>
		<td class="s3">帐号:</td>
		<td colspan='2'><input type="text" name="username"></td>
	</tr>
	<tr>
		<td class="s3">密码:</td>
		<td colspan='2'><input type="password" name="password"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan='2'>
			<input type="button" value="登录" onClick="reg()">
			<input type="button" value="返回" onClick="document.location='index.jsp'">
		</td>
	</tr>
	<tr>
		<td><input type="hidden" name="action" value="login"></td>
	</tr>
</table>
<div align="center">
	<p>---------------------------------------------------</p>
	<a href="register.jsp">
		<font class='s2'>没有帐号？</font>
	</a>
	<a href="findPasswd.jsp">
		<font class='s2'>忘记密码？</font>
	</a>
</div>
</form>

</body>
</html>