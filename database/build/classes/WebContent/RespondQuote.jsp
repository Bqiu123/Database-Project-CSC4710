<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Respond to Quote</title>
    <!-- Add your CSS and JS files here -->
</head>
<body>
    <h1>Respond to Quote</h1>
    <!-- Assuming the quote details are passed to this JSP -->
    <p>Quote: [Quote Details]</p> <!-- Replace [Quote Details] with the actual quote details -->
    <form action="ControlServlet" method="post">
        <input type="hidden" name="action" value="respondToQuote">
        <input type="hidden" name="quoteId" value="[Quote ID]"> <!-- Replace [Quote ID] with the actual quote ID -->
        <input type="radio" id="agree" name="response" value="agree">
        <label for="agree">Agree</label><br>
        <input type="radio" id="negotiate" name="response" value="negotiate">
        <label for="negotiate">Negotiate</label><br>
        <input type="radio" id="reject" name="response" value="reject">
        <label for="reject">Reject</label><br>

        <div id="negotiationDetails" style="display:none;">
            New Price: <input type="text" name="newPrice"><br>
            Time Window: <input type="text" name="timeWindow"><br>
        </div>

        <input type="submit" value="Submit Response">
    </form>

    <script>
        // Simple script to toggle negotiation details
        document.getElementById('negotiate').onchange = function() {
            document.getElementById('negotiationDetails').style.display = this.checked ? 'block' : 'none';
        };
    </script>
</body>
</html>
