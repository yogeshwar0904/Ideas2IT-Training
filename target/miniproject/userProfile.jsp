<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Instagram</title>
</head>
<body>

<form action = "update" method="Post">
      <lable>User Name</lable>
        <input type="text" name="userName" value="${user.userName}" pattern = "^[a-zA-Z]{4,}" title = "Start with charecter minimum four" required/><br><br>
      <lable>Mobile Number </lable>
        <input type="text" name="mobileNumber" value="${user.mobileNumber}" pattern = "^[6-9]{1}[0-9]{9}" title = "Start  from six total ten digits" required/><br><br>
      <lable>password</lable>
        <input type="text" name="password" value="${user.password}" pattern = "^[a-zA-Z0-9]{4,}[@$&*]{1,}[0-9]{1,3}" title = "contain 7 characters '5' Letters, one Special character and number" required/><br><br>

	<button type = "submit">UPDATE</button>
</form>
    
<form action = "delete" method="post">
    <input type="submit" value = "Delete Account">
</form>
<h1>${message}</h1>
</body>
</html>