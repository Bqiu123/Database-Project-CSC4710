<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>David Smith DashBoard</title>
</head>
<body>

<div align = "center">
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

	
<h1>List all trees</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Trees</h2></caption>
            <tr>
                <th>Tree ID</th>
                <th>Size</th>
                <th>Height</th>
                <th>Location</th>
                <th>Distance to House</th>
                <th>Client ID</th>
            </tr>
            <c:forEach var="tree" items="${listTreeWithoutQuote}">
                <tr style="text-align:center">
                    <td><c:out value="${tree.treeID}" /></td>
                    <td><c:out value="${tree.size}" /></td>
                    <td><c:out value="${tree.height}" /></td>
                    <td><c:out value="${tree.location}" /></td>
                    <td><c:out value="${tree.proximityToHouse}" /></td>
                    <td><c:out value="${tree.clientID}" /></td>
                    <td><button type="button" onclick="showQuoteForm('${tree.treeID}')">Send Quote</button></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="quoteForm" style="display:none;">
    <form action="" method="">
        <input type="hidden" name="treeID" id="treeID" value="">
        Initial Price: <input type="text" name="initialPrice"><br>
        Time Window:<input type="text" name="timeWindow"><br>
        <input type="submit" value="Submit Quote">
    </form>
</div>
<script>
function showQuoteForm(treeID) {
    document.getElementById('treeID').value = treeID;
    document.getElementById('quoteForm').style.display = 'block';
}
</script>
	</div>

</body>
</html>