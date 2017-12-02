<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
	     <link rel="stylesheet" type="text/css"
	          href="${pageContext.request.contextPath}/wlib/jquery-easyui-1.5/themes/default/easyui.css">
	    <link rel="stylesheet" type="text/css"
	          href="${pageContext.request.contextPath}/wlib/jquery-easyui-1.5/themes/icon.css">
	    <script type="text/javascript"
	            src="${pageContext.request.contextPath}/wlib/jquery-easyui-1.5/jquery.min.js"></script>
	    <script type="text/javascript"
	            src="${pageContext.request.contextPath}/wlib/jquery-easyui-1.5/jquery.easyui.min.js"></script>
	    <script type="text/javascript"
	            src="${pageContext.request.contextPath}/wlib/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
	    <script type="text/javascript">
	    $(function(){  
	    	var childlist = ${child};
	    	$("#blBt").click(function () {                
	    		var showlist = $("<ul></ul>");                 
	    		listMenus(childlist, showlist);                 
	    		$("#div_menu").append(showlist);             
	    	});
	    });
	    function listMenus(mList,parent){
	    	for(var i = 0;i<mList.length;i++){
	    		 //如果该节点没有子节点，则直接将该节点li以及文本创建好直接添加到父亲节点中
	    		if(null==mList[i].children || mList[i].children.length ==0){                    
	    			$("<li></li>").append(mList[i].t.menuName).appendTo(parent);
	    		}   
	    		//如果有子节点，则遍历该子节点 
	    		if(mList[i].children.length > 0){
	    			var li = $("<li></li>");
	    			$(li).append(mList[i].t.menuName).append("<ul></ul>").appendTo(parent);
	    			listMenus(mList[i].children,$(li).children().eq(0))
	    		}
	    	}
	    }
	    </script>
    </head>
    <body>
    <input type="button" width="100" name="test" value="test" id="blBt">
    
    <div id="div_menu"></div>
    
    </body>
</html>