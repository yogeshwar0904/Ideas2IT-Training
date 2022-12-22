<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Instagram</title>
</head>
<body>

<form action = "update" method="Post">
      <lable>User Name</lable>
        <input type="text" name="userName" value="${user.userName}">
      <lable>Mobile Number </lable>
        <input type="text" name="mobileNumber" value="${user.mobileNumber}">
      <lable>password</lable>
        <input type="text" name="password" value="${user.password}">

	<button type = "submit">UPDATE</button>
</form>
    
<form action = "delete" method="post">
    <input type="submit" value = "Delete Account">
</form>
<h1>${message}</h1>
</body>
</html>