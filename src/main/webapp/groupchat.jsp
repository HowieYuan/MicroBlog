<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
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
.search{
      margin-left:800px;
      margin-top:-22px;
}
.thename{
      margin-left:1060px;
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
a.headimg{
      font-size:13px;
      color:#BB3D00;
      text-decoration:none;
      margin-left:0px;
      text-shadow:0 0 0px;
}
img.head2{
      margin-top:20px;
      border-radius:80px;
      margin-top:5px;
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
a.username{
      font-weight:900;
      text-decoration:none;
      color:black;
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
.boadr{
      width:900px;
      height:600px;
      margin-left:220px;
      background-color:#F0F0F0;
      border:2px solid 	#BB3D00;
      border-radius:8px;
}
.myarrow {
	  float:right;
	  width:0px;
	  height:0px;
	  font-size:0;
	  margin-top:19px;
	  border:solid 8px;
	  border-color:transparent transparent transparent #00DB00;
}
.hisarrow {
	  float:left;
	  width:0px;
	  height:0px;
	  font-size:0;
	  margin-top:19px;
	  border:solid 8px;
	  border-color:transparent #fff transparent transparent;
}
.chatname{
      font-size:20px;
      font-weight:900;
      margin-left:420px;
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
      margin-left:685px;
      color:#6C6C6C;
      font-size:14px;
}
.hisdate{
      color:#6C6C6C;
      font-size:14px;
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
	  
      <div class="boader">
	      <div class="chathead">
	            <span class="chatname">管理员群信</span>
	      </div>
	      
	      <div class="chatbox" id="chatbox">
				<c:forEach items="${requestScope.groupList}" var="li" varStatus="status">
				   ${li}<div class="arrow"></div>
				   <div style="clear:both"></div><br>
				</c:forEach>
				
				<div id="newchat">
				</div>
	      </div> 
		
		  <form name="myform" method="post" action="sendChatMessage?action=group" onSubmit="return check()"  target="sform">
			  <input class="chatinput" id="msginput" type="text" name="chatmsg" placeholder="说点什么吧~"/>
			  <button class="chatbutton" type="button" onclick="return sendok('msginput')" >发送</button>
		  </form>
		  <iframe width="0" height="0" style="display: none" name="sform"></iframe>
      </div>
</body>


<script type="text/javascript">
function getServerInfo(){
	document.getElementById("chatbox").scrollTop = 9999999999;
	document.getElementById('msginput').value = "";
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
			    document.getElementById("newchat").innerHTML += "<img class='myhead' src='headimages/admin.jpg'><div class='myarrow'></div>"
			    	                                              + "<div class='mydate'>"+date+"</div>"
			    	                                              + "<div class='mymessage' id='mymessage'>"+message+"</div>" + "<div style='clear:both'></div>";
		    	getServerInfo();
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
function sendok(){
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
</script>

</html>