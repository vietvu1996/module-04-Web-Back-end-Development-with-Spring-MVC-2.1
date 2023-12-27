<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/27/2023
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Email Validate</h1>
<h3 style="color: red">${message}</h3>
<form action="validate" method="post">
    <label>
        <input type="text" name="email"><br>
    </label>
    <label>
        <input type="submit" value="validate">
    </label>
</form>
</body>
</html>
