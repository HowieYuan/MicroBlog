<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      margin-bottom:10px;
      border:2px solid 	#BB3D00; 
      background-image:url("images/bj7.jpg");
      background-size:cover;
      padding-top:10px;
}
img.head{
      margin-left:80px;
      margin-top:20px;
      border-radius:80px;
}
a.nav{
      font-size:20px;
      color:#930000;
      text-decoration:none;
      margin-left:50px;
}
body {
      background-image:url("images/login.jpg");
      background-attachment:fixed;
      background-size:cover;
}
.myhead{
      float:left;
      height:40px;
      width:40px;
      margin-left:5px;
      box-shadow:3px 3px 2px hsla(0, 0%, 0%, 0.3);
}
.mymessage{
      float:left;
      max-width:400px;
      word-wrap:break-word;
      min-height:21px;
      box-shadow:3px 3px 2px hsla(0, 0%, 0%, 0.3);
      background-color:#FFFFFF;
      border-radius:4px;
      margin-bottom:10px;
      font-size:18px;
      padding:9px 10px 8px 10px;
}
.chatbox{
      height:400px;
      width:900px;
      margin-left:220px;
      background-color:	#E0E0E0;
      border:2px solid #E0E0E0;
      overflow-y:scroll;
}
.chathead{
      height:35px;
      width:904px;
      margin-left:220px;
      padding-top:15px;
      background-color:#4F4F4F;
      color:white;
}
.myarrow {
	  float:left;
	  width:0px;
	  height:0px;
	  font-size:0;
	  margin-top:19px;
	  border:solid 8px;
	  border-color:transparent #fff transparent transparent;
}
.mydate{
      color:#6C6C6C;
      font-size:14px;
}
.chatname{
      font-size:20px;
      font-weight:900;
      margin-left:420px;
}
.deletehischatms{
      float:left;
      margin-left:10px;
      margin-top:10px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员私信</title>
</head>
<body onload="getMessage()">
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
	      <div class="chathead">
	            <span class="chatname">管理员私信</span>
	      </div>
	      
	      <div class="chatbox" id="chatbox">
				<c:forEach items="${requestScope.adminchatList}" var="li" varStatus="status">
				   ${li}<div class="arrow"></div>
				   <div style="clear:both"></div><br>
				</c:forEach>
	      </div> 
      </div>
</body>
<script type="text/javascript">
function deletechatms(Id){
	if(document.getElementById("deletechatms"+Id).style.display=="none"){
		document.getElementById("deletechatms"+Id).style.display="";
	}
	else document.getElementById("deletechatms"+Id).style.display="none";
}
function getMessage(){
	document.getElementById("chatbox").scrollTop = 9999999999; 
	unread(1);
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
</html>