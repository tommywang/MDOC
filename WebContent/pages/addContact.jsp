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
		<table frame="border" cellpadding="1">
			<tr>
				<td>
					<p></p>
					First Name*: <input type="text" name="firstName" size="25" value="Toto">
					<p></p>
					Last Name*: <input type="text" name="lastName" size="25" value="Titi">
					<p></p>
					Email*: <input type="text" name="email" size="25" value="toto@titi.com">
					<p></p>
				</td>
			</tr>
			<tr>
				<td>
					<p></p>
					Street*: <input type="text" name="street" size="25" value="5, place de jussieu">
					<p></p>
					ZIP*: <input type="text" name="zip" size="25" value="75005">
					<p></p> 
					City*: <input type="text" name="city" size="25" value="paris">
					<p></p>
					Country*: <input type="text" name="country" size="25" value="France">
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
					PhoneNumber*: <input type="text" name="phoneNumber" size="25" value="0123456789">
					<p></p>
				</td>
			</tr>
			<tr>
				<td>
					<p></p>
					Group Name*: <input type="text" name="groupName" size="25" value="AMIS">
					<p></p>
				</td>
			</tr>
		</table>
		<input type="submit" value="Submit"> <input type="reset" value="Reset" >
	</form>
</body>
</html>