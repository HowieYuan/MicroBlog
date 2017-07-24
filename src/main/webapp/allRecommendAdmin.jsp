<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
a{
      text-decoration:none;
}
.loginOut{
      margin-left:1100px;
      margin-top:-16px;
}
.out{
      width:40px;
      height:22px;
      margin-bottom:30px;
      border-radius:5px;
      outline:none;
      background-color:#5B5B5B;
      border:2px solid #5B5B5B;
      color:#fff;
      font-size:5px;
}
.name{
      margin-left:1000px;
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
a.nav{
      font-size:20px;
      color:#930000;
      text-decoration:none;
      margin-left:175px;
}
body {
      background-image:url("images/login.jpg");
      background-attachment:fixed;
      background-size:cover;
}
.weibo{
      width:800px;
      min-height:10px;
      background-color:	#F0F0F0;
      border-radius:8px;
      margin-left:227px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
}
.resHead{
      margin-left:5px;
      margin-top:5px;
      color:red;
      font-size:15px;
      font-weight:900;
}
img.head2{
      border-radius:80px;
}
a.username{
      font-weight:900;
      text-decoration:none;
      color:black;
}
.comment{
      color:blue;
      text-decoration:none;
      font-size:15px;
}
.upvote{
      margin-left:20px;
      font-size:15px;
}
a.delete{
      border:4px solid #5B5B5B;
      border-radius:5px;
      background-color:#5B5B5B;
      color:#fff;
      font-size:15px;
}
a.recommend{
      border:1px solid #5B5B5B;
      background-color:#5B5B5B;
      border-radius:5px;
      color:#fff;
      font-size:15px;
      margin-left:600px;
      text-decoration:none;
      font-weight:900;
}
a.recommended{
      border:1px solid #FF5809;
      background-color:#FF5809;
      border-radius:5px;
      color:#fff;
      font-size:15px;
      margin-left:600px;
      text-decoration:none;
      font-weight:900;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员群信</title>
</head>
<body onload="getServerInfo()">
	   <div id="head">
          MicroBlog管理员页面
          <span class="name">管理员${sessionScope.login}</span>
          <form class="loginOut" action='loginOut'>
	               <button class="out" type='submit'>注销</button>
	      </form>
      </div>
      
      <div class="navigation">
	      <a class="nav" href='adminHome.jsp'>首页</a>
	      <a class="nav" href="userAdmin?action=showRecommend">推荐微博</a>
          <a class="nav" href="freezeUserAdmin.jsp">封号用户</a>
	  </div>
	  
      <c:forEach items="${requestScope.allRecommendAdminList}" var="li" varStatus="status">
	      <div class="weibo">
	          <span class="resHead">🔥推荐</span>
	          <dl>
	              <dt><img class="head2" src="./headimages/${ li.userId }.jpg" height="50" width="50" >
	                 <font size="2" style="word-wrap:break-word">
	                 	<a class="username" href="userAdmin?action=weibo&userId=${ li.userId }&username=${li.username}&weiboNum=${ li.vip.weibo }&followNum=${ li.vip.follow }&fansNum=${li.vip.fans}" target="_blank">${li.username}：</a>
	                 </font>
                        </dt>
	              <dt><font size="4" style="word-wrap:break-word">${li.content}</font><br>
	              <c:if test="${li.pictureExist}" >
                     <img src="./weiboimages/${li.weiboId}.jpg" height="100" width="120" ><br>
                  </c:if><hr>
	              <dt><font size="2">${li.dateLine}</font>
	              <dt>
	                 <font size="2">
	                   <a class="comment" href="userAdmin?action=comment&weiboId=${li.weiboId}&userId=${ li.userId }&username=${ li.username }&weiboNum=${ li.vip.weibo }&followNum=${ li.vip.follow }&fansNum=${li.vip.fans}">评论${li.comment}</a>
	                   <span class="upvote">赞❤${li.upvote }</span>
	                 </font>
	                 <a class="recommended" id="rec${li.weiboId}" href='javascript:void(0)' onclick="recommend('${li.weiboId}')">已荐</a>
	                 <a class="delete" href='userAdmin?action=deleteWeibo_r&weiboId=${li.weiboId}&userId=${ li.userId }&username=${ li.username }&weiboNum=${ li.vip.weibo }&followNum=${ li.vip.follow }&fansNum=${li.vip.fans}'>删除</a>
	          </dl>
	      </div>
	  </c:forEach>

</body>
<script type="text/javascript">
function recommend(weiboId){
	var isrecommended = null;
	var isrecommend = document.getElementById("rec"+weiboId).innerHTML;
	if(isrecommend=="推荐"){
		isrecommended = "false";
	}
	else {
		isrecommended = "true";
	}
	xmlHttp=new XMLHttpRequest();
	xmlHttp.open("GET","userAdmin?action=recommend&weiboId="+weiboId+"&hasRecommended="+isrecommended,"true");
	xmlHttp.onreadystatechange=function(){
    if (xmlHttp.readyState==4 && xmlHttp.status==200){
    	if(xmlHttp.responseText=="推荐"){
    		document.getElementById("rec"+weiboId).style.background = "#5B5B5B";
    	}
    	else{
    		document.getElementById("rec"+weiboId).style.background="#FF5809";
    	}
        document.getElementById("rec"+weiboId).innerHTML=xmlHttp.responseText;
       }
    }
	xmlHttp.send(null);
}
</script>
</html>