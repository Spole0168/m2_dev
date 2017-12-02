//init 初始化 树形菜单：
function initMenuLevelList(childlist){
	var showlist = $("#menu_list");                 
	listMenus(childlist, showlist);                 
//	$("#div_menu").append(showlist);   
}
function listMenus(mList,parent){
	for(var i = 0;i<mList.length;i++){
		 //如果该节点没有子节点，则直接将该节点li以及文本创建好直接添加到父亲节点中
		if(null==mList[i].children || mList[i].children.length ==0){  
			$("<a><a>")
			.attr("href","javascript:openTab("+mList[i].t,mList[i].t.menuName,mList[i].t.menuName+")")
			.attr("class","easyui-linkbutton")
			.attr("data-options","plain:true,iconCls:'icon-wenzhang'")
			.attr("style","width: 150px;")
			.append(mList[i].t.menuName)
			.appendTo(parent);
		}   
		//如果有子节点，则遍历该子节点 
		if(mList[i].children.length > 0){
//			var li = $("<li></li>");
//			$(li).append(mList[i].t.menuName).append("<ul></ul>").appendTo(parent);
//			listMenus(mList[i].children,$(li).children().eq(0))
			var div = $("<div></div>");
			$(div)
//			.attr("id",mList[i].t.menuName)
			.attr("title",mList[i].t.menuName)
			.attr("data-options","selected:true,iconCls:'icon-wenzhangs'")
			.attr("style","padding: 10px;height:10px;")
			.append(mList[i].t.menuName)
			.appendTo(parent);
			listMenus(mList[i].children,$(div).children().eq(0));
		}
	}
}




