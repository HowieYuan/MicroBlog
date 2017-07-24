<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
h1.head{
   color:white;
   text-shadow:0 0 8px;
   margin-top:20px;
}
input::-webkit-input-placeholder {
      color: #fff;
      font-size: 15px;
}
input{
      width:233px;
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
a{
      font-size:15px;
      color:#fff;
      text-decoration:none;
}
body {
	  background-image:url("images/login.jpg");
	  background-size:cover;
	  background-attachment:fixed;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码</title>
</head>
<body>     
     
    <center>
       <h1 class="head">找回密码</h1> 
       <font size="3" color="red" >您输入的邮箱或手机号码不正确，请重新输入</font><br>
       <form action='forgetPassword' onsubmit="return check()"> 
		      <input type='text' name='email' id="email" size="23" placeholder="为保证安全，请输入注册邮箱地址"   ><br><br>
		      <input type='text' name='phonenumber' id="phonenumber" size="23" placeholder="为保证安全，请输入注册手机号码"  ><br><br>      
		      <input type='password' name='newpassword' id="newpassword" size="23" placeholder="请输入您的新密码"  ><br><br>      
		      <input type='password' name='conpassword' id="conpassword" size="23" placeholder="请再次确认您的新密码"   ><br><br>                                                                  
              <button type='submit'>确定</button><br>
              <a href="login.jsp">返回上层</a>
       </form>
    </center>
    
    
<script>
function check(){  
    var emai_phonenumbe = false;
    var conpasswor = false;
     
    if(checkEmail_Phonenumber(document.getElementById("email").value,document.getElementById("phonenumber").value)){ emai_phonenumbe = true; }  
    if(checkNewpassword(document.getElementById("newpassword").value,document.getElementById("conpassword").value)){ conpasswor = true; }
    if(emai_phonenumbe && conpasswor){ 
      return true;
    }
     alert("请重新填写吧~"); 
     return false; 
}
  
function checkEmail_Phonenumber(email,phonenumber){ 
	if(email == "" || phonenumber == ""){
		 alert("邮箱地址和手机号码不能为空呀"); 
	     return false;
	   }
	   else return true;
}
function checkNewpassword(newpassword,conpassword){ 
	var pattern= /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
	if(newpassword == "" || conpassword == ""){
		 alert("密码不能为空呀"); 
	     return false;
	   }else if(!pattern.test(newpassword)){
		   alert("密码必须是6到20位，由数字和英文组成哦！"); 
		   return false;
	   }
	   else return true;
}
</script>
</body>
</html>