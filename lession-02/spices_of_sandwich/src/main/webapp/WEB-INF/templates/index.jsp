<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h2>Sandwich Condiments</h2>
<form action="save" method="post">
    <label>
        <input type="checkbox" name="spices" value="Lettuce">
        Lettuce
    </label>

    <label>
        <input type="checkbox" name="spices" value="Tomato">
        Tomato
    </label>

    <label>
        <input type="checkbox" name="spices" value="Mustard">
        Mustard
    </label>

    <label>
        <input type="checkbox" name="spices" value="Sprouts">
        Sprouts
    </label>

    <hr>
    <label>
        <button type="submit">Save</button>
    </label>
</form>
</body>
</html>