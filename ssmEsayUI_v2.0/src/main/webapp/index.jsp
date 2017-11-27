<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Index</title>
		<link href="<%=basePath%>/wlib/jquery-easyui-1.5/themes/default/easyui.css" rel="stylesheet" type="text/css" >
    	<link href="<%=basePath%>/wlib/jquery-easyui-1.5/themes/icon.css" rel="stylesheet" type="text/css" >
		
		<script src="<%=basePath%>/wlib/jquery-easyui-1.5/jquery.min.js" type="text/javascript" ></script>
    	<script src="<%=basePath%>/wlib/jquery-easyui-1.5/jquery.easyui.min.js" type="text/javascript" ></script>
    	<script src="<%=basePath%>/wlib/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"type="text/javascript" ></script>
		
		<script src="<%=basePath%>/wlib/js/index.js"></script>
		<script type="text/javascript">
		function changeImage(){
			$('#randCodeImage').attr("src", "<%=basePath%>/user/getCheckCodeImage.do?timestamp=" + new Date() + Math.floor(Math.random()*24));
		}
		</script>
	</head>
	
	<body>
		<div style="margin:20px 0;"></div>
		<div class="easyui-layout" style="width:100%;height:80%;">
		<div data-options="region:'north'" style="height:10%"></div>
		<div data-options="region:'south',split:true" style="height:10%;"></div>
		<div data-options="region:'east',split:true" title="East" style="width:100px;"></div>
		<div data-options="region:'west',split:true" title="West" style="width:100px;"></div>
		<div data-options="region:'center',title:'Login',iconCls:'icon-ok'">		
				<form  id="loginForm" action="<%=basePath%>/user/login.do" method="post" >
					<span > ${resMap.msg}</span>
					<table  align="center" height="60%" width="50%">
						<tr>
							<td>用户名：</td>  <td><input type="text" id="username" name="username" value="${resMap.currentUser.username }" >  </td>
						</tr>
						<tr>
							<td>密码：   </td>   <td><input type="password" id="password" name="password" value="" >  </td>
						</tr>
						<tr>
							<td>验证图片：   </td>  
							<td>
                            	<img id="randCodeImage" alt="验证码"  src="<%=basePath%>/user/getCheckCodeImage.do" width="180" height="40" />
                       			<a href="#" onclick="changeImage()">看不清楚，换一张</a>
                       		</td>
						</tr>
						<tr>
							<td>验证码：   </td>  
							<td>
								<input type="text" id="VerificationCode" name="VerificationCode"  >
                       		</td>
						</tr>
						<tr>
							<td><input type="button" value="登录" width="50px" onclick="login()" > </td>
							<td><input type="reset"  value="重置" width="50px"> </td>
						</tr>
						<tr>
							<td><a href="<%=basePath%>/regUser.jsp">注册用户</a></td>
							<td><a href="#">忘记密码</a></td>
						</tr>
					
					</table>	
				</form>
			</div>
		</div>
	</body>
</html>