<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 1/1/2024
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
    <style>
        form, h3 {
            width: 50%;
            text-align: center;
            margin: auto;
            justify-content: center;
        }
    </style>
</head>
<body>
<h3>Home</h3>
<%--@elvariable id="login" type="com.codegym.controller"--%>
<form:form action="login" method="post" modelAttribute="login">
    <fieldset>
        <legend>
            Login
        </legend>
        <table>
            <tr>
                <td><form:label path="account">Account: </form:label></td>
                <td><form:input path="account"/></td>
            </tr>

            <tr>
                <td><form:label path="password">Password: </form:label></td>
                <td><form:input path="password"/></td>
            </tr>

            <tr>
                <td></td>
                <td><form:button>Login</form:button></td>
            </tr>
        </table>
    </fieldset>
</form:form>
</body>
</html>
