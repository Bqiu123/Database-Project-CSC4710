<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Registration</title></head>
<body>
	<div align="center">
		<form action="initialRequest">
			<table border="1" cellpadding="5">
				<tr>
					<th>Tree Size: </th>
					<td align="center" colspan="3">
						<input type="text" name="size" size="45"  value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Tree Height: </th>
					<td align="center" colspan="3">
						<input type="text" name="height" size="45" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Location: </th>
					<td align="center" colspan="3">
						<input type="text" name="location" size="45" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
				    <th>Distance From House: </th>
				    <td align="center" colspan="3">
				        <input type="text" name="proximityToHouse" size="45" value="" onfocus="this.value=''">
				    </td>
				</tr>
				
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Get Your Quote"/>
					</td>
				</tr>
			</table>
			<a href="activitypage.jsp" target="_self">Return to User Page</a>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>