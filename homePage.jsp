<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Instagram</title>
</head>
<body>
<div>
<c:forEach items="${getAllUsersPost}" var = "post">    
    ${post}<br>
</c:forEach>
</div>
<form action ="homePage" method = "post">

<input type="submit" value = "Home Page">
</form>
</body>
</html>