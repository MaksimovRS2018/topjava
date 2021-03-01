<%--
  Created by IntelliJ IDEA.
  User: maksi
  Date: 28.02.2021
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date,
                 ru.javawebinar.topjava.model.MealTo,
                 ru.javawebinar.topjava.Color,
                 java.util.List" %>
<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<a href="addMeal.jsp">add meal</a>

<table border="1">
    <caption>Table</caption>

    <tr align="center">
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Update</th>
        <th>Delete</th>
        <%--        <th><form action="editMeal"><input type="submit" name="update" value="Update"></form></th>--%>
        <%--        <th><form action="deleteMeal"><input type="submit" name="deleteBut" value="Delete"></form></th>--%>
    </tr>
    <% MealsUtil MealToForUser = (MealsUtil) session.getAttribute("meals");
        List<MealTo> listMealToForUser = MealToForUser.getMealsTo();
        for (int i = 0; i < listMealToForUser.size(); i++) {
            MealTo oneMealTo = listMealToForUser.get(i);
            out.println(
                    "<tr align=\"center\">" +
                            "<td><font  color=" + Color.valueOf(oneMealTo.isExcess() ? "green" : "red") + ">" + oneMealTo.getDateTime().toString().split("T")[0] +
                            " " + oneMealTo.getDateTime().toString().split("T")[1] + "</font></td>" +
                            "<td><font  color=" + Color.valueOf(oneMealTo.isExcess() ? "green" : "red") + ">" + oneMealTo.getDescription() + "</font></td>" +
                            "<td><font  color=" + Color.valueOf(oneMealTo.isExcess() ? "green" : "red") + ">" + oneMealTo.getCalories() + "</font></td>" +
                            "<td><form action=\"editMeal\">" +
                            "<input type=\"hidden\" name=\"idEdit\" value=\""+(i)+"\">"+
                            "<input type=\"submit\" value=\"Edit\">" +
                            "</form></td>" +
                            "<td><form action=\"deleteMeal\">" +
                            "<input type=\"hidden\" name=\"idDel\" value=\""+(i)+"\">"+
                            "<input type=\"submit\" value=\"delete\">" +
                            "</form></td>"+
                    "</tr>");
        }
    %>

</table>

</body>
</html>
