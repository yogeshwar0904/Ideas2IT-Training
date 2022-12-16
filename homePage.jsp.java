<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Instagram</title>
</head>
<body>
<div>
<c:forEach items="${listOfPosts}" var = "post">    
    ${post}<br>
</c:forEach>
</div>
<form action ="viewProfile" method = "get">

<input type="submit" value = "ViewProfile">
</form>
</body>
</html>