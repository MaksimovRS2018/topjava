<%--
  Created by IntelliJ IDEA.
  User: maksi
  Date: 28.02.2021
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add meal</title>
</head>
<hr>
<form action="meals">
    <a href="meals.jsp">back</a>
</form>
<body>
<form action="addMeal">
    <p><b>DateTime:</b><br>
        <input type="datetime-local" name="datetime" size="40">
    </p>
    <p><b>Description:</b><br>
        <input type="text" name="description" size="40">
    </p>
    <p><b>Calories:</b><br>
        <input type="number" name="calories" size="40">
    </p>
    <p><input type="submit" value="Add"></p>
</form>
</body>
</html>
