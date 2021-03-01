<%--
  Created by IntelliJ IDEA.
  User: maksi
  Date: 01.03.2021
  Time: 1:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<hr>
<form action="meals">
    <a href="meal.jsp">back</a>
</form>
<body>
<%--<form action="editMeal">--%>
    <p><b>DateTime:</b><br>
        <input type="datetime-local" name="datetime" size="40">
    </p>
    <p><b>Description:</b><br>
        <input type="text" name="description" size="40">
    </p>
    <p><b>Calories:</b><br>
        <input type="text" name="calories" size="40">
    </p>
    <p><input type="submit" value="Save">
        <input type="reset" value="Cancel"></p>
<%--</form>--%>
</body>
</html>
