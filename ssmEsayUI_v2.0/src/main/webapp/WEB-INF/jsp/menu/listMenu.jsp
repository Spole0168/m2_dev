<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
		
<script>
	//全局变量
	var dlgUserId 		= "#dialog-ajax-view";					//弹出页面所在div
	var listUrl 		= "<%=basePath%>/menu/list.do";
	var searchUrl 		= "<%=basePath%>/menu/queryByPage.do";	// 分页查询地址
	var editUrl 		= "<%=basePath%>/menu/edit.do";			//编辑（新增，修改，查看）地址
	var delUrl 			= "<%=basePath%>/menu/delete.do";	
	var exportUrl 		= "<%=basePath%>/menu/export.do";	
$(function(){
	$('#datagrid').datagrid({
        url: searchUrl ,
        singleSelect:true,
        pagination:true,
        pageList:[5,10,30,50],
        columns:[[
            {field:'id',title:'id',hidden:true,width:100},
            {field:'menuPid',title:'父级菜单id',width:100},
            {field:'menuName',title:'菜单名称',width:100},
            {field:'menuType',title:'菜单类型',width:100},
            {field:'menuLevel',title:'菜单级别',width:100},
            {field:'menuUrl',title:'菜单连接地址',width:100},
            {field:'menuOrder',title:'菜单排序',width:100},
            {field:'isValid',title:'isValid',width:100},
            {field:'creator',title:'creator',width:100},
            {field:'createTime',title:'createTime',width:100}
        ]],
        onLoadSuccess:function(data){
        }
    });
});
	//定义函数 -- 查询
	function searchData(){
	    $('#datagrid').datagrid('load', {aaa:"a"});
	}
	
	function add(){
		openDialog(dlgUserId,"添加",editUrl);
	}
	function del(){
		var select = $('#datagrid').datagrid('getSelected');
        if (select == null){
            $.messager.alert("Info","请选择记录！","Info");
        }
		var id = select.id;
		 $.messager.confirm('继续操作', '确定执行下发操作吗?', 
				 function(confirm){
			 if(confirm){
				 $.ajax({
					 type: "POST",
					 dataType: "json",
					 url: delUrl,
					 data: {id:id},
					 success: function (result) {
						 searchData();
					 }
					});
			 }
		 });
	}
	function upd(){
		var select = $('#datagrid').datagrid('getSelected');
        if (select == null){
            $.messager.alert("Info","请选择记录！","Info");
        }
		var id = select.id;
		openDialog(dlgUserId,"添加",editUrl+"?id="+id);
	}
	function list_export(){
		//获得表头
		var columnFields = $("#datagrid").datagrid('getColumnFields');
		var fs = "";
		for(var cc in columnFields){
			fs = fs + columnFields[cc]+"-";
		}
		fs = fs.substring(0,fs.length-1);
		window.location = exportUrl+"?columnFields="+fs;
		return;
	}
	
	
	
	function openDialog(dlgId, title, url){
		$(dlgId).html("");
		$(dlgId).dialog({
			position:[500,200],
			width:650,
			height:600,
			autoOpen:false,
			modal:true,
			useSlide:true,
			resizable: false,
			title:title,
			modal:true
			});
		$(dlgId).load(url);
		$(dlgId).dialog('open');
	}
</script>
<form id="conditionForm" method="post">
	<table cellpadding="5">
		<tr>
   			<td>Name:</td>
<!--    			<td><input class="easyui-textbox" type="text" name="menuName" id="menuName" data-options="required:false"></input></td> -->
   		</tr>
	</table>
	
    <a id="serachBtn" onclick="searchData();" 	href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
</form>
    <a id="addBt" 	  onclick="add()" 	 	href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a id="updBt" 	  onclick="upd()" 		href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
    <a id="delBt" 	  onclick="del()" 		href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    <a id="exportBt" 	  onclick="list_export()" 		href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-remove'">导出</a>
<div>
    <table id="datagrid"></table>
</div>
<div id="dialog-ajax-view"></div>

