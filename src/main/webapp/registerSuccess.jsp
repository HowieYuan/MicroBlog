<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% //注册成功页面  %>
<html>
<head>
<style type="text/css">
body {background-image:url("images/login.jpg");background-size:cover;background-attachment:fixed;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
</head>
<body>
   <center>
       <h1>注册成功！欢迎${param.username}</h1><br>
           <a href='login.jsp'>返回登录页面</a>
   </center>
</body>
</html>