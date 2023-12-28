<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>TimeZone Clock</title>
</head>
<body>
<h1>Current Local Times Around The World</h1>
<span>
    Current time in ${city}: <strong>${date}</strong>
</span>
<form id="locale" action="world-clock" method="get">
    <label> <select name="city" onchange="document.getElementById('locale').submit()">
        <option value="Asia/Ho_Chi_Minh" selected>Ho Chi Minh</option>
        <option value="Asia/Ho_Chi_Minh">Ho Chi Minh</option>
        <option value="Singapore">Singapore</option>
        <option value="Asia/Hong Kong">Hong Kong</option>
        <option value="Asia/Tokyo">Hong Kong</option>
        <option value="Asia/Seoul">Hong Kong</option>
        <option value="Europe/London">London</option>
        <option value="Europe/Madrid">Madrid</option>
        <option value="America/New York">New York</option>
        <option value="Australia/Sydney">Sydney</option>
        <option value="Argentina/Buenos_Aires">Buenos Aires</option>
    </select></label>
</form>
</body>
</html>