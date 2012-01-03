<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Contact</title>
</head>
<body>
<h3>Enter the contact you are looking for</h3>
<form method="post" action="SearchContact">
First Name: <input type="text" name="firstName" size="25">
<p></p>
Last Name: <input type="text" name="lastName" size="25">
<p></p>
<input type="submit" value="Submit">
<input type="reset" value="Reset">
</form>
</body>
</html>