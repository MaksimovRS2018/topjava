<%--
  Created by IntelliJ IDEA.
  User: maksi
  Date: 01.03.2021
  Time: 1:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<hr>
<form action="meals">
    <a href="meals.jsp">back</a>
</form>
<body>


<form action="meals">
    <% MealsUtil MealToForUser = (MealsUtil) session.getAttribute("meals");
        int idEdit = (Integer) session.getAttribute("idEdit");
        MealTo actualMealTo = MealToForUser.getMealsTo().get(idEdit);
    %>
    <p><b>DateTime:</b><br>
        <input type="datetime-local" name="datetime" value="<%=actualMealTo.getDateTime()%>" size="40">
    </p>
    <p><b>Description:</b><br>
        <input type="text" name="description" value="<%=actualMealTo.getDescription()%>" size="40">
    </p>
    <p><b>Calories:</b><br>
        <input type="number" name="calories" value="<%=actualMealTo.getCalories()%>" size="40">
    </p>

    <p><input type="submit" value="Save" name="save">
        <input type="reset" value="Cancel" name="cancel">
    </p>
</form>
</body>
</html>
