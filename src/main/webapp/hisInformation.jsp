<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% //用户主页  %>
<html>
<head>
<style type="text/css">
a{
  text-decoration:none;
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
.resHead{
      margin-left:5px;
      margin-top:5px;
      color:red;
      font-size:15px;
      font-weight:900;
}
.onhead{
      width:900px;
      height:200px;
      margin-left:220px;
      margin-bottom:10px;
      border:2px solid 	#BB3D00; 
      background-image:url("images/bj7.jpg");
      background-size:cover;
      font-size:25px;
      text-shadow:0 0 2px;
}
.navigation{
      width:900px;
      height:30px;
      margin-left:220px;
      margin-bottom:10px;
      border:2px solid 	#BB3D00; 
      background-image:url("images/bj7.jpg");
      background-size:cover;
      padding-top:10px;
}
img.head{
      margin-top:20px;
      border-radius:80px;
      margin-left:30px;
}
a.nav{
      font-size:20px;
      color:#930000;
      margin-left:50px;
}
button.isFollow{
      background-color:#8E8E8E;
      border:2px solid #5B5B5B;
      color:#fff;
      font-size:15px;
      width:80px;
      height:30px;
      margin-bottom:30px;
      margin-top:15px;
      border-radius:4px;
      outline:none;
}
button.isnotFollow{
      width:80px;
      height:30px;
      margin-bottom:30px;
      margin-top:15px;
      border-radius:4px;
      outline:none;
      background-color:#FF8040;
      border:2px solid #5B5B5B;
      color:#fff;
      font-size:15px;
}
body {
      background-image:url("images/login.jpg");
      background-attachment:fixed;
      background-size:cover;
}
.arrow{
      float:right;
      margin-right:0px;
}
.littlebox{
      float:right;
      margin-top:10px;
      margin-right:120px;
      font-size:15px;
}
.extra{
      color:#FFF0AC;
      border:4px solid 	#FFF0AC;
}
.boader{
      width:880px;
      height:200px;
      margin-left:230px;
      background-color:#F0F0F0;
      border:2px solid 	#BB3D00;
      border-radius:8px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微儿博</title>
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
     
      <div class="littlebox" id="littlebox" style="display:none">
          <a class="extra" href='othersHome.jsp'>他的主页</a><br><br>
          <a class="extra" href='hisInformation.jsp'>查看个人信息</a><br><br>
          <a class="extra" href='chat?myuserId=${sessionScope.userId}&hisuserId=${ sessionScope.hisVip.userId }&hisusername=${ sessionScope.hisVip.username }'>私聊</a>
      </div>
     
      <div class="onhead" >
            <span class="arrow" onclick="clickOther(this)">▶</span>
	        <center>
	            <img class="head" src="./headimages/${ sessionScope.hisVip.userId }.jpg" height="100" width="100" ><br>
	            ${ sessionScope.hisVip.username }
	            <form action='followPeople?followN=2' method="post">
		            <c:choose>
		                 <c:when test="${sessionScope.isFollow }">
		                      <button class="isFollow" type='submit' 
		                      onmouseover="mOver(this)" onmouseout="mOut(this)">√已关注</button>
		                 </c:when>
		                 <c:otherwise>
		                      <button class="isnotFollow" type='submit'>+关注</button>
		                 </c:otherwise>
		            </c:choose>   
	            </form>
	        </center>    
      </div>
              <div class="boader">
	          <h3 style='text-align:center'>基本信息</h3>
	          <div class="nav1" >
		      <center>  
		             <form action='changeInformation' onsubmit="return check()">
		                  <table>
				             <tr>                                       
	                              <td>真实姓名:</td>
	                              <td>${sessionScope.hisVip.realname }</td>
				             </tr>                                       
				             <tr>                                       
				                  <td>所 在 地:</td>
				                  <td>${sessionScope.hisVip.address }</td>
				             </tr>                                       
				             <tr>                                        
				                  <td>性 别: </td>
				                  <td>${sessionScope.hisVip.sex }</td>
				             </tr>                                        
				             <tr>                                        
				                 <td>生 日:</td>
				                 <td>${sessionScope.hisVip.birthday }</td>
				             </tr>    
				             <tr>    
				                 <td>Q Q:</td>
				                 <td>${sessionScope.hisVip.qq }</td>
				             </tr>    
		                 </table>
		            </form> 
		      </center>
	          </div>
              </div>
</body>
<script type="text/javascript">
function mOver(obj){
    obj.innerHTML="取消关注"
}

function mOut(obj){
    obj.innerHTML="√已关注"
}

function clickOther(obj){
	if(document.getElementById("littlebox").style.display=="none")
	     document.getElementById("littlebox").style.display="";
	else document.getElementById("littlebox").style.display="none";
}

function unread(i){      //AJAX检测有无未读信息
	xmlHttp=new XMLHttpRequest();
	xmlHttp.onreadystatechange = function(){
		 if (xmlHttp.readyState==4 && xmlHttp.status==200){
		     document.getElementById("unread").innerHTML=xmlHttp.responseText;
		     unread(i+1) ;
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
</html>