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
	<form method="post" action="UpdateContact">
		<table frame="border" cellpadding="1">
		
			<tr>
				<td>
				    <p></p>
				    ID: <input type="text" name="id" size="25" value="<%= (String)request.getAttribute("id").toString() %>">
					<p></p>
					First Name*: <input type="text" name="firstName" size="25" value="<%= (String)request.getAttribute("firstName") %>">
					<p></p>
					Last Name*: <input type="text" name="lastName" size="25" value="<%= (String)request.getAttribute("lastName") %>">
					<p></p>
					Email*: <input type="text" name="email" size="25" value="<%= (String)request.getAttribute("email") %>">
					<p></p>
				</td>
			</tr>
			<tr>
				<td>
					<p></p>
					Street*: <input type="text" name="street" size="25" value="<%= (String)request.getAttribute("street") %>">
					<p></p>
					ZIP*: <input type="text" name="zip" size="25" value="<%= (String)request.getAttribute("zip") %>">
					<p></p> 
					City*: <input type="text" name="city" size="25" value="<%= (String)request.getAttribute("city") %>">
					<p></p>
					Country*: <input type="text" name="country" size="25" value="<%= (String)request.getAttribute("country") %>">
				</td>
			</tr>
			<tr>
				<td>
					<p></p>
					PhoneKind*: <select name="phoneKind">
									<option value="MOBILE" selected="selected">MOBILE</option>
									<option value="MAISON">MAISON</option>
									<option value="TRAVAIL">TRAVAIL</option>
								</select>
					<p></p>
					PhoneNumber*: <input type="text" name="phoneNumber" size="25" value="<%= (String)request.getAttribute("phoneNumber") %>">
					<p></p>
				</td>
			</tr>
			<tr>
				<td>
					<p></p>
					Group Name*: <input type="text" name="groupName" size="25" value="<%= (String)request.getAttribute("groupName") %>">
					<p></p>
				</td>
			</tr>
		</table>
		<input type="submit" value="Update"> <input type="reset" value="Reset" >
	</form>
</body>
</html>