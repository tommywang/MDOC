<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Welcome, what do you want?</h3>
<!-- <A HREF="addContact.jsp">Add a contact</A> -->
<!-- <p></p> -->
<!-- <A HREF="removeContact.jsp">Remove a contact</A> -->
<!-- <p></p> -->
<!-- <A HREF="searchContact.jsp">Search a contact</A> -->
<!-- <p></p> -->
<!-- <A HREF="updateContact.jsp">Update a contact</A> -->
<!-- <p></p> -->
<input type="submit" value="Add a contact" onclick="javascript:window.location ='pages/addContact.jsp';"/>
<p></p>
<input type="submit" value="Remove a contact" onclick="javascript:window.location ='pages/removeContact.jsp';"/>
<p></p>
<input type="submit" value="Search a contact" onclick="javascript:window.location ='pages/searchContact.jsp';"/>
<p></p>
<input type="submit" value="Update a contact" onclick="javascript:window.location ='pages/updateContact.jsp';"/>
<p></p>
<p></p>
<p></p>
<input type="submit" value="Se déconnecter" onclick="javascript:window.location ='pages/index.jsp';"/>
<p></p>
<!-- <form method="post" action="RemoveContactForward"> -->
<!-- <input type="submit" value="Remove a contact"> -->
<!-- </form> -->
<!-- <p></p> -->
<!-- <form method="post" action="SearchContactForward"> -->
<!-- <input type="submit" value="search a contact"> -->
<!-- </form> -->
<!-- <p></p> -->
<!-- <form method="post" action="UpdateContactForward"> -->
<!-- <input type="submit" value="update a contact"> -->
<!-- </form> -->
</body>
</html>