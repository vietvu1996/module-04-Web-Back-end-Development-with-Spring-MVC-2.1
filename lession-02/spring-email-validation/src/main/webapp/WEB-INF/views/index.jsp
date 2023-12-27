<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Email Validation</title>
</head>
<body>
<h1>Email Validate</h1>
<h3 style="color: red">${message}</h3>
<form action="validate" method="post">
    <label>
        <input type="text" name="email"><br>
    </label>
    <label>
        <input type="submit" value="Validate">
    </label>
</form>
</body>
</html>