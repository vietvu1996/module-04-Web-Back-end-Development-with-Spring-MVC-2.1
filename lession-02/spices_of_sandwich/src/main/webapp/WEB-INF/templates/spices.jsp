<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/28/2023
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Spices was selected:
<c:forEach var="spice" items="${spices}">
    <p>${spice}</p>
</c:forEach>
</body>
</html>
