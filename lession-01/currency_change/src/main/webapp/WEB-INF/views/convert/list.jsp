<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/26/2023
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Currency Converter</title>
</head>
<body>
<h1>Currency Converter</h1>
<br>
<form action="${pageContext.request.contextPath}/convert" method="post">
    <table>
        <tr>
            <th>USD</th>
            <td>
                <label>
                    <input type="text" name="usd">
                </label>
            </td>
        </tr>
        <tr>
            <th>Currency</th>
            <td>
                <label>
                    <input type="text" name="currency">
                </label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <label>
                    <input type="submit" value="convert">
                </label>
            </td>
        </tr>
    </table>
</form>
<p>Result: ${result}</p>
</body>
</html>
