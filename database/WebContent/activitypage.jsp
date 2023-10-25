<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

<center><h1>Welcome! You have been successfully logged in</h1> </center> <br> <br>

	<center> 
	<h4>Please include the following: how many trees(1-7), size and height of each tree, location, distance from house, and 3 pictures	 </h4>
	
	<form action = "davidSmithDashboard.jsp" method = "post">
		<textarea name = "requestQuote" id = "requestQuote" rows = "12" cols = "125"></textarea> <br> 
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