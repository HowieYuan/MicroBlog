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
      margin-left:80px;
      margin-top:20px;
      border-radius:80px;
}
img.head2{
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
a.username{
      font-weight:900;
      text-decoration:none;
      color:black;
}
button.b0{
      width:50px;
      height:24px;
      margin-bottom:30px;
      border-radius:40px;
      outline:none;
      background-color:#FF8040;
      border:2px solid #5B5B5B;
      color:#fff;
      font-size:5px;
}
button.b1{
      width:45px;
      height:20px;
      margin-top:5px;
      margin-bottom:5px;
      margin-left:310px;
      border-radius:7px;
      outline:none;
      background-color:#FF8040;
      border:2px solid #5B5B5B;
      color:#fff;
      font-size:5px;
}
body {
      background-image:url("images/login.jpg");
      background-attachment:fixed;
      background-size:cover;
}
#section {
      width:360px; 
      float:left; 
      padding:10px;
      margin-left:222px;
      background-color:	#FFFFDF;
      border-radius:8px;
      font-size:12px;
}
.weibo{
      width:500px;
      min-height:10px;
      background-color:	#FFFFDF;
      border-radius:8px;
      margin-left:610px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
}
.commentarea{
      width:400px;
      min-height:10px;
      background-color:#F0F0F0;
      border-radius:8px;
      margin-left:610px;
      margin-top:-16px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
}
img.upvote{
      margin-left:20px;
      width:20px;
      height:20px;
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
a.reply{
      font-size:13px;
      color:blue;
      text-decoration:none;
}
.reply{
      width:380px;
      min-height:10px;
      background-color:	#d0d0d0;
      border-radius:8px;
      margin-left:10px;
      margin-top:10px;
      margin-bottom:10px;
      padding-top:1px;
      padding-right:5px;
      padding-left:5px;
}
.commenthead{
      text-decoration:none;
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
	            <img class="head" src="./headimages/${ sessionScope.userId }.jpg" height="100" width="100" >
	            <a class="headimg" href='changeHead.jsp'>换个头像？</a><br>
	            ${ sessionScope.login }
	        </center>    
      </div>
	     
	  
      
      <div id="section">
           <form action='message' method='post' enctype="multipart/form-data" onsubmit="return check()">有什么新鲜事想告诉大家？<br/>
                  <textarea cols='48' rows='8' name='weibo' id='weibo' placeholder="140字以内哦~" ></textarea><br>
                  <table>
                 <tr> 
                     <td>上传图片:</td>                   
                  <td><input type="file" name="fileupload" onchange="previewImage(this)" > </td> 
                  <td><input type="submit" value="发布"></td>
                 </tr>
            </table>
           </form>
           
          <div id="preview" class = "head">
               <img id="imghead" height="120" width="100" src=''/>
          </div>
      </div>
      
	  <c:forEach items="${sessionScope.commentWeibo}" var="li" varStatus="status">   
	     <div class="weibo">
	         <dl> 
                 <dt><img class="head2" src="./headimages/${ li.userId }.jpg" height="50" width="50" ><font size="2" style="word-wrap:break-word"><a class="username" href="othersHome?username=${li.username}">${li.username}：</a></font><br><br></dt>    
                 <dt><font size="4" style="word-wrap:break-word">${li.content}</font><br></dt>
                 <c:if test="${li.pictureExist}" >
                     <img src="./weiboimages/${li.weiboId}.jpg" height="100" width="120" ><br>
                 </c:if><hr>
                 <dt><font size="2">${li.dateLine}</font><br></dt>
                 <dt><font size="2">
	                 <a class="commenthead" href="toComment?commentWeiboId=${li.weiboId}">评论${li.comment}</a>
	                 
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
	     
	     <div id="commentarea${li.weiboId}" class="commentarea" >
	          <form action='comment?commentWeiboId=${li.weiboId}' method='post' onsubmit="return checkcomment(${li.weiboId})"><br/>
                  <textarea cols='50' rows='2' name='comment${li.weiboId}' id='comment${li.weiboId}' placeholder="来个神评吧~" ></textarea><br>
	              <button class="b1" type='submit' >评论</button><hr>
	          </form>
	          
	          <c:forEach items="${sessionScope.commentList}" var="list" varStatus="status">
	             <dt><img class="head2" src="./headimages/${ list.userId }.jpg" height="40" width="40" ><font size="2" style="word-wrap:break-word"><a class="username" href="othersHome?username=${list.username}">${list.username}：</a></font><br></dt>    
                 <dt><br><font size="4" style="word-wrap:break-word">${list.content}</font><br></dt>
                 <dt><font size="2">${list.dateLine}</font></dt>
                 <a class="reply" href="javascript:void(0)" onclick="reply('${list.commentId}')" >查看回复${list.replyComment}</a>
                 <c:if test="${list.canDelete }">
                	 <a class="delete" href='commentDelete?commentId=${list.commentId }&commentWeiboId=${li.weiboId}'><font size="2">删除</font></a>
                 </c:if>
                 
                 <div class="reply" id="replyarae${list.commentId}" style="display:none">
                    <form action='replyComment?commentId=${list.commentId}&commentWeiboId=${li.weiboId}' method='post' onsubmit="return checkreply(${list.commentId})"><br/>
                        <textarea cols='48' rows='2' name='reply${list.commentId}' id='reply${list.commentId}' placeholder="回复" ></textarea><br>
	                    <button class="b1" type='submit'>回复</button><hr>
	                </form>
                    
                    <c:set var='ReplyComment' value="ReplyComment${list.commentId}" scope="page"/>
                    <c:forEach items="${sessionScope[ReplyComment]}" var="list2" varStatus="status">
                           <dt><img class="head2" src="./headimages/${ list2.userId }.jpg" height="40" width="40" ><font size="2" style="word-wrap:break-word"><a class="username" href="othersHome?username=${list2.username}">${list2.username}：</a></font><br></dt>    
                           <dt><br><font size="4" style="word-wrap:break-word">${list2.content}</font><br></dt>
                           <dt><font size="2">${list2.dateLine}</font></dt>
                           <c:if test="${list2.canDelete }">
                	              <a class="delete" href='replyCommentDelete?replyCommentId=${list2.replyCommentId }&commentId=${list2.commentId}&commentWeiboId=${li.weiboId}'><font size="2">删除</font></a>
                           </c:if>
                           <hr>
                    </c:forEach>
                 </div>
                 <hr>
	          
	          </c:forEach>
	     </div>
	  </c:forEach>
	  
<script type="text/javascript">
function reply(commentId){
	if(document.getElementById("replyarae"+commentId).style.display=="none"){
		document.getElementById("replyarae"+commentId).style.display="";
	}
	else document.getElementById("replyarae"+commentId).style.display="none";
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

function check(){ 
	var pattern=/[\S]/;
	var weibo = document.getElementById("weibo").value;
	if(weibo.length == 0 || !pattern.test(weibo)){
		  alert("可是你什么都没写耶...");  
		  return false;
	   }else if(weibo.length > 140){
		   alert("140字以内哦~"); 
		   return false;
	   }
	   else return true;   
	 }  
function checkcomment(weiboId){
	var pattern=/[\S]/;
	var comment = document.getElementById("comment"+weiboId).value;
	if(comment.length == 0 || !pattern.test(comment)){
		  alert("可是你什么都没写耶...");  
		  return false;
	   }
	   else return true;    
}
function checkreply(commentId){
	var pattern=/[\S]/;
	var reply = document.getElementById("reply"+commentId).value;
	if(reply.length == 0 || !pattern.test(reply)){
		  alert("可是你什么都没写耶...");  
		  return false;
	   }
	   else return true;    
}
</script>
<script type="text/javascript">
function previewImage(file)
{
    var MAXWIDTH  = 150;
    var MAXHEIGHT = 170;
    var div = document.getElementById('preview');
    if (file.files && file.files[0])
    {
        div.innerHTML ='<img id=imghead>';
        var img = document.getElementById('imghead');
        img.onload = function(){
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width  =  rect.width;
            img.height =  rect.height;
            //                 img.style.marginLeft = rect.left+'px';
            img.style.marginTop = rect.top+'px';
        }
        var reader = new FileReader();
        reader.onload = function(evt){img.src = evt.target.result;}
        reader.readAsDataURL(file.files[0]);
    }
    else //兼容IE
    {
        var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
        div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
    }
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight )
    {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
      
        if( rateWidth > rateHeight )
        {
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else
        {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
  
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}
window.onbeforeunload = function(){
    var scrollPos;    
    if (typeof window.pageYOffset != 'undefined') {
        scrollPos = window.pageYOffset;
    }
    else if (typeof document.compatMode != 'undefined' &&
        document.compatMode != 'BackCompat') {
        scrollPos = document.documentElement.scrollTop;
    }
    else if (typeof document.body != 'undefined') {
        scrollPos = document.body.scrollTop;
    }
    document.cookie="scrollTop="+scrollPos; //存储滚动条位置到cookies中
}

window.onload = function(){ 
    if(document.cookie.match(/scrollTop=([^;]+)(;|$)/)!=null){
        var arr=document.cookie.match(/scrollTop=([^;]+)(;|$)/); //cookies中不为空，则读取滚动条位置
        document.documentElement.scrollTop=parseInt(arr[1]);
        document.body.scrollTop=parseInt(arr[1]);
    }
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
</body>
</html>