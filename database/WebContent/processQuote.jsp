<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Initial Quote</title></head>
<body>
	<div align="center">
		<!-- Display error message if present -->
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>

        <!-- Display success message from session if present -->
        <c:if test="${not empty sessionScope.successMessage}">
            <p style="color: green;">${sessionScope.successMessage}</p>
            <% session.removeAttribute("successMessage"); // Remove after displaying %>
        </c:if>
		<form action="initialQuote">
			<table border="1" cellpadding="5">
				<tr>
					<th>Initial Price: </th>
					<td align="center" colspan="3">
						<input type="text" name="initialPrice" size="45"  value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Schedule Start: </th>
					<td align="center" colspan="3">
						<input type="text" name="scheduleStart" size="45" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Schedule End: </th>
					<td align="center" colspan="3">
						<input type="text" name="scheduleEnd" size="45" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Client ID: </th>
					<td align="center" colspan="3">
						<input type="text" name="clientID" size="45" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Tree ID: </th>
					<td align="center" colspan="3">
						<input type="text" name="treeID" size="45" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Send"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>