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
		
		<script src="<%=basePath%>/wlib/jquery-easyui-1.5/jquery.min.js" type="text/javascript" />
    	<script src="<%=basePath%>/wlib/jquery-easyui-1.5/jquery.easyui.min.js" type="text/javascript" />
    	<script src="<%=basePath%>/wlib/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"type="text/javascript" />
		
		<script src="<%=basePath%>/wlib/js/constant.js"></script>
		
		<script type="text/javascript">
			function reg(){
			    $('#regUserFrom').submit();
			}
		</script>
		
	</head>
	
	<body>
			<div class="easyui-panel" title="新用户信息" style="width:400px">
				<div style="padding:10px 60px 20px 60px">
			    <form id="regUserFrom" method="post" action="<%=basePath%>/user/register.do" >
			    	<table cellpadding="5">
			    		<tr>
			    			<td>用户名:</td>
			    			<td><input class="easyui-textbox" type="text" name="username" id="username"></input></td>
			    		</tr>
			    		<tr>
			    			<td>邮箱:</td>
			    			<td><input class="easyui-textbox" type="text" name="email" id="email"></input></td>
			    		</tr>
			    		<tr>
			    			<td>手机号码:</td>
			    			<td><input class="easyui-textbox" type="text" name="tel" id="tel"></input></td>
			    		</tr>
			    		<tr>
			    			<td>password:</td>
			    			<td><input class="easyui-textbox" type="password" name="password" id="password"></input></td>
			    		</tr>
			    		<tr>
			    			<td>password1:</td>
			    			<td>
			    				<input class="easyui-textbox" type="password" name="password1" id="password1">
			    			</td>
			    		</tr>
			    		<tr>
			    			<td><input type="button" value="登录" width="50px" onclick="reg()" > </td>
							<td><input type="reset"  value="重置" width="50px"> </td>
			    		</tr>
			    	</table>
			    </form>
			    </div>
			</div>
		
	</body>
</html>