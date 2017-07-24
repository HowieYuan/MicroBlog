<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.unread{
      float:right;
      margin-top:10px;
      font-size:17px;
      color:red;
}
.envelope{
      float:left;
      margin-left:1060px;
      margin-top:-30px;
      text-decoration:none;
      color:black;
      font-size:25px;
}
.search{
      margin-left:750px;
      margin-top:-17px;
}
.loginOut{
      float:right;
      margin-top:-17px;
      width:40px;
      height:22px;
      border-radius:5px;
      outline:none;
      background-color:#5B5B5B;
      border:2px solid #5B5B5B;
      color:#fff;
      font-size:5px;
}
.thename{
      float:right;
      font-weight:900;
}
#head{
      height:25px;
      background-color:white;
      text-shadow:0 0 1px;
      margin-bottom:20px;
      padding-left:20px;
}
.navigation{
      width:900px;
      height:30px;
      margin-left:220px;
      margin-bottom:20px;
      border:2px solid 	#BB3D00; 
      background-image:url("images/bj7.jpg");
      background-size:cover;
      padding-top:10px;
}
a.nav{
      font-size:20px;
      color:#930000;
      text-decoration:none;
      margin-left:50px;
}
h3{
      color:black;
      text-shadow:0 0 8px;
      margin-top:60px;
}
.boader{
      width:900px;
      height:600px;
      margin-left:220px;
      background-color:#F0F0F0;
      border:2px solid 	#BB3D00;
      border-radius:8px;
}
.nav1{
      color:black;
      font-size:15px;
      margin-top:38px;
}
input.special::-webkit-input-placeholder {
      color: #FF5809;
      font-size: 15px;
}
input.special{
      width:200px;
      height:40px;
      margin-top:0px;
      margin-bottom:20px;
      padding-left:15px;
      border-radius:40px;
      outline:none;
      border:2px solid 	#5B5B5B;
      background-color:transparent;
      color:black;
      font-size:15px;
}
button.special{
      width:240px;
      height:50px;
      margin-bottom:10px;
      border-radius:40px;
      outline:none;
      background-color:#FF8040;
      border:2px solid 	#5B5B5B;
      color:#fff;
      font-size:15px;
}
body {
      background-image:url("images/login.jpg");
      background-size:cover;
      background-attachment:fixed;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
</head>
<body onload="unread(1)">
      <div id="head">
          MicroBlog
          <span class="thename">☺${sessionScope.login}</span>
          <form action='loginOut'>
	               <button class="loginOut" type='submit'>注销</button>
	      </form>
	                   
	      <form class="search" action='search' onsubmit="return checksearch()">
              <select name="searchSelect">
                  <option value="weibo" selected="selected">微博</option>
                  <option value="user">用户</option>
              </select>
              <input type='text' name='searchKey' id="searchKey"  placeholder="请输入关键词"/>
              <button type='submit'>搜索</button>
          </form>
                              
          <a class="envelope" href="unreadMes?userId=${sessionScope.userId }">✉
              <span class="unread" id="unread"></span>
          </a>
      </div>
      
      <div class="navigation">
	      <a class="nav" href='homePage.jsp'>首页</a>
	      <a class="nav" href='home.jsp'>个人主页</a>
          <a class="nav" href='changeInformation.jsp'>修改个人信息</a>
          <a class="nav" href='changePassword.jsp'>修改密码</a>
          <a class="nav" href='forgetPassword.jsp'>忘记密码</a>
          <a class="nav" href='myCollections?userId=${sessionScope.userId }'>我的收藏</a>
	  </div>
      
    <div class="boader">
	    <h3 style='text-align:center'>修改密码</h3>      
	    <div class="nav1">
		    <center>
		       <br><br> 
		       <form action='changePassword' onsubmit="return check()"> 
		                                原 密 码:<input class="special" type='password' name='oldpassword' id="oldpassword" size="23" placeholder="为保证安全，请输入原密码"   ><br><br>
		                                新 密 码:<input class="special" type='password' name='newpassword' id="newpassword" size="23" placeholder="请输入您的新密码" ><br><br>      
		                                确认密码:<input class="special" type='password' name='conpassword' id="conpassword" size="23" placeholder="请再次确认您的新密码"   ><br><br>                                                                   
		          <button class="special" type='submit'>确定</button><br>
		          <font size="2">如果原来的密码忘记了，建议您通过<a href='forgetPassword.jsp'>找回密码</a>的方式来修改密码~</font>
		       </form>
		    </center>
	    </div>
    </div>
    
<script>   
function check(){  //表单提交则验证开始
    var old_newpasswor = false;
    var conpasswor = false;
     
    if(checkOldpassword(document.getElementById("oldpassword").value,document.getElementById("newpassword").value)){ old_newpasswor = true; }  
    if(checkConpassword(document.getElementById("newpassword").value,document.getElementById("conpassword").value)){ conpasswor = true; }
    if(old_newpasswor && conpasswor){ 
      return true;
    }
     alert("输入不正确，请重新输入！"); 
     return false; 
  }
function checkOldpassword(oldpassword,newpassword){ 
	var pattern= /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
	if(oldpassword == "" || newpassword == ""){
	     return false;
	   }else if(!pattern.test(oldpassword) || !pattern.test(newpassword)){
		   alert("密码必须是6到20位，由数字和英文组成！"); 
		   return false;
	   }
	   else return true;
}
function checkConpassword(newpassword,conpassword){ 
	   if(conpassword == "" || newpassword == "" ){
	     return false;
	   }else if(newpassword != conpassword){
		   alert("两次输入的密码不一致！");
		   return false;
	   }
	   else
		   return true;
}

function unread(i){      //AJAX检测有无未读信息
	xmlHttp=new XMLHttpRequest();
	xmlHttp.onreadystatechange = function(){
		 if (xmlHttp.readyState==4 && xmlHttp.status==200){
		     document.getElementById("unread").innerHTML=xmlHttp.responseText;
		     unread(i+1); 
		 }
	}
	xmlHttp.open("GET","getUnreadMes?time="+Math.random()+"&i="+i,true);   
	xmlHttp.send(null);
}
//对搜索输入框过滤
function checksearch(){
	var pattern=/[\S]/;
	var searchKey = document.getElementById("searchKey").value;
	if(searchKey=="" || !pattern.test(searchKey)){
		alert("你什么都没写耶...."); 
		return false;
	}
	else return true;
}
</script>
</body>
</html>