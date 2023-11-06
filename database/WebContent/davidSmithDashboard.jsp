<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>David Smith Dashboard</title>
</head>
<body>
<center>
<h1>Welcome David Smith to your dashboard!</h1>

<h1>Quotes</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Quotes</h2></caption>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Tree Amount</th>
                <th>Tree Size</th>
                <th>Tree Height</th>
                <th>Tree Distance</th>
                <th>Tree Location</th>
            </tr> 
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.tree_amt}" /></td>
			        <td><c:out value="${users.tree_size}" /></td>
			        <td><c:out value="${users.tree_height}" /></td>
                    <td><c:out value="${users.tree_distance}" /></td>
                    <td><c:out value="${users.tree_location}" /></td>

            </c:forEach>
        </table>
	</div>
	</div>


<br> <br> <a href="login.jsp"target ="_self" > logout</a><br><br> 

</center>



</body>
</html>