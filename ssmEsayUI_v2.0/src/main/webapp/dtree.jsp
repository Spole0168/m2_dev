<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
	    <link href="${pageContext.request.contextPath}/wlib/dtree/dtree.css" rel="stylesheet" type="text/css" >
	    <script src="${pageContext.request.contextPath}/wlib/jquery-easyui-1.5/jquery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/wlib/dtree/dtree.js" type="text/javascript"></script>
	    </script>
    </head>
    <body>
	    <div class="dtree" id="menuList">
	    	<script type="text/javascript">
	    	 $(function(){  
	 	    	var childlist = ${dtree};
	 	    	d = new dTree('d');
	 	    	 d.add(0,-1,'ROOTNode',null,"","","${pageContext.request.contextPath}/wlib/dtree/img/base.gif");
	 	    	for(var i = 0;i<childlist.length;i++){
	 	    		/*  参数说明:
	 	    			1.ID 
	 	    			2.父ID 
	 	    			3.名称 
	 	    			4.URL 
	 	    			5.热点提示
	 	    			6.URL显示目标target 
	 	    			7.节点关闭时图标
	 	    			8.节点打开时图标
	 	    			9.判断节点是否打开*/
	 	    		d.add(
	 	    				childlist[i].id,
	 	    				null == childlist[i].menuPid?0:childlist[i].menuPid,
	 	    				childlist[i].menuName,
	 	    				null,
	 	    				childlist[i].menuName,
	 	    				"",
	 	    				"${pageContext.request.contextPath}/wlib/dtree/img/folder.gif",
	 	    				"${pageContext.request.contextPath}/wlib/dtree/img/folderopen.gif"
	 	    				);
	 	    	}
	  	    	document.write(d);
	 	    });
	    	</script>
		</div>
    
    </body>
</html>