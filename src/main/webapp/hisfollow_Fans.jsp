<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% //用户主页  %>
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
}
img.head2{
      margin-top:20px;
      border-radius:80px;
}
a.headimg{
      font-size:13px;
      color:#BB3D00;
      text-decoration:none;
      margin-left:0px;
      text-shadow:0 0 0px;
}
a.delete{
      font-size:13px;
      color:red;
      text-decoration:none;
}
a.nav{
      font-size:20px;
      color:#930000;
      text-decoration:none;
      margin-left:50px;
}
a.nav2{
      font-size:20px;
      color:black;
      text-decoration:none;
}
a.Follow1{
      color:black;
      text-decoration:none;
      font-weight:900;
      font-size:18px;
}
.follow1{
      margin-top:-50px;
      margin-left:80px;
      word-wrap:break-word;
}
.follow2{
      float:left;
      margin-top:-40px;
      margin-left:20px;
      word-wrap:break-word;
}
.follow3{
      margin-top:0px;
      margin-left:220px;
      word-wrap:break-word;
}
.nav3{
      float:left;
      margin-right:30px;
      margin-left:30px;
      margin-top:20px;
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
.section {
      width:360px; 
      height:100px;
      float:left; 
      padding:10px;
      margin-left:222px;
      background-color:	#FFFFDF;
      border-radius:8px;
      font-size:12px;
}
.head3{
      width:500px;
      height:20px;
      background-color:	#FFFFDF;
      border-radius:8px;
      margin-left:610px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
      font-weight:900;
}
.weibo{
      width:500px;
      height:100px;
      background-color:	#FFFFDF;
      border-radius:8px;
      margin-left:610px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
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
      
      <div class="onhead" >
	        <center>
	            <img class="head" src="./headimages/${ sessionScope.hisVip.userId }.jpg" height="100" width="100" ><br>
	            ${ sessionScope.hisVip.username }
	            <form action='followPeople?followN=1' method="post">
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
	     
     <div class="section">
	        <table class="nav3">
		          <tr align="center">
			          <td><a class="nav2" href='othersHome?username=${sessionScope.hisVip.username}'>${ sessionScope.hisVip.weibo }</a></td>
			      </tr>
			      <tr align="center">    
			          <td><a class="nav2" href='othersHome?username=${sessionScope.hisVip.username}'>微博</a></td>
			      </tr>
		    </table>
		    <table class="nav3">
		          <tr align="center">
			          <td><a class="nav2" href='follow_Fans?action=hisfollow&hisuserId=${ sessionScope.hisVip.userId }'>${ sessionScope.hisVip.follow }</a></td>
			      </tr>
			      <tr align="center">    
			          <td><a class="nav2" href='follow_Fans?action=hisfollow&hisuserId=${ sessionScope.hisVip.userId }'>关注</a></td> 
			      </tr>
		    </table>
			<table class="nav3">
		          <tr align="center">
			          <td><a class="nav2" href='follow_Fans?action=hisfans&hisuserId=${ sessionScope.hisVip.userId }'>${ sessionScope.hisVip.fans }</a></td>
			      </tr>
			      <tr align="center">    
			         <td><a class="nav2" href='follow_Fans?action=hisfans&hisuserId=${ sessionScope.hisVip.userId }'>粉丝</a></td> 
			      </tr>
		    </table>
      </div>
      
      <div class="head3">
           <c:choose>
             <c:when test="${sessionScope.isFollowNum }">
                   <center>他的关注</center>
             </c:when>
             <c:otherwise>
                   <center>他的粉丝</center>
             </c:otherwise>
        </c:choose>
      </div>
      
	  <c:forEach items="${sessionScope.follow_fansList}" var="li" varStatus="status">   
	     <div class="weibo">
                 <img class="head2" src="./headimages/${ li.userId }.jpg" height="70" width="70" ><br>
                 
                 <div class="follow1">
                    <a class="Follow1" href="othersHome?username=${li.username}">${li.username}</a>  
                 </div>
         
		         <div class="follow3">        
			            <table class="follow2">
					          <tr align="center">
						          <td><a class="nav2" href='othersHome?username=${li.username}' target="_blank">${ li.weibo }</a></td>
						      </tr>
						      <tr align="center">    
						          <td><a class="nav2" href='othersHome?username=${li.username}' target="_blank">微博</a></td>
						      </tr>
					    </table>
					    <table class="follow2">
					          <tr align="center">
						          <td><a class="nav2" href='follow_Fans?action=hisfollow&hisuserId=${ li.userId }' target="_blank">${ li.follow }</a></td>
						      </tr>
						      <tr align="center">    
						          <td><a class="nav2" href='follow_Fans?action=hisfollow&hisuserId=${ li.userId }' target="_blank">关注</a></td> 
						      </tr>
					    </table>
						<table class="follow2">
					          <tr align="center">
						          <td><a class="nav2" href='follow_Fans?action=hisfans&hisuserId=${ li.userId }' target="_blank">${li.fans}</a></td>
						      </tr>
						      <tr align="center">    
						         <td><a class="nav2" href='follow_Fans?action=hisfans&hisuserId=${ li.userId }' target="_blank">粉丝</a></td> 
						      </tr>
					    </table>
				 </div>
	     </div>
	  </c:forEach>


<script>
function mOver(obj){
obj.innerHTML="取消关注"
}

function mOut(obj){
obj.innerHTML="√已关注"
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
	    
</body>
</html>