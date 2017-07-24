<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
img.head2{
      border-radius:80px;
}
a.username{
      font-weight:900;
      text-decoration:none;
      color:black;
}
.resHead{
      margin-left:5px;
      margin-top:5px;
      color:red;
      font-size:15px;
      font-weight:900;
}
.loginOut{
      margin-left:1120px;
      margin-top:-16px;
}
.name{
      margin-left:1010px;
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
      margin-left:80px;
      margin-top:20px;
      border-radius:80px;
}
img.head2{
      margin-top:20px;
      border-radius:80px;
      margin-top:5px;
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
      margin-left:175px;
}
a.nav2{
      font-size:20px;
      color:black;
      text-decoration:none;
}
a.nav1{
      color:black;
      text-decoration:none;
      font-weight:900;
      font-size:18px;
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
a.delete{
      border:4px solid #5B5B5B;
      border-radius:5px;
      background-color:#5B5B5B;
      color:#fff;
      font-size:15px;
}
a.delete2{
      border:4px solid #5B5B5B;
      border-radius:5px;
      background-color:#5B5B5B;
      color:#fff;
      font-size:15px;
      margin-left:550px;
      text-decoration:none;
}
a.delete3{
      border:2px solid #5B5B5B;
      border-radius:5px;
      background-color:#5B5B5B;
      color:#fff;
      font-size:15px;
      text-decoration:none;
}
.nav3{
      float:left;
      margin-right:30px;
      margin-left:30px;
      margin-top:20px;
}
button{
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
body {
      background-image:url("images/login.jpg");
      background-attachment:fixed;
      background-size:cover;
}
#head{
      height:25px;
      background-color:white;
      text-shadow:0 0 1px;
      margin-bottom:20px;
      padding-left:20px;
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
.user{
      width:900px;
      height:60px;
      background-color:	#FFFFDF;
      margin-left:220px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
}
.User{
      width:900px;
      height:60px;
      margin-left:220px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
}
.follow_fans{
      width:900px;
      height:60px;
      background-color:	#F0F0F0;
      margin-left:220px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
}
.heading{
      width:900px;
      height:30px;
      background-color:	#FFFFDF;
      color:blue;
      margin-left:220px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
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
.upvote{
      margin-left:20px;
      font-size:15px;
}
.comment{
      color:blue;
      text-decoration:none;
      font-size:15px;
}
.commentarea{
      width:700px;
      min-height:10px;
      background-color:	#d0d0d0;
      border-radius:8px;
      margin-left:228px;
      margin-top:-16px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
}
.replyarea{
      width:600px;
      min-height:10px;
      background-color:#ADADAD;
      border-radius:8px;
      margin-left:8px;
      margin-top:6px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
}
a.reply{
      font-size:15px;
      color:blue;
      text-decoration:none;
      width:380px;
      min-height:10px;
      background-color:	#ADADAD;
      border-radius:8px;
}
.boader{
      width:880px;
      height:200px;
      margin-left:230px;
      background-color:#F0F0F0;
      border:2px solid 	#BB3D00;
      border-radius:8px;
}
.nav1{
      font-size:15px;
      font-weight:900;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>
      <div id="head">
          MicroBlog管理员页面
          <span class="name">管理员${sessionScope.login}</span>
          <form class="loginOut" action='loginOut'>
	               <button type='submit'>注销</button>
	      </form>
      </div>
      
      <div class="navigation">
	      <a class="nav" href='adminHome.jsp'>首页</a>
	      <a class="nav" href="userAdmin?action=showRecommend">推荐微博</a>
          <a class="nav" href="freezeUserAdmin.jsp">封号用户</a>
	  </div>
	  
	  <table class="User" border="0" width="500" height="20">
	      <tr class="heading">
              <th>头像</th>
              <th>用户名</th>
              <th>微博</th>
              <th>关注</th>
              <th>粉丝</th>
              <th>个人信息</th>
              <th>私信</th>
          </tr>
	      <tr class="user" align="center"> 
	          <td><img class="head2" src="./headimages/${ requestScope.userId }.jpg" height="40" width="40" ></td>
	          <td><a class="nav1" href="userAdmin?action=weibo&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}">${ requestScope.username }</a> </td> 
	          <td><a class="nav2" href='userAdmin?action=weibo&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}'>微博${ requestScope.weiboNum }</a></td>
	          <td><a class="nav2" href='userAdmin?action=follow&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}'>关注${ requestScope.followNum }</a></td> 
	          <td><a class="nav2" href='userAdmin?action=fans&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}'>粉丝${requestScope.fansNum}</a></td> 
	          <td><a href="userAdmin?action=information&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}">查看个人信息</a></td>
	          <td><a href="userAdmin?action=adminchat&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}">私信他/她</a></td>
	      </tr>
	      
	      <c:if test="${requestScope.follow_fansTopic}">
	          <c:forEach items="${requestScope.follow_fansList}" var="li" varStatus="status">       
				     <tr class="follow_fans" align="center"> 
				          <td><img class="head2" src="./headimages/${ li.userId }.jpg" height="40" width="40" ></td>
			              <td><a class="nav1" href="userAdmin?action=weibo&userId=${ li.userId }&username=${li.username}&weiboNum=${ li.weibo }&followNum=${ li.follow }&fansNum=${li.fans}">${li.username}</a> </td> 
				          <td><a class="nav2" href='userAdmin?action=weibo&userId=${ li.userId }&username=${li.username}&weiboNum=${ li.weibo }&followNum=${ li.follow }&fansNum=${li.fans}'>微博${ li.weibo }</a></td>
				          <td><a class="nav2" href='userAdmin?action=follow&userId=${ li.userId }&username=${li.username}&weiboNum=${ li.weibo }&followNum=${ li.follow }&fansNum=${li.fans}'>关注${ li.follow }</a></td> 
				          <td><a class="nav2" href='userAdmin?action=fans&userId=${ li.userId }&username=${li.username}&weiboNum=${ li.weibo }&followNum=${ li.follow }&fansNum=${li.fans}'>粉丝${li.fans}</a></td> 
				          <td><a href="userAdmin?action=information&userId=${ li.userId }&username=${li.username}&weiboNum=${ li.weibo }&followNum=${ li.follow }&fansNum=${li.fans}">查看个人信息</a></td>
				          <td><a href="userAdmin?action=adminchat&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}">私信他/她</a></td>
				       </tr>
			 </c:forEach>
	      </c:if>
	  </table>
	  
	  <c:choose>
		 <c:when test="${requestScope.weiboTopic}">
			  <c:forEach items="${requestScope.recommendAdminList}" var="li" varStatus="status">
			      <div class="weibo">
			          <span class="resHead">🔥推荐</span>
			          <dl>
			              <dt><font size="4" style="word-wrap:break-word">${li.content}</font><br>
			              <c:if test="${li.pictureExist}" >
		                     <img src="./weiboimages/${li.weiboId}.jpg" height="100" width="120" ><br>
		                  </c:if><hr>
			              <dt><font size="2">${li.dateLine}</font>
			              <dt>
			                 <font size="2">
			                   <a class="comment" href="userAdmin?action=comment&weiboId=${li.weiboId}&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}">评论${li.comment}</a>
			                   <span class="upvote">赞❤${li.upvote }</span>
			                 </font>
			                 <a class="recommended" id="rec${li.weiboId}" href='javascript:void(0)' onclick="recommend('${li.weiboId}')">已荐</a>
			                 <a class="delete" href='userAdmin?action=deleteWeibo&weiboId=${li.weiboId}&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}'>删除</a>
			          </dl>
			      </div>
			  </c:forEach>
			  
			  <c:forEach items="${requestScope.adminList}" var="li" varStatus="status">
			      <div class="weibo">
			          <dl>
			              <dt><font size="4" style="word-wrap:break-word">${li.content}</font><br>
			              <c:if test="${li.pictureExist}" >
		                     <img src="./weiboimages/${li.weiboId}.jpg" height="100" width="120" ><br>
		                  </c:if><hr>
			              <dt><font size="2">${li.dateLine}</font>
			              <dt>
			                 <font size="2">
			                   <a class="comment" href="userAdmin?action=comment&weiboId=${li.weiboId}&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}">评论${li.comment}</a>
			                   <span class="upvote">赞❤${li.upvote }</span>
			                 </font>
			                 <a class="recommend" id="rec${li.weiboId}" href='javascript:void(0)' onclick="recommend('${li.weiboId}')">推荐</a>
			                 <a class="delete" href='userAdmin?action=deleteWeibo&weiboId=${li.weiboId}&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}'>删除</a>
			          </dl>
			      </div>
			  </c:forEach>
		</c:when> 
		
		<c:when test="${requestScope.commentTopic}">
	         <c:forEach items="${requestScope.adminList}" var="li" varStatus="status">
			      <div class="weibo">
			          <dl>
			              <dt><font size="4" style="word-wrap:break-word">${li.content}</font><br>
			              <c:if test="${li.pictureExist}" >
		                     <img src="./weiboimages/${li.weiboId}.jpg" height="100" width="120" ><br>
		                  </c:if><hr>
			              <dt><font size="2">${li.dateLine}</font>
			              <dt>
			                 <font size="2">
			                   <a class="comment" href="userAdmin?action=comment&weiboId=${li.weiboId}&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}">评论${li.comment}</a>
			                   <span class="upvote">赞❤${li.upvote }</span>
			                 </font>
			                 <a class="delete" href='userAdmin?action=deleteWeibo&weiboId=${li.weiboId}&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}'>删除</a>
			          </dl>
			      </div>
			  
		      
		          <div class="commentarea" >
			          <c:forEach items="${requestScope.commentAdminList}" var="list" varStatus="status">
				          
				             <dt><img class="head2" src="./headimages/${ list.userId }.jpg" height="40" width="40" >
					             <font size="2" style="word-wrap:break-word">
					                  <a class="nav1" href="userAdmin?action=weibo&userId=${ list.userId }&username=${list.username}&weiboNum=${ list.weibo }&followNum=${ list.follow }&fansNum=${list.fans}">${list.username}</a>
					             </font><br>
				             </dt>    
			                 <dt>
			                    <br><font size="4" style="word-wrap:break-word">${list.content}</font><br>
			                 </dt>
			                 <dt>
			                    <font size="2">${list.dateLine}</font>
			                 </dt>
			                 
			                 <dt>
			                    <a class="reply" href="javascript:void(0)" onclick="reply('${list.commentId}')" >查看回复${list.replyComment}</a>
			                    <a class="delete2"  href='userAdmin?action=deleteComment&commentId=${list.commentId}&weiboId=${li.weiboId}&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}'><font size="2">删除</font></a>
			                 </dt>
			               
			                 <div class="replyarea" id="replyarae${list.commentId}" style="display:none">
			                    <c:set var='ReplyComment' value="ReplyComment${list.commentId}" scope="page"/>
			                    <c:forEach items="${requestScope[ReplyComment]}" var="list2" varStatus="status">
			                           <dt><img class="head2" src="./headimages/${ list2.userId }.jpg" height="40" width="40" ><font size="2" style="word-wrap:break-word"><a class="username" href="userAdmin?action=weibo&userId=${ list2.userId }&username=${list2.username}&weiboNum=${ list2.weibo }&followNum=${ list2.follow }&fansNum=${list2.fans}">${list2.username}：</a></font><br></dt>    
			                           <dt><br><font size="4" style="word-wrap:break-word">${list2.content}</font><br></dt>
			                           <dt><font size="2">${list2.dateLine}</font></dt>
			                           <c:if test="${list2.canDelete }">
			                	              <a class="delete3" href='userAdmin?action=deleteReply&replyCommentId=${list2.replyCommentId}&commentId=${list.commentId}&weiboId=${li.weiboId}&userId=${ requestScope.userId }&username=${ requestScope.username }&weiboNum=${ requestScope.weiboNum }&followNum=${ requestScope.followNum }&fansNum=${requestScope.fansNum}'><font size="2">删除</font></a>
			                           </c:if>
			                           <hr>
			                    </c:forEach>
			                 </div>
			                 <hr>
	                     
	                  </c:forEach>
                  </div>
             </c:forEach>
		</c:when> 
		
		<c:when test="${requestScope.informationTopic}">
		      <div class="boader">
	          <h3 style='text-align:center'>基本信息</h3>
	          <div class="nav1" >
		      <center>  
		             <form action='changeInformation' onsubmit="return check()">
		                  <table>
				             <tr>                                       
	                              <td>真实姓名:</td>
	                              <td>${requestScope.vip.realname }</td>
				             </tr>                                       
				             <tr>                                       
				                  <td>所 在 地:</td>
				                  <td>${requestScope.vip.address }</td>
				             </tr>                                       
				             <tr>                                        
				                  <td>性 别: </td>
				                  <td>${requestScope.vip.sex }</td>
				             </tr>                                        
				             <tr>                                        
				                 <td>生 日:</td>
				                 <td>${requestScope.vip.birthday }</td>
				             </tr>    
				             <tr>    
				                 <td>Q Q:</td>
				                 <td>${requestScope.vip.qq }</td>
				             </tr>    
		                 </table>
		            </form> 
		      </center>
	          </div>
              </div>
		</c:when>
	</c:choose> 
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

function reply(commentId){
	if(document.getElementById("replyarae"+commentId).style.display=="none"){
		document.getElementById("replyarae"+commentId).style.display="";
	}
	else document.getElementById("replyarae"+commentId).style.display="none";
}
</script>
</html>