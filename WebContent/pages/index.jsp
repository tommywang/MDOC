<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Hello, What's your name?</h3>

<%String erreur = (String) request.getAttribute("erreur");
      if (erreur != null) { %>
            <strong><FONT COLOR=red >Erreur : <%out.print(erreur); %></FONT></strong>
      <% } %>
      
<form method="post" action="Connection">
Name: <input type="text" name="username" size="25" value="cobra">
Password: <input type="password" name="password" size="25" value="cobra">
<p></p>
<input type="submit" value="Submit">
<input type="reset" value="Reset">
</form>
</body>
</html>