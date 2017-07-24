<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% //登录页面   %>
<html>
<head>
<style type="text/css"> 
h1.head{
   color:white;
   text-shadow:0 0 8px;
   margin-top:60px;
}
.head2{
   font-size:20px;
   color:white;
   text-shadow:0 0 5px;
   margin-bottom:10px;
}
.a{
   margin-top:60px;
}
.checkcode{
      width:100px;
      margin-left:100px;
}
input::-webkit-input-placeholder {
      color: #fff;
      font-size: 15px;
}
input.in{
      margin-top:10px;
}
input{
      width:220px;
      height:50px;
      margin-top:0px;
      margin-bottom:20px;
      padding-left:15px;
      border-radius:40px;
      outline:none;
      border:2px solid 	#5B5B5B;
      background-color:transparent;
      color:#fff;
      font-size:15px;
}
.admin{
      float:left;
      height:20px;
      margin-top:-600px;
      margin-left:1120px;
}
.adminname{
      float:right;
      color:#fff;
      font-size:18px;
      margin-top:-584px;
      margin-right:17px;
}
.rememberbox{
      height:20px;
      margin-left:-100px;
}
.remember{
      color:#fff;
      font-size:15px;
}
.rememberbox2{
      float:left;
      height:20px;
      margin-left:522px;
      margin-top:-23px;
}
.remember2{
      float:left;
      color:#fff;
      font-size:15px;
      margin-left:560px;
      margin-top:-23px;
}
button{
      width:240px;
      height:50px;
      margin-left:3px;
      margin-bottom:10px;
      border-radius:40px;
      outline:none;
      background-color:#FF8040;
      border:2px solid 	#5B5B5B;
      color:#fff;
      font-size:15px;
}
a.b{
      font-size:15px;
      color:#fff;
      margin-left:-30px;
      text-decoration:none;
}
a.c{
      font-size:15px;
      color:#fff;
      text-decoration:none;
}
a.a_code{
      font-size:15px;
      color:#fff;
      text-decoration:none;
}
body{
     background-image:url("images/login.jpg");
     background-size:cover;
     background-attachment:fixed;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微儿博登录</title>
</head>
<body onload="getRememberCookie()">
            <center>
                <h1 class="head" >MicroBlog</h1>
                <form action='login' method="post" onSubmit="return check()">
                    <div class="a">                                       
	                    <div id="abc" class="head2" >用户登录</div>
	                    <font size="3" color="red" >验证码输入不正确，请您重新输入</font><br>
	                    <input class="in" type='text' name='accountnumber' id="accountnumber"  placeholder="    邮箱地址或手机号码" /><br>
                    </div>
                    <input type='password' name='password' id="password"  placeholder="    密码"/><br/>
				    <input class="checkcode" type="text" name="checkcode"  id="checkcode" placeholder="不区分大小写"/>  
				    <img class="img_code" src="codeImageServlet" id="code" /> 
				    <a class="a_code" href="javascript:changeCode()">看不清？换一张</a><br>
                    <button type='submit'>登录</button><br>
	                
	                <span class="remember">记住我</span>
	                   <input class="rememberbox" id="rememberbox" type="checkbox" name="rememberUser" value="rememberUser">
	                
	                <a class="b" href="forgetPassword_Login.jsp">忘记密码</a><br><br>
	               
	                <span class="remember2">记住密码</span>
	                   <input class="rememberbox2" id="rememberbox2" type="checkbox" name="rememberPass" value="rememberPass"><br>
	                
	                <a class="c" href="register.jsp">还没注册账号！</a>
	                
	                <span class="adminname">管理员登录</span>
	                   <input class="admin" type="checkbox" name="admin" value="admin" onclick="admins()">
                </form>
            </center>
            
<script>
function getRememberCookie(){
	xmlHttp=new XMLHttpRequest();
	xmlHttp.onreadystatechange = function(){
		 if (xmlHttp.readyState==4 && xmlHttp.status==200){
			 var text = xmlHttp.responseText;
			 var num = text.indexOf("&");
		     var username = text.substring(0,num);
		     var password = text.substring(num+1);
		     if(username!="" && password==""){
		    	 document.getElementById("rememberbox").checked=true;
		     }
		     if(username!="" && password!=""){
		    	 document.getElementById("rememberbox2").checked=true;
		     }
			 document.getElementById("accountnumber").value= username;
			 document.getElementById("password").value= password;
		 }
	}
	xmlHttp.open("GET","login?time="+Math.random(),true);   
	xmlHttp.send(null);
}
function admins(){
	if(document.getElementById("abc").innerHTML=="用户登录"){
		document.getElementById("abc").innerHTML="管理员登录";
		document.getElementById("accountnumber").placeholder="    管理员账号";
	}
	else{
		document.getElementById("abc").innerHTML="用户登录";
		document.getElementById("accountnumber").placeholder="    邮箱地址或手机号码";
	}
}

function check(){  
    var accountnumbe = false;
    var passwor = false;
    var checkcod = false;
    
    if(checkAccountnumber(document.getElementById("accountnumber").value)){ accountnumbe = true; }  
    if(checkPassword(document.getElementById("password").value)){ passwor = true; }
    if(checkCheckcode(document.getElementById("checkcode").value)){ checkcod = true; }
    if(accountnumbe && passwor && checkcod){ 
      return true;
    }
    	return false; 
  }
  
function checkAccountnumber(accountnumber){ 
	   if(accountnumber != ""){
	     return true;
	   }else{
		   alert("账号不能为空！"); 
		   return false;
	   }
	 }
function checkPassword(password){ 
	   if(password != ""){
	     return true;
	   }else{
		   alert("密码不能为空！"); 
		   return false;
	   }
	 }
function checkCheckcode(checkcode){ 
	   if(checkcode != ""){
	     return true;
	   }else{
		   alert("验证码不能为空！"); 
		   return false;
	 }
}
function changeCode(){
	document.getElementById("code").src = "codeImageServlet?a="+new Date();
}
</script>
</body>
</html>

