<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.loginOut{
      margin-left:1170px;
      margin-top:-16px;
}
.name{
      margin-left:1060px;
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
      margin-left:113px;
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
button.allchat{
      margin-bottom:0px;
}
button.b1{
      width:40px;
      height:22px;
      margin-bottom:30px;
      border-radius:5px;
      outline:none;
      background-color:#FF8040;
      border:2px solid #FF8040;
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
.freeze{
      margin-top:15px;
      margin-bottom:-15px;
}
.freezeDiv{
      margin-top:-20px;
      margin-bottom:15px;
}
.freezeInput{
      width:20px;
}
.freezeBu{
      margin-top:3px;
      margin-bottom:6px;
}
.selectbutoon{
      float:left;
      margin-top:-280px;
      margin-left:170px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员首页</title>
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
          <a class="nav" href='#' onclick="send()">群发信息</a>
	  </div>
	  
	  <table class="User" border="0" width="500" height="20">
	      <tr class="heading">
            <th id="selecthead" style="display:none">
                                            选择<br>
               <button class="allchat" onclick="allselect()">全选</button>
            </th>
            <th>头像</th>
            <th>用户名</th>
            <th>微博</th>
            <th>关注</th>
            <th>粉丝</th>
            <th>个人信息</th>
            <th>账号状态</th>
            <th>封号</th>
            <th>私信</th>
          </tr>
		
		  <c:forEach items="${sessionScope.userList}" var="li" varStatus="status">   
		       <tr class="user" align="center"> 
		          <td id="selectbox${li.userId}" style="display:none"><input id="chatselectbox${li.userId}" name="chatselectbox" type="checkbox" value="${li.userId}" class="" onchange="change('${li.userId}')"></td>  
		          <td><img class="head2" src="./headimages/${ li.userId }.jpg" height="40" width="40" ></td>
	              <td><a class="nav1" href="userAdmin?action=weibo&userId=${ li.userId }&username=${li.username}&weiboNum=${ li.weibo }&followNum=${ li.follow }&fansNum=${li.fans}" target="_blank">${li.username}</a> </td> 
		          <td><a class="nav2" href='userAdmin?action=weibo&userId=${ li.userId }&username=${li.username}&weiboNum=${ li.weibo }&followNum=${ li.follow }&fansNum=${li.fans}' target="_blank">微博${ li.weibo }</a></td>
		          <td><a class="nav2" href='userAdmin?action=follow&userId=${ li.userId }&username=${li.username}&weiboNum=${ li.weibo }&followNum=${ li.follow }&fansNum=${li.fans}' target="_blank">关注${ li.follow }</a></td> 
		          <td><a class="nav2" href='userAdmin?action=fans&userId=${ li.userId }&username=${li.username}&weiboNum=${ li.weibo }&followNum=${ li.follow }&fansNum=${li.fans}' target="_blank">粉丝${li.fans}</a></td> 
		          <td><a href="userAdmin?action=information&userId=${ li.userId }&username=${li.username}&weiboNum=${ li.weibo }&followNum=${ li.follow }&fansNum=${li.fans}" target="_blank">查看个人信息</a></td>
		          <c:choose>
		              <c:when test="${ li.hasFreeze }">
				          <td>已被封号<br>至${ li.endTime }</td>
				          <td>
				             <form class="freeze" action="freeze?userId=${ li.userId }&action=cancel"  method="post" onSubmit="return check(${ li.userId })">
				                <button class="b1" type='submit'  onmouseover="mOver(this)" onmouseout="mOut(this)" >已封</button><br>
				             </form>
				          </td>
		              </c:when>
		              <c:when test="${ li.login }">
		                  <td>在线</td>
				          <td>
				             <form class="freeze" action="freeze?userId=${ li.userId }&action=do" method="post" onSubmit="return check(${ li.userId })">
				                <button type='button' onclick="freeze('${ li.userId }')" >封号</button><br>
				                <div class="freezeDiv" id="freeze${ li.userId }" style="display:none">
				                   <input class="freezeInput" type='text' name='year' id="year${ li.userId }" placeholder="17"/>年
				                   <input class="freezeInput" name='month' id="month${ li.userId }" placeholder="5"/>月<br>
				                   <input class="freezeInput" name='day' id="day${ li.userId }" placeholder="2"/>日
				                   <input class="freezeInput" name='hour' id="hour${ li.userId }" placeholder="21"/>时<br>
				                   <button class="freezeBu" type='submit'>确定</button>
				                </div>
				             </form>
				          </td>
		              </c:when>
		              <c:otherwise>
		                  <td>下线</td>
				          <td>
				             <form class="freeze" action="freeze?userId=${ li.userId }&action=do" method="post" onSubmit="return check(${ li.userId })">
				                <button type='button' onclick="freeze('${ li.userId }')" >封号</button><br>
				                <div class="freezeDiv" id="freeze${ li.userId }" style="display:none">
				                   <input class="freezeInput" type='text' name='year' id="year${ li.userId }" placeholder="17"/>年
				                   <input class="freezeInput" name='month' id="month${ li.userId }" placeholder="5"/>月<br>
				                   <input class="freezeInput" name='day' id="day${ li.userId }" placeholder="2"/>日
				                   <input class="freezeInput" name='hour' id="hour${ li.userId }" placeholder="21"/>时<br>
				                   <button class="freezeBu" type='submit'>确定</button>
				                </div>
				             </form>
				          </td>
		              </c:otherwise>
		          </c:choose>
		          <td><a href="userAdmin?action=adminchat&userId=${ li.userId }&username=${ li.username }&weiboNum=${ li.weibo }&followNum=${ li.follow }&fansNum=${li.fans}"  target="_blank" >私信他/她</a></td>
		       </tr>
		  </c:forEach>
	  </table>
	  
	  <form action="groupchat?action=groupchat">
		  <c:forEach items="${sessionScope.userList}" var="li" varStatus="status">
		      <input id="chatselectbox2${li.userId}" name="chatselectbox2${li.userId}" type="checkbox" value="${li.userId}" class="" style="display:none">
		  </c:forEach>
		  <button id="selectbutoon" class="selectbutoon" style="display:none">确定</button>
	  </form>
</body>
<script type="text/javascript">
function change(userId){
	 var flag = document.getElementById("chatselectbox"+userId).checked;
	 if(flag==true){
		 document.getElementById('chatselectbox2'+userId).checked=true;
	 }
	 else document.getElementById('chatselectbox2'+userId).checked=false;
}

function allselect(){
	var num=1;
	var array = new Array();
	<c:forEach items="${sessionScope.userList}" var="li" varStatus="status"> 
	   array.push('${li.userId}'); 
	</c:forEach>
	for (var i=0;i<array.length;i++){
		if(document.getElementById('chatselectbox'+array[i]).checked==false)  {
			num=0;
			break;
		}
	}
	for (var i=0;i<array.length;i++){
		if(num==0){
			document.getElementById('chatselectbox'+array[i]).checked=true;
			document.getElementById('chatselectbox2'+array[i]).checked=true;
		}
		else{
			document.getElementById('chatselectbox'+array[i]).checked=false;
			document.getElementById('chatselectbox2'+array[i]).checked=false;
		}
	}
}

function send(){
	var array = new Array();
	<c:forEach items="${sessionScope.userList}" var="li" varStatus="status"> 
	   array.push('${li.userId}'); 
	</c:forEach>
	if(document.getElementById('selecthead').style.display=="none"){
		for (var i=0;i<array.length;i++){
			document.getElementById('selectbox'+array[i]).style.display="";
		}
		document.getElementById('selecthead').style.display="";
		document.getElementById('selectbutoon').style.display="";
	}
	else {
		for (var i=0;i<array.length;i++){
			document.getElementById('selectbox'+array[i]).style.display="none";
		}
		document.getElementById('selecthead').style.display="none";
		document.getElementById('selectbutoon').style.display="none";
	}
}

function freeze(userId){
	if(document.getElementById("freeze"+userId).style.display=="none"){
		document.getElementById("freeze"+userId).style.display="";
	}
	else document.getElementById("freeze"+userId).style.display="none";
}

function check(userId){
	var element = false;
	var element2 = false;
    
    
    var year = parseInt(document.getElementById("year"+userId).value);
    var month = parseInt(document.getElementById("month"+userId).value);
    var day = parseInt(document.getElementById("day"+userId).value);
    var hour = parseInt(document.getElementById("hour"+userId).value);
    
    var date = new Date();
    var nowYear = date.getYear()-100;
    var nowMonth = date.getMonth()+1;
    var nowDay = date.getDate();
    var nowHour = date.getHours()+1;
    
    
    if(document.getElementById("year"+userId).value!="" && document.getElementById("month"+userId).value!="" 
		&& document.getElementById("day"+userId).value!="" && document.getElementById("hour"+userId).value!=""){ 
	    element = true; 
    } 
    if(year > nowYear && month<= 12 && hour<=24){ 
    	if( (month==4||month==6||month==9||month==11) && day<=30){
    		element2 =true;
    	}
    	else if( year%4==0 && month==2 && day<=29){
    		element2 =true;
    	}
    	else if(month==2 && day<=28){
    		element2 =true;
    	}
    	else if((month==1||month==3||month==5||month==7||month==8||month==10||month==12) && day<=31){
    		element2 =true;
    	}
    }
    else if(year == nowYear && month > nowMonth && month<= 12 && hour<=24){ 
    	if( (month==4||month==6||month==9||month==11) && day<=30){
    		element2 =true;
    	}
    	else if( year%4==0 && month==2 && day<=29){
    		element2 =true;
    	}
    	else if(month==2 && day<=28){
    		element2 =true;
    	}
    	else if((month==1||month==3||month==5||month==7||month==8||month==10||month==12) && day<=31){
    		element2 =true;
    	}
    }
    else if(year == nowYear && month == nowMonth && day > nowDay){
    	if( (month==4||month==6||month==9||month==11) && day<=30){
    		element2 =true;
    	}
    	else if( year%4==0 && month==2 && day<=29){
    		element2 =true;
    	}
    	else if(month==2 && day<=28){
    		element2 =true;
    	}
    	else if((month==1||month==3||month==5||month==7||month==8||month==10||month==12) && day<=31){
    		element2 =true;
    	}
    }
    else if(year == nowYear && month == nowMonth && day == nowDay && hour > nowHour && hour<=24){
    	element2 =true;
    }
    if(element && element2){ 
    	alert("成功封号！"); 
    	return true;
    }
    else {
    	alert("输入的时间不正确，输入的时间必须晚于当前时间，请重新输入");
    	return false; 
    }
}

function mOver(obj){
obj.innerHTML="取消"
}

function mOut(obj){
obj.innerHTML="已封"
}
</script>
</html>