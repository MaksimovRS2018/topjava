package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession currentSession = req.getSession();
        String nameXML = req.getRequestURL().toString().split("/")[4];

        if (nameXML.equals("meals")) {
            MealsUtil mealsUtilForUser = (MealsUtil) currentSession.getAttribute("meals");

            if (mealsUtilForUser == null) {
                mealsUtilForUser = new MealsUtil();
                mealsUtilForUser.main();
            }

            log.debug("redirect to meals");

            currentSession.setAttribute("meals", mealsUtilForUser);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("meal.jsp");
            requestDispatcher.forward(req, resp);

        } else {
            log.debug("redirect to add meals");
            LocalDateTime dateTimeReq = parseTime(req.getParameter("datetime"));
            String descriptionReq = (String) req.getParameter("description");
            int caloriesReq = Integer.parseInt(req.getParameter("calories"));
            MealsUtil mealsUtilForUser = (MealsUtil) currentSession.getAttribute("meals");
            List<Meal> actualListMeal = mealsUtilForUser.getMeals();
            actualListMeal.add(new Meal(dateTimeReq,descriptionReq,caloriesReq));
            mealsUtilForUser.setMeals(actualListMeal);
            mealsUtilForUser.main();
            currentSession.setAttribute("meals", mealsUtilForUser);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("addMeal.jsp");
            requestDispatcher.forward(req, resp);

        }



    }


//        int startTime = Integer.parseInt(req.getParameter("startT"));
//        int endTime = Integer.parseInt(req.getParameter("endT"));
//        int colPerDay = Integer.parseInt(req.getParameter("c"));

//        MealsUtil.setStartTime(startTime);
//        MealsUtil.setEndTime(endTime);
//        MealsUtil.setCaloriesPerDay(colPerDay);




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameXML = req.getRequestURL().toString().split("/")[4];
    }

    private LocalDateTime parseTime(String requestTime){

        String data = requestTime.split("T")[0];
        String time = requestTime.split("T")[1];
        int year = Integer.parseInt(data.split("-")[0]);
        int month = Integer.parseInt(data.split("-")[1]);
        int day = Integer.parseInt(data.split("-")[2]);

        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);


        return LocalDateTime.of(LocalDate.of(year,month,day), LocalTime.of(hour,minute));
    }
}
