<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result</title>
</head>
<body>
	<h3>Enter your new contact</h3>
	<form method="post" action="UpdateContact">
		<table frame="border" cellpadding="1">
				<tr>
					<td align="right">ID:</td> 
					<td align="left"><input type="text" name="id" size="25" value="<%= (String)request.getAttribute("id").toString() %>"></td>
				</tr>
				<tr>
					<td align="right">First Name*:</td>
					<td align="left"> <input type="text" name="firstName" size="25" value="<%= (String)request.getAttribute("firstName").toString() %>"></td>
				</tr>
				<tr>
					<td align="right">Last Name*:</td>
					<td align="left"> <input type="text" name="lastName" size="25" value="<%= (String)request.getAttribute("lastName").toString() %>"></td>
				</tr>
				<tr>
					<td align="right">Email*:</td>
					<td align="left"><input type="text" name="email" size="25" value="<%= (String)request.getAttribute("email").toString() %>"></td>
				</tr>
				<tr>
					<td align="right">Street*:</td> 
					<td align="left"><input type="text" name="street" size="25" value="<%= (String)request.getAttribute("street").toString() %>"></td>
				</tr>
				<tr>
					<td align="right">ZIP*:</td> 
					<td align="left"><input type="text" name="zip" size="25" value="<%= (String)request.getAttribute("zip").toString() %>"></td>
				</tr>
				<tr>
					<td align="right">City*:</td> 
					<td align="left"><input type="text" name="city" size="25" value="<%= (String)request.getAttribute("city").toString() %>"></td>
				</tr>
				<tr>
					<td align="right">Country*:</td> 
					<td align="left"><input type="text" name="country" size="25" value="<%= (String)request.getAttribute("country").toString() %>"></td>
				</tr>
				<tr>
					<td align="right">PhoneKind*: </td>
					<td align="left"><select name="phoneKind">
									<option value="MOBILE" selected="selected">MOBILE</option>
									<option value="MAISON">MAISON</option>
									<option value="TRAVAIL">TRAVAIL</option>
								</select></td>
				</tr>
				<tr>
					<td align="right">PhoneNumber*: </td>
					<td align="left"><input type="text" name="phoneNumber" size="25" value="<%= (String)request.getAttribute("phoneNumber").toString() %>"></td>
				</tr>
				<tr>
					<td align="right">Group Name*:</td> 
					<td align="left"><input type="text" name="groupName" size="25" value="<%= (String)request.getAttribute("groupName").toString() %>"></td>
				</tr>
				<tr>
					<td align="right">Siret Number:</td>
					<td align="left"> <input type="hidden" name="numSiret" size="25" value="<%= (String)request.getAttribute("numSiret").toString() %>"></td>
				</tr>
		</table>
		<input type="submit" value="Update"> <input type="reset" value="Reset" >
	</form>
	<form method="post" action="RemoveContact">
	<input type="hidden" name="id" size="25" value="<%= (String)request.getAttribute("id").toString() %>">
	<input type="submit" value="Delete"></form>
</body>
</html>