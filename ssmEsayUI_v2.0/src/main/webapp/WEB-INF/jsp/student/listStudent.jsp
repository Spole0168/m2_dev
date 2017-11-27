<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ListStu</title>
</head>
<body>
	<c:forEach items="${list}" var="stu" >
		<c:out value="${stu.name}"></c:out>
		<br>
		<c:out value="${stu.sex}"></c:out>
		<br>
		<c:out value="${stu.age}"></c:out>
		<br>
	</c:forEach>

</body>
</html>