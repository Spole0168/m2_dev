<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()
            + path ;
%>
<script>
	var saveUrl = "<%=basePath%>/menu/save.do";
$(function(){
	
});
function checkForm(){
	
	return "";
}
function submit_syn(){
	$("#editForm").submit();
}
function submit_asyn(){
	var msg = checkForm();
	if(msg!=null||msg!=""){
		//tip
	}
	 $.ajax({
	 type: "POST",
	 dataType: "json",
	 url: saveUrl,
	 data: $('#editForm').serialize(),
	 success: function (result) {
		 $(dlgUserId).dialog('close');
		 searchData();
	 }
	});
}
function cancel() {
    $('#myform').form('clear');
    $(dlgUserId).dialog('close');
}	
</script>
<div>
    <form id="editForm" method="post" action="<%=basePath%>/menu/save.do">
        <table align="center">
            <tr>
                <td>
                <label>code:</label>
                </td>
                <td>
                <input class="easyui-textbox" type="hidden" id="id" name="id" 
                    style="width: 290px;" data-options="required:true" value="${entity.id}">
                
                <input class="easyui-validatebox" type="text" id="menuCode" name="menuCode" 
                    style="width: 290px;" data-options="required:true" value="${entity.menuCode}">
                </td>
            </tr>
            <tr>
                <td>
                <label>name:</label>
                </td>
                <td>
                <input class="easyui-validatebox" type="text" id="menuName" name="menuName"
                    style="width: 290px;" data-options="required:true" value="${entity.menuName}">
                </td>
            </tr>
            <tr>
                <td>
                <label>url:</label>
                </td>
                <td>
                <input class="easyui-validatebox" type="text" id="menuUrl" name="menuUrl"
                    style="width: 290px;" data-options="required:true" value="${entity.menuUrl}">
                </td>
            </tr>
        </table>
</form>
</div>
<div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submit_asyn()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancel()">取消</a>
</div>
