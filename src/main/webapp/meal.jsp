<%--
  Created by IntelliJ IDEA.
  User: maksi
  Date: 28.02.2021
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page import="java.util.Date,
                 ru.javawebinar.topjava.model.MealTo,
                 ru.javawebinar.topjava.Color,
                 java.util.List" %>
<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<h3><a href="index.html">Home</a></h3>
<hr>
<a href="addMeal.jsp">add meal</a>

<table border="1" >
    <caption>Table</caption>

    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th><form action="editMeal"><input type="submit" name="update" value="Update"></form></th>
        <th><form action="deleteMeal"><input type="submit" name="deleteBut" value="Delete"></form></th>
    </tr>
    <% MealsUtil MealToForUser = (MealsUtil) session.getAttribute("meals");
        List<MealTo> listMealToForUser = MealToForUser.getMealsTo();
        for (int i = 0; i < listMealToForUser.size(); i++) {
            MealTo oneMealTo = listMealToForUser.get(i);
            out.println(
                    "<tr>" +
                            "<td><font  color=" + Color.valueOf(oneMealTo.isExcess() ? "green" : "red") + ">" + oneMealTo.getDateTime().toString().split("T")[0] +
                            " " + oneMealTo.getDateTime().toString().split("T")[1] + "</font></td>" +
                            "<td><font  color=" + Color.valueOf(oneMealTo.isExcess() ? "green" : "red") + ">" + oneMealTo.getDescription() + "</font></td>" +
                            "<td><font  color=" + Color.valueOf(oneMealTo.isExcess() ? "green" : "red") + ">" + oneMealTo.getCalories() + "</font></td>" +
                            "<td><input type=\"radio\" name=\"edit\" value=edit\"" + (i + 1) + "\">" + (i + 1) + "</td>" +
                            "<td><input type=\"radio\" name=\"delete\" value=delete\"" + (i + 1) + "\">" + (i + 1) + "</td>" +
                            "</tr>");
        }
    %>

</table>

</body>
</html>
