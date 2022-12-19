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

<form action = "delete" method="post">
<input type="submit" value = "Delete Account">
</form>

<label>Message</label>
</body>
</html>