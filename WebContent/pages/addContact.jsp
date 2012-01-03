<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Enter your new contact</h3>
<form method="post" action="AddContact">
First Name*: <input type="text" name="firstname" size="25">
<p></p>
Last Name*: <input type="text" name="lastname" size="25">
<p></p>
Email*: <input type="text" name="email" size="25">
<p></p>
Street*: <input type="text" name="street" size="25">
<p></p>
ZIP*: <input type="text" name="zip" size="25">
<p></p>
City*: <input type="text" name="city" size="25">
<p></p>
Country*: <input type="text" name="country" size="25">
<p></p>
PhoneKind*: <input type="text" name="phoneKind" size="25">
<p></p>
PhoneNumber*: <input type="text" name="phoneNumber" size="25">
<p></p>
Group Name*: <input type="text" name="groupName" size="25">
<p></p>
<input type="submit" value="Submit">
<input type="reset" value="Reset">
</form>
</body>
</html>