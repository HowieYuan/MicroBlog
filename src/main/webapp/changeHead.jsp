<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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
      margin-bottom:20px;
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
h3{
      color:black;
      text-shadow:0 0 8px;
      margin-top:60px;
}
.boader{
      width:900px;
      height:350px;
      margin-left:220px;
      background-color:#F0F0F0;
      border:2px solid 	#BB3D00;
      border-radius:8px;
}
#preview{overflow:hidden;}
#imghead {
         filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
         border-radius:160px;
}
body {
      background-image:url("images/login.jpg");
      background-size:cover;
      background-attachment:fixed;
      }
</style>
<style>
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更换头像</title>
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

	<div class="boader"> 
        <h3 style='text-align:center'>更换头像</h3>
        <center>
            <div id="preview" class = "head">
               <img id="imghead" height="120" width="120" src='./headimages/${ sessionScope.userId }.jpg'/>
            </div>
                <br>
             <form action="uploadPicture" method="post" enctype="multipart/form-data">  
                                         上传图片：<input type="file" name="fileupload" onchange="previewImage(this)"/>  
             <input type="submit" value="确定"/>  
             </form>
        </center>
	</div>



<script type="text/javascript">
function previewImage(file){
    var MAXWIDTH  = 120;
    var MAXHEIGHT = 120;
    var div = document.getElementById('preview');
    if (file.files && file.files[0]){
        div.innerHTML ='<img id=imghead>';
        var img = document.getElementById('imghead');
        img.onload = function(){
            var rect =  {top:0, left:0, width:MAXWIDTH, height:MAXHEIGHT};
            img.width  =  rect.width;
            img.height =  rect.height;
            img.style.marginTop = rect.top+'px';
        }
        var reader = new FileReader();
        reader.onload = function(evt){img.src = evt.target.result;}
        reader.readAsDataURL(file.files[0]);
    }
}
function clacImgZoomParam( MAXWIDTH, MAXHEIGHT, width, height ){
    var param = {top:0, left:0, width:MAXWIDTH, height:MAXHEIGHT};
    return param;
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