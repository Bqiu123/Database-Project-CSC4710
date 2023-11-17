<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Respond to Quote</title>
</head>
<body>
    <div align="center">
        <h1>Respond to Quote</h1>
        <form action="updateQuote">
            <input type="hidden" name="quoteID" value="${param.quoteID}" />
            <label for="price">New Price (leave blank to keep current):</label>
            <input type="text" name="price" id="price" /><br><br>
            <label for="timeWindow">New Time Window (leave blank to keep current):</label>
            <input type="text" name="timeWindow" id="timeWindow" /><br><br>
            <button type="submit">Update Quote</button>
        </form>
    </div>
</body>
</html>
