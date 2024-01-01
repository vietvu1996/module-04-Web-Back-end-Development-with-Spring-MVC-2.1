<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 1/1/2024
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>User</title>
</head>
<body>
<h1>Welcome</h1>
<h3>Account: ${user.account}</h3>
<h3>Name: ${user.name}</h3>
<h3>Email: ${user.email}</h3>
<h3>Age: ${user.age}</h3>
</body>
</html>
