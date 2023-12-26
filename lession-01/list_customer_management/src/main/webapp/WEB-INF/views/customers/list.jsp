<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/26/2023
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer List</title>
</head>
<style>

</style>
<body>
<h1>Customer List</h1>
There are ${requestScope.customers.size()} in this list;
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>
                    ${customer.id}
            </td>
            <td>
                <a href="info.jsp?id=${customer.id}">${customer.name}</a>
            </td>
            <td>
                    ${customer.email}
            </td>
            <td>
                    ${customer.address}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
