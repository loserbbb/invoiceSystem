<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>欢迎登录增值税发票数据分析系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link href="style/login/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="scripts/login/jquery.js" type="text/javascript"></script>
	<script src="scripts/login/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    });
});  
</script> 
  
<body style="background-color:#1c77ac; background-image:url(images/login/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录增值税发票数据分析系统</span>        
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    
    <%--
    	登录表单
    --%>
    <form action="<c:url value='/LoginServlet'/>" method="post">
    <input type="hidden" name="method" value="login"/>
    <ul>
    <li>
    	<input name="uName" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value=''"/>
    	<span style = "color: red; font-weight: 900;">${errors.username }</span>
    </li>
    <li>
    	<input name="uPassword" type="password" class="loginpwd"/>
    	<span style = "color: red; font-weight: 900;">${errors.pwd }</span>
   	</li>
    <li>
    	<span style="color: red; font-weight: 900">${msgs }</span>
    </li>
    <li>
    	<input type="submit" class="loginbtn" value="登录" />
	    <label><input name="uRemmberpwd" type="checkbox" checked="checked" />记住密码</label>
	    <label><a href="#">忘记密码？</a></label>
    </li>
    </ul>
    </form>
    
    
    </div>
    </div>
    <div class="loginbm">版权所有，勿用于任何商业用途</div>
    
</body>
</html>
