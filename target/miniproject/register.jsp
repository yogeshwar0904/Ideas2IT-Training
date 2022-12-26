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
      <input type = "text" placeholder = "Account Name" name = "accountName" pattern = "^[A-Za-z][A-Za-z0-9_]{5,29}$" title = "Start with minimum '5' char,Continue with numbers and underscore" required/><br><br>
      <label>User Name :</label>
      <input type = "text" placeholder = "User Name" name = "userName" pattern = "^[a-zA-Z]{4,}" title = "Start with charecter minimum four" required/><br><br>
      <label>Mobile Number:</label>
      <input type = "text" placeholder = "Mobile Number" name = "mobileNumber" pattern = "^[6-9]{1}[0-9]{9}" title = "Start  from six total ten digits" required/><br><br>
      <label>Password:</label>
      <input type = "password" placeholder = "Password" name = "password" pattern = "^[a-zA-Z0-9]{4,}[@$&*]{1,}[0-9]{1,3}" title = "contain 7 characters '5' Letters, one Special character and number" required/><br><br>
      <input type = "submit" value="Create Account">
    </form>
    <h1>${message}</h1>
</body>
</html>