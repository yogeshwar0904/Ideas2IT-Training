<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title>Login</title>
</head>
<body>
  <form action = "login" method = "post">
        <input type = "text" placeholder = "Account Name" name = "accountName">
        <input type="password" placeholder="Password" name = "password">
      <a href="register.jsp">Create New Account?</a>
      <h1>${Message}</h1>
      <button>Sign in</button>
  </form>
</body>
</html>