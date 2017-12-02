<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<html>
	<head>
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    	<title>柱子哥</title>
		<link href="<%=basePath%>/wlib/jquery-easyui-1.5/themes/default/easyui.css" rel="stylesheet" type="text/css" >
    	<link href="<%=basePath%>/wlib/jquery-easyui-1.5/themes/icon.css" rel="stylesheet" type="text/css" >
		
		<script src="<%=basePath%>/wlib/jquery-easyui-1.5/jquery.min.js" type="text/javascript" ></script>
    	<script src="<%=basePath%>/wlib/jquery-easyui-1.5/jquery.easyui.min.js" type="text/javascript" ></script>
    	<script src="<%=basePath%>/wlib/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"type="text/javascript" ></script>
		
    	<script src="<%=basePath%>/wlib/js/main.js"></script>
   
	    <script type="text/javascript">
		    $(function(){
		    	$('#tabs').tabs('add',{
		    		title:'首页',
		    		tools:[{
		    	        iconCls:'icon-mini-refresh',
		    	        handler:function(){
		    	        	var tab = $('#tabs').tabs('getSelected');
		    	        	tab.panel('refresh', '<%=basePath%>/tree.jsp');
		    	        }
		    	    }],
		            href:'<%=basePath%>/tree.jsp',
		            closable:false
		    	});
		    	
		    	$('.easyui-tree').tree({
		            onClick:function(node){
		            	if (node.attributes.url != '' && node.attributes.url != null)
		                {
		                    if ($('.easyui-tabs').tabs('exists', node.text))
		                    {
		                        $('.easyui-tabs').tabs('select', node.text);
		                    }
		                    else
		                    {
		                        $('.easyui-tabs').tabs('add',{   
		                            title:node.text,   
		                            href:node.attributes.url,   
		                            closable:true  
		                        });
		                    }
		                }
		            }
		        });
		    	
		  	}); //页面初始化结束
	
	    </script>
	<body class="easyui-layout">  
	    <div data-options="region:'north',noheader:true,split:false" style="height:66px;background:#FFFFCC">  
	        <h1>shangzhuzi System</h1>
	        <div id="user">${resMap.currentUser.username}</div>   
	    </div>  
	    <div data-options="region:'south',noheader:true,split:false" style="height:50px;"><a href="#">版权所有@ Corapte</a></div>  
	    <div data-options="region:'west',title:'菜单',split:true" style="width:200px;">  
			<div class="easyui-accordion"  border="false" id='menu'>
	            ${resMap.menu}
	         </div>
	    </div>  
	    <div data-options="region:'center'," style="padding:1px;">  
	        <div id='tabs' class="easyui-tabs" data-options="fit:true,border:false">  
	        </div>  
	    </div>  
	</body>  
</html>  
