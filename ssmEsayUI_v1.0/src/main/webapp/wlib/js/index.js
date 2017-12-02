//login 部分js
function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    if (username == null || username == "") {
        alert("用户名不能为空！");
        return;
    }
    if (password == null || password == "") {
        alert("密码不能为空！");
        return;
    }
    $('#loginForm').submit();
}

//    $.ajax({
//        type: "POST",
//        dataType: "json",
//        url: "user/login.do",
//        data: $('#loginForm').serialize(),
//        success: function (result) {
////        	alert("result"+result.serialize());
//            if (result.code == SUCCESS_RESULT_CODE) {
////                setCookie("userName", result.data.currentUser.userName);
////                setCookie("roleName", result.data.currentUser.roleName);
//                window.location.href = "main.jsp";
//            }else{
//            	//提示
//            	alert("ERROR="+result.msg);
//            }
//            
//
//        },
//        error: function () {
//            alert("异常！");
//        }
//    });