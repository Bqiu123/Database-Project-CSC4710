<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

<center><h1>Welcome! You have been successfully logged in</h1> </center>

	<center> 
	<h4>Please include the following: how many trees(1-7), size and height of each tree, location, distance from house, and 3 pictures	 </h4>
	</center>
	
	
	
	<form action = "davidSmithDashboard.jsp" method = "post">
	<center>
	
		<table border="1" cellpadding="5">
		<tr>
			<th> Tree Amount : </th>
			<td>
			<select name= "tree_amt">
				<option value ="1"> 1  </option>
				<option value ="2"> 2  </option>
				<option value ="3"> 3  </option>
				<option value ="4"> 4  </option>
				<option value ="5"> 5  </option>
				<option value ="6"> 6  </option>
				<option value ="7"> 7  </option>
			</select>
			</td>
		</tr>
		
		<tr>
			<th> Tree Size (Diameter in feet): </th>
			<td align="center" colspan="8">
			<input type = "text" name = "tree_size" size = "5">
			</td>
		</tr>
		
		<tr>
			<th> Tree Height (In feet): </th>
			<td align="center" colspan="8">
			<input type = "text" name = "tree_height" size = "5">
			</td>
		</tr>
		
		<tr>
			<th> Distance from house (In feet): </th>
			<td align="center" colspan="8">
			<input type = "text" name = "tree_distance" size = "5">
			</td>
		</tr>
		
		<tr>
			<th> Location of tree: </th>
			<td align="center" colspan="8">
			<input type = "text" name = "tree_location" size = "5">
			</td>
		</tr>
		
		<tr>
			<td align="center" colspan="8">
			<input type = "submit" value = "Submit initial request">
			</td>
		</tr>
		</center>
		
		</table>
	</form>

  
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p> You can show all the transactions or other attributes here like balance, name of the user and others.</p>
		 </center>
	</body>
</html>