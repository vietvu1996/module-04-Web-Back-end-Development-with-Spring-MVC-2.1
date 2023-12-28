<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Calculator</h1>
<br/>
<form action="submit" method="post">
    <label>
        <input type="text" name="firstOperand" placeholder="Enter a number">
    </label>

    <label>
        <input type="text" name="secondOperand" placeholder="Enter a number">
    </label>

    <label>
        <button type="submit" name="submitButton" value="Addition">Addition</button>
    </label>

    <label>
        <button type="submit" name="submitButton" value="Subtraction">Subtraction</button>
    </label>

    <label>
        <button type="submit" name="submitButton" value="Multiplication">Multiplication</button>
    </label>

    <label>
        <button type="submit" name="submitButton" value="Division">Division</button>
    </label>
</form>

<p>
    Result of the operation is: ${result}
</p>
</body>
</html>