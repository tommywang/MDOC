<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="UpdateContact">
<h3>Enter the name of the contact</h3>
Name: <input type="text" name="username" size="25">
<p></p>
<h3>Enter the new data of your contact</h3>
Name: <input type="text" name="username" size="25">
<p></p>
Password: <input type="password" name="password" size="25">
<p></p>
Confirm Password: <input type="password" name="passwordConfirmation" size="25">
<p></p>
Email: <input type="text" name="email" size="25">
<p></p>
<input type="submit" value="Submit">
<input type="reset" value="Reset">
</form>
</body>
</html>