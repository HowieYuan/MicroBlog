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
      margin-left:50px;
}
body {
      background-image:url("images/login.jpg");
      background-attachment:fixed;
      background-size:cover;
}
.myhead{
      float:right;
      height:40px;
      width:40px;
      margin-right:5px;
      box-shadow:-3px 3px 4px hsla(0, 0%, 0%, 0.3);
}
.mymessage{
      float:right;
      max-width:400px;
      word-wrap:break-word;
      min-height:21px;
      box-shadow:-3px 3px 4px hsla(0, 0%, 0%, 0.3);
      background-color:	#00DB00;
      border-radius:4px;
      margin-bottom:10px;
      padding:9px 10px 8px 10px;
      font-size:18px;
}
.hismessage{
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
.hishead{
      float:left;
      height:40px;
      width:40px;
      margin-left:5px;
      box-shadow:3px 3px 2px hsla(0, 0%, 0%, 0.3);
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
.chatinput{
      width:830px;
      height:35px;
      margin-left:220px;
      border:solid 2px 	#ADADAD;
      border-radius:10px;
      outline:none;
      padding-left:5px;
}
.myarrow {
	  float:right;
	  width:0px;
	  height:0px;
	  font-size:0;
	  margin-top:20px;
	  border:solid 8px;
	  border-color:transparent transparent transparent #00DB00;
}
.hisarrow {
	  float:left;
	  width:0px;
	  height:0px;
	  font-size:0;
	  margin-top:20px;
	  border:solid 8px;
	  border-color:transparent #fff transparent transparent;
}
.chatname{
      font-size:20px;
      font-weight:900;
      color:white;
}
.chatbutton{
      width:55px;
      height:35px;
      border-radius:10px;
      background-color:	#00DB00;
      border:solid 2px 	#00DB00;
      color:white;
      font-size:15px;
}
.mydate{
      margin-left:680px;
      color:#6C6C6C;
      font-size:14px;
}
.hisdate{
      color:#6C6C6C;
      font-size:14px;
}
.deletemychatms{
      float:right;
      margin-right:10px;
      margin-top:10px;
}
.deletehischatms{
      float:left;
      margin-left:10px;
      margin-top:10px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>私聊</title>
</head>
<body onload="getServerInfo('${requestScope.myuserId}','${requestScope.hisuserId}')">
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
    
      <div class="chathead">
            <center>
            <a class="chatname" href='othersHome?username=${requestScope.hisusername}'>${requestScope.hisusername}</a>
            </center>
      </div>
      
      <div class="chatbox" id="chatbox">
			<c:forEach items="${requestScope.chatList}" var="li" varStatus="status">
			   ${li}<div class="arrow"></div>
			   <div style="clear:both"></div><br>
			</c:forEach>
			
			<div id="newchat">
			</div>
      </div> 
	
	  <form name="myform" method="post" action="sendChatMessage?hisuserId=${requestScope.hisuserId}&action=user" onSubmit="return check()" target="sform">
		  <input class="chatinput" id="msginput" type="text" name="chatmsg" placeholder="说点什么吧~"/>
		  <button class="chatbutton" type="button" onclick="return sendok('msginput')" >发送</button>
	  </form>
	  <iframe width="0" height="0" style="display: none" name="sform"></iframe>
</body>


<script type="text/javascript">
function deletechatms(Id){
	if(document.getElementById("deletechatms"+Id).style.display=="none"){
		document.getElementById("deletechatms"+Id).style.display="";
	}
	else document.getElementById("deletechatms"+Id).style.display="none";
}
function getServerInfo(myuserId,hisuserId){
	document.getElementById("chatbox").scrollTop = 9999999999;    //滚动条为最底
	document.getElementById('msginput').value = "";          //输入框归零
	xmlHttp=new XMLHttpRequest();
	xmlHttp.onreadystatechange = function(){
		 if (xmlHttp.readyState==4 && xmlHttp.status==200){
		    	var message = xmlHttp.responseText;
		    	var num1 = message.indexOf("a");
			    var id = message.substring(0,num1);
			    message = message.substring(num1+1);
			    var num2 = message.indexOf("a");
			    var by_id = message.substring(0,num2);
			    message = message.substring(num2+1);
			    var num3 = message.indexOf("a");
			    var date = message.substring(0,num3);
			    message = message.substring(num3+1);
			    var num4 = message.indexOf("a");
			    var chatId = message.substring(0,num4);
			    message = message.substring(num4+1);
			    if(myuserId == id && hisuserId == by_id){
			    	document.getElementById("newchat").innerHTML += "<img class='myhead' src='./headimages/"+myuserId+".jpg'><div class='myarrow'></div>"
			    	                                              + "<div class='mydate'>"+date+"</div>"
			    	                                              + "<div class='mymessage' onclick='deletechatms("+chatId+")'>"+message+"</div>"
			    	                                              + "<form method='post' action='deleteChatMes?action=my&hisuserId="+hisuserId+"&chatId="+chatId+"&myuserId="+myuserId+"'>"
			    	                                              + "<button class='deletemychatms' id='deletechatms"+chatId+"' style='display:none'>删除</button></form>" + "<div style='clear:both'></div>";
			    }
			    else if(hisuserId == id && myuserId == by_id){
			    	document.getElementById("newchat").innerHTML += "<img class='hishead' src='./headimages/"+hisuserId+".jpg'><div class='hisarrow'></div>"
			    	                                              + "<div class='hisdate'>"+date+"</div>"
			    	                                              + "<div class='hismessage' onclick='deletechatms("+chatId+")'>"+message+"</div>"
			    	                                              + "<form method='post' action='deleteChatMes?action=his&hisuserId="+hisuserId+"&chatId="+chatId+"&myuserId="+myuserId+"'>"
			    	                                              + "<button class='deletehischatms' id='deletechatms"+chatId+"' style='display:none'>删除</button></form>" + "<div style='clear:both'></div>";
			    }
		    	getServerInfo(myuserId,hisuserId);
			   }
	}
	xmlHttp.open("GET","getChatMessage?time="+Math.random(),true);   //使用一个新的参数重新请求当前资源,这样,浏览器会以为这是一个不同的资源,而不会从缓存中返回
	xmlHttp.send(null);
}

function getMessage(){
	 if (xmlHttp.readyState==4 && xmlHttp.status==200){
    	var message = xmlHttp.responseText;
	    var id = message.substring(0,2);
	    message = message.substring(2);
	    document.getElementById("newchat").innerHTML += message + "\r\n";
    	getServerInfo();
	   }
}


function sendok(obj){
	var pattern=/[\S]/;
	var msginput = document.getElementById("msginput").value;
	if(msginput=="" || !pattern.test(msginput)){
		alert("你什么都没写耶...."); 
		return false;
	}
	else myform.submit();          //提交表单
}

function check(){
    var pattern=/[\S]/;
	var msginput = document.getElementById("msginput").value;
	if(msginput=="" || !pattern.test(msginput)){
		alert("你什么都没写耶...."); 
		return false;
	}
	else return true;
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