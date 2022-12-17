<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>register</title>
</head>
<body>
    <form action = "register" method="post">
      <label>Account Name :</label>
      <input type = "text" placeholder = "Account Name" name = "accountName"><br><br>
      <label>User Name :</label>
      <input type = "text" placeholder = "User Name" name = "userName"><br><br>
      <label>Mobile Number:</label>
      <input type = "text" placeholder = "Mobile Number" name = "mobileNumber"><br><br>
      <label>Password:</label>
      <input type = "password" placeholder = "Password" name = "password"><br><br>
      <input type = "submit" value="Create Account">
    </form>
    <h1>${Message}</h1>
</body>
</html>