<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

<center><h1>Welcome! You have been successfully logged in</h1> </center> <br> <br> <br>

	<center> 
	<form action = "submitQuote" method = "post">
		
		Initial Quote: <input type : "text" name = "initialQuoteText">
		<input type = "submit" value = "Submit Initial Quote">
		
		</center>
	</form>

 
	<body>
	 <center>
	 <br> <br> <br> <br> <br> 
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p> You can show all the transactions or other attributes here like balance, name of the user and others.</p>
		 </center>
	</body>
</html>