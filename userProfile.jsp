<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Instagram</title>
</head>
<body>
    <form action = "update" method="post">
    <input type = "text" placeholder = "user name" name = "userName">
    <input type="text" placeholder="Update Password" name = "password">
    <input type="number" placeholder = "Update Mobile Number" name = "mobileNumber">  
<input type="submit" value = "Update">
</form>

    <form action = "getUserProfileDetails" method="get">
      <c:forEach var="user" items="${userDetails}">
       <input type="hidden" name="accountName" value="${user.accountName}">
        <input type="hidden" name="userName" value="${user.userName}">
        <input type="hidden" name="mobileNumber" value="${user.mobileNumber}">
    <input type="submit" value = "Show My Profile">
 </c:forEach>
    </form>
    
<form action = "delete" method="post">
<input type="submit" value = "Delete Account">
</form>
<h1>${Message}</h1>
</body>
</html>