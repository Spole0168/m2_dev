<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<script>
$(function(){
	$('#dg').datagrid({
        url:'<%=basePath%>/menu/listMenus.do',
        singleSelect:true,
        columns:[[
            {field:'id',title:'id',hidden:true,width:300},
            {field:'menuPid',title:'父级菜单id',width:300},
            {field:'menuName',title:'菜单名称',width:300},
            {field:'menuCode',title:'类别',width:300},
            {field:'menuPid',title:'菜单编码',width:300},
            {field:'menuType',title:'菜单类型',width:300},
            {field:'menuLevel',title:'菜单级别',width:300},
            {field:'menuUrl',title:'菜单连接地址',width:300},
            {field:'menuOrder',title:'菜单排序',width:300},
            {field:'isValid',title:'isValid',width:300},
            {field:'creator',title:'creator',width:300},
            {field:'createTime',title:'createTime',width:300}
        ]],
        toolbar: [{
        	text:'添加',
            iconCls: 'icon-add',
            handler: function(){
            	$('#window').window({
            		title:'添加',
            		closed:false
            	});
            	$('#window').window('refresh', '<%=basePath%>/jsp/adddatadict.jsp');
            }
        },'-',{
        	text:'编辑',
            iconCls: 'icon-edit',
            handler: function(){
            	var select = $('#dg').datagrid('getSelected');
                if (select == null){
                    $.messager.alert("Info","请选择记录！","Info");
                }
                else{
                    $.ajax({
                        type: 'POST',   
                        url: '<%=basePath%>/commonController/preUpdateDatadictData.do',
                        data: 'id='+select.id,
                        dataType:'text',
                        success: function(msg){
                        	$('#window').window({
                                title:'编辑',
                                closed:false,
                                content:msg
                            });
                        }
                    });
                }
            }
        },'-',{
        	text:'删除',
            iconCls: 'icon-remove',
            handler: function(){
            	var select = $('#dg').datagrid('getSelected');
            	if (select == null){
            		$.messager.alert("Info","请选择记录！","Info");
            	}
            	else{
            		$.ajax({
            			type: 'POST',   
                        url: '<%=basePath%>/commonController/deleteDatadictData.do',
                        data: 'id='+select.id,
                        dataType:'text',
                        success: function(msg){
                            var temp = $.parseJSON(msg); 
                            if (temp.success) {
                                $.messager.alert('删除成功！',temp.msg,'Info');
                            }
                            else{
                                $.messager.alert('删除失败',temp.msg,'Info');
                            }
                            $('#dg').datagrid('load');
                          }
            		});
            	}
            }
        }],
        onLoadSuccess:function(data){
        	$("#datalist").datagrid("loadData",list);
        }
    });
});
	
</script>
<div>
    <table id="dg"></table>
</div>
