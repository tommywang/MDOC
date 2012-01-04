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
					<td align="right">First Name*:</td>
					<td align="left"> <input type="text" name="firstName" size="25" value="Toto"></td>
				</tr>
				<tr>
					<td align="right">Last Name*:</td>
					<td align="left"> <input type="text" name="lastName" size="25" value="Titi"></td>
				</tr>
				<tr>
					<td align="right">Email*:</td>
					<td align="left"><input type="text" name="email" size="25" value="toto@titi.com"></td>
				</tr>
				<tr>
					<td align="right">Street*:</td> 
					<td align="left"><input type="text" name="street" size="25" value="5, place de jussieu"></td>
				</tr>
				<tr>
					<td align="right">ZIP*:</td> 
					<td align="left"><input type="text" name="zip" size="25" value="75005"></td>
				</tr>
				<tr>
					<td align="right">City*:</td> 
					<td align="left"><input type="text" name="city" size="25" value="paris"></td>
				</tr>
				<tr>
					<td align="right">Country*:</td> 
					<td align="left"><input type="text" name="country" size="25" value="France"></td>
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
					<td align="left"><input type="text" name="phoneNumber" size="25" value="0123456789"></td>
				</tr>
				<tr><td align="left" colspan="2"><strong>Enter a known group or a new name to create a contact group:</strong></td></tr>
				<tr>
					<td align="right">Group Name*:</td> 
					<td align="left"><input type="text" name="groupName" size="25" value="AMIS"></td>
					
				</tr>
				<tr><td align="left" colspan="2"><strong>Fill this field, if you are adding a contact for a enterprise:</strong></td></tr>
				<tr>
					<td align="right">Siret Number:</td>
					<td align="left"> <input type="text" name="numSiret" size="25"></td>
					
				</tr>
		</table>
		<input type="submit" value="Submit"> <input type="reset" value="Reset" >
	</form>
</body>
</html>