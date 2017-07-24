<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% //注销成功页面  %>
<html>
<head>
<style type="text/css">
body {
      background-image:url("images/login.jpg");
      background-size:cover;
      background-attachment:fixed;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注销</title>
</head>
<body>
<body background='D:/Swit Project/MicroBlog/images/timg (1).jpg'>
    <center>
         <h2>用户${requestScope.username}已注销</h2>
                    <form action='login.jsp'>
                          <input type='submit' value='重新登录'>
                    </form>
    </center>
</body>
</html>