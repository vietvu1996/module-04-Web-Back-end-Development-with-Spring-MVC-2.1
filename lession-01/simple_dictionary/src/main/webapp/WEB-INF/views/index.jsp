<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1>Dictionary Application</h1>
<form action="dictionary" method="post">
  <label>
    <input type="text" name="word" value="English">
  </label>
  <label>
    <input type="submit" value="submit">
  </label>
</form>
<p>Nghĩa của từ này là: ${result}</p>
</body>
</html>