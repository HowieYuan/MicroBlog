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
a.username{
      font-weight:900;
      text-decoration:none;
      color:black;
}
body {
      background-image:url("images/login.jpg");
      background-size:cover;
      background-attachment:fixed;
}
.weibo{
      width:600px;
      height:100px;
      background-color:	#FFFFDF;
      border-radius:8px;
      margin-left:220px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
}
.weibo2{
      width:600px;
      min-height:10px;
      background-color:	#FFFFDF;
      border-radius:8px;
      margin-left:220px;
      margin-bottom:10px;
      padding-right:5px;
      padding-left:5px;
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
img.head2{
      margin-top:20px;
      border-radius:80px;
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
a.nav{
      font-size:20px;
      color:#930000;
      text-decoration:none;
      margin-left:50px;
}
a.collection{
      margin-left:330px;
      font-size:14px;
      color:#F75000;
      text-decoration:none;
}
a.collection2{
      margin-left:330px;
      font-size:14px;
      color:#4F4F4F;
      text-decoration:none;
}
a.upvote{
      margin-left:20px;
      font-size:14px;
      color:#4F4F4F;
      text-decoration:none;
}
a.upvote2{
      margin-left:20px;
      font-size:14px;
      color:#F75000;
      text-decoration:none;
}
.commenthead{
      text-decoration:none;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
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
	  
	  
	  <c:choose>
		  <c:when test="${requestScope.weiboResult}">
		      <div class="weibo2">
		          <p><br>“${requestScope.searchKey}”相关搜索结果:<br><br></p>
		      </div>
		      <c:forEach items="${requestScope.searchList}" var="li" varStatus="status">   
				     <div class="weibo2">
				         <dl> 
			                 <dt><img class="head2" src="./headimages/${ li.userId }.jpg" height="50" width="50" >
				                 <font size="2" style="word-wrap:break-word">
				                 	<a class="username" href="othersHome?username=${li.username}" target="_blank">${li.username}：</a>
				                 </font>
			                 </dt>    
			                 <dt><br><font size="4" style="word-wrap:break-word">${li.searchContent}</font><br></dt>
			                 <c:if test="${li.pictureExist}" >
			                     <img src="./weiboimages/${li.weiboId}.jpg" height="100" width="120" ><br>
			                 </c:if><hr>
			                 <dt><font size="2">${li.dateLine}</font><br></dt>
			                 <dt><font size="2">
				                 <a class="commenthead" href="toComment?commentWeiboId=${li.weiboId}" target="_blank">评论${li.comment}</a>
				                 
				                 <c:choose>
					                 <c:when test="${li.hasCollected }">
					                      <a id="num${li.weiboId}" class="collection" href="javascript:void(0)" onclick="collect('${li.weiboId}')">已收藏</a>
					                 </c:when>
					                 <c:otherwise>
					                      <a id="num${li.weiboId}" class="collection2" href="javascript:void(0)" onclick="collect('${li.weiboId}')">收藏它</a>
					                 </c:otherwise>
					             </c:choose>
					            
					             <c:choose>
					                 <c:when test="${li.hasUpvoted }">
					                      <a id="vote${li.weiboId}" class="upvote2" href="javascript:void(0)" onclick="upvote('${li.weiboId}','${li.upvote }')" >已赞❤${li.upvote }</a>
					                 </c:when>
					                 <c:otherwise>
					                      <a id="vote${li.weiboId}" class="upvote" href="javascript:void(0)" onclick="upvote('${li.weiboId}','${li.upvote }')" >赞❤${li.upvote }</a>
					                 </c:otherwise>
					             </c:choose>
			                 </font></dt>
				        </dl>
				     </div>
		      </c:forEach>
		  </c:when>
		  
		  <c:when test="${requestScope.userResult}">
			  <div class="weibo2">
		          <p><br>“${requestScope.searchKey}”相关搜索结果:<br><br></p>
		      </div>
			  <c:forEach items="${requestScope.searchList}" var="li" varStatus="status">   
			     <div class="weibo">
		                 <img class="head2" src="./headimages/${ li.userId }.jpg" height="70" width="70" ><br>
		                 
		                 <div class="follow1">
		                    <a class="Follow1" href="othersHome?username=${li.username}" target="_blank">${li.searchUsername}</a>  
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
		  </c:when>
		  
		  <c:when test="${requestScope.noResult}">
		      <div class="weibo2">
		          <p><br>抱歉，未找到“${requestScope.searchKey}”相关结果。<br><br>
		                                        建议:<br>
                                                                您可以尝试更换关键词，再次搜索。<br><br>
                  </p>
		      </div>
		  </c:when>
	  </c:choose>
</body>
<script type="text/javascript">
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
function upvote(weiboId,upvoteNum){
	var isupvoted = null;
	var isupvote = document.getElementById("vote"+weiboId).innerHTML;
	if(isupvote.indexOf("赞❤") == 0){
		isupvoted = "false";
	}
	else if(isupvote.indexOf("已赞❤") == 0){
		isupvoted = "true";
	}
	xmlHttp=new XMLHttpRequest();
	xmlHttp.open("GET","upvote?weiboId="+weiboId+"&hasUpvoted="+isupvoted,"true");
	xmlHttp.onreadystatechange=function(){
    if (xmlHttp.readyState==4 && xmlHttp.status==200){
    	if(xmlHttp.responseText.indexOf("赞❤") == 0) document.getElementById("vote"+weiboId).style.color = "#4F4F4F";
    	else document.getElementById("vote"+weiboId).style.color = "#F75000";
    	document.getElementById("vote"+weiboId).innerHTML=xmlHttp.responseText;
       }
	}
	xmlHttp.send(null);
}

function collect(weiboId){
	var iscollected = null;
	var iscollect = document.getElementById("num"+weiboId).innerHTML;
	if(iscollect=="收藏它"){
		iscollected = "false";
	}
	else {
		iscollected = "true";
	}
	xmlHttp=new XMLHttpRequest();
	xmlHttp.open("GET","collect?weiboId="+weiboId+"&hasCollected="+iscollected,"true");
	xmlHttp.onreadystatechange=function(){
    if (xmlHttp.readyState==4 && xmlHttp.status==200){
    	if(xmlHttp.responseText=="收藏它") document.getElementById("num"+weiboId).style.color = "#4F4F4F";
    	else document.getElementById("num"+weiboId).style.color = "#F75000";
        document.getElementById("num"+weiboId).innerHTML=xmlHttp.responseText;
       }
    }
	xmlHttp.send(null);
}
</script>
</html>