<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.font{
      margin-left:-40px;
}
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
.head{
      color:black;
      text-shadow:0 0 8px;
      margin-top:60px;
}
.boader{
      width:900px;
      height:800px;
      margin-left:220px;
      background-color:#F0F0F0;
      border:2px solid 	#BB3D00;
      border-radius:8px;
}
.nav1{
      color:black;
      font-size:15px;
      margin-left:80px;
      margin-top:20px;
}
input.radio{
      margin-left:-50px;
}
input.special::-webkit-input-placeholder {
      color:#FF5809;
      font-size: 15px;
}
input.special{
      width:150px;
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
      margin-right:100px;
      border-radius:40px;
      outline:none;
      background-color:#FF8040;
      border:2px solid 	#5B5B5B;
      color:#fff;
      font-size:15px;
}
a.password{
      color:#FF5809;
}
a.bottom{
      font-size:15px;
      color:#FF5809;
      margin-right:100px;
      text-decoration:none;
}
body {
      background-image:url("images/login.jpg");
      background-size:cover;
      background-attachment:fixed;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更改用户基本信息</title>
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
	      <h3 class="head" style='text-align:center'>基本信息</h3>
	      <div class="nav1">
		      <center>  
		             <br><br>
		             <font class="font" size="3" color="red" >您输入的昵称已被他人使用，请重新输入</font><br>
		             <form action='changeInformation' onsubmit="return check()">
		                  <table>
		                         <tr>
					                  <td>邮箱地址:</td>
					                  <td>${ sessionScope.email }&nbsp;<a class="password" href='changePassword.jsp'>修改密码</a></td>
					             </tr>
					             <tr>
					                  <td>手机号码：</td>
					                  <td>${sessionScope.phonenumber }</td>
					             </tr>                                    
				                 <tr>
					                  <td> 昵 称:</td>
					                  <td><input class="special" type='text' name='username' id="username" size="20" placeholder="符号只能包含下划线" value=${sessionScope.vip.username } ></td>
					             </tr>                                       
					             <tr>                                       
		                              <td>真实姓名:</td>
		                              <td><input class="special" type='text' name='realname' id="realname" size="20" placeholder="真实姓名" value=${sessionScope.vip.realname } ></td>
					             </tr>                                       
					             <tr>                                       
					                  <td>所 在 地:</td>
					                  <td><input class="special" type='text' name='address' id="address" size="20" placeholder="所在地" value=${sessionScope.vip.address } ></td>
					             </tr>                                       
					             <tr>                                        
					                  <td>性 别: </td>
					                  <td>男：<input class="special" class="radio" type="radio"  name="sex" value="男" <c:if test="${sessionScope.vip.sex== '男'}">checked="checked"</c:if>/>女：<input class="radio" type="radio" name="sex" value="女" <c:if test="${sessionScope.vip.sex== '女'}">checked="checked"</c:if>/></td>
					             </tr>                                        
					             <tr>                                        
					                 <td>生 日:</td>
					                 <td><input class="special" type='text' name='birthday' id="birthday" size="20" placeholder="生日:2000-01-01格式" value=${sessionScope.vip.birthday } ></td>
					             </tr>    
					             <tr>    
					                 <td>Q Q:</td>
					                 <td><input class="special" type='text' name='qq' id="qq" size="20" placeholder="QQ" value=${sessionScope.vip.qq } ></td>
					             </tr>    
		                 </table>
		                 <button class="special" type='submit'>保存</button><br>
		                 <a class="bottom" href='home.jsp'>返回上层</a>
		            </form> 
		      </center>
	      </div>
      </div>
       


<script>
function check(){  //表单提交则验证开始
    var usernam = false;
    var realnam = false;
    var addres = false;
    var se = false;
    var birthda = false;
    var q = false;
     
    if(checkUsername(document.getElementById("username").value)){ usernam = true; }  
    if(checkRealname(document.getElementById("realname").value)){ realnam = true; }
    if(checkAddress(document.getElementById("address").value)){ addres = true; }
    if(checkBirthday(document.getElementById("birthday").value)){ birthda = true; }
    if(checkQq(document.getElementById("qq").value)){ q = true; }
    if(usernam && realnam && addres && birthda && q){ 
      return true;
    }
     return false; 
  }
  
function checkUsername(username){ 
	var pattern= /^[\u4e00-\u9fff\w]{1,16}$/;
	if(username == "" ){
		 alert("昵称不能为空！");
	     return false;
	   }else if(!pattern.test(username)){
		   alert("请输入正确的昵称"); 
		   return false;
	   }
	   else return true; 
	 }
function checkRealname(realname){ 
	var pattern= /^([\u4e00-\u9fa5]+|([a-z]+\s?)+)$/;
	if(realname == "" ){
	     return true;
	   }else if(!pattern.test(realname)){
		   alert("请输入真实的姓名!"); 
		   return false;
	   }
	   else return true; 
	 }
function checkAddress(address){ 
	var pattern= /^([\u4e00-\u9fa5]|[A-Za-z0-9_])+/;
	if(address == "" ){
	     return true;
	   }else if(!pattern.test(address)){
		   alert("请输入正确的地址！"); 
		   return false;
	   }
	   else return true; 
	 }
function checkBirthday(birthday){ 
	var pattern= /^(19|20)\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/;
	if(birthday == "" ){
	     return true;
	   }else if(!pattern.test(birthday)){
		   alert("该日期不符合格式！"); 
		   return false;
	   }
	   else return true; 
	 }
function checkQq(qq){ 
	var pattern= /^[1-9]([0-9]{4,10})$/;
	if(qq == "" ){
	     return true;
	   }else if(!pattern.test(qq)){
		   alert("请输入正确的QQ号码！"); 
		   return false;
	   }
	   else return true; 
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