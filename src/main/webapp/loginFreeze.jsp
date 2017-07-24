<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
body {
      background-image:url("images/login.jpg");
      background-attachment:fixed;
      background-size:cover;
}
.freeze{
      width:300px;
      height:150px;
      background-color:	#FFFFDF;
      border-radius:8px;
      margin-top:200px;
      margin-bottom:10px;
      padding-top:50px;
      padding-right:50px;
      padding-left:50px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>您已被封号</title>
</head>
<body>
    <center>
	    <div class="freeze">
		                 您已被封号！<br><br>
		                 封号时间:${sessionScope.startTime}<br>
		                 截止时间:${sessionScope.endTime}<br><br>
		      <a href="freezeOut">确定</a>
	    </div>
    </center>
</body>
</html>