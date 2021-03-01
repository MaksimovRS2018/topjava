package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.AddOrDel;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private int idEdit;
    private int idDel;
    private int save;
    private int cancel;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession currentSession = req.getSession();
        String nameXML = req.getRequestURL().toString().split("/")[4];
        log.debug("Request received");
        getParameters(req);

        if (nameXML.equals("meals") & save == -1 & cancel == -1) {
            MealsUtil mealsUtilForUser = (MealsUtil) currentSession.getAttribute("meals");

            if (mealsUtilForUser == null) {
                mealsUtilForUser = new MealsUtil();
                mealsUtilForUser.main();
            }

            log.debug("redirect to meals");
            currentSession.setAttribute("meals", mealsUtilForUser);

        } else if (nameXML.equals("addMeal")) {
            log.debug("redirect to add meals");

            LocalDateTime dateTimeReq = parseTime(req.getParameter("datetime"));
            String descriptionReq = req.getParameter("description");
            int caloriesReq = Integer.parseInt(req.getParameter("calories"));

            MealsUtil mealsUtilForUser = (MealsUtil) currentSession.getAttribute("meals");
            List<Meal> actualListMeal = mealsUtilForUser.getMeals();
            Meal newMeal = new Meal(dateTimeReq, descriptionReq, caloriesReq);
            actualListMeal.add(newMeal);
            mealsUtilForUser.setMeals(actualListMeal);
            mealsUtilForUser.main();
            currentSession.setAttribute("meals", mealsUtilForUser);
            log.debug("{} was added to Meals",newMeal);
        } else if (nameXML.equals("editMeal")) {
            log.debug("redirect to edit meals");
            currentSession.setAttribute("idEdit", idEdit);
        } else if (nameXML.equals("meals") & save > 0) {
            MealsUtil mealsUtilForUser = (MealsUtil) currentSession.getAttribute("meals");
            List<Meal> actualListMeal = getActualList(mealsUtilForUser, AddOrDel.ADD, idEdit, req);
            mealsUtilForUser.setMeals(actualListMeal);
            mealsUtilForUser.main();
            currentSession.setAttribute("meals", mealsUtilForUser);
        } else if (nameXML.equals("deleteMeal")) {
            log.debug("redirect to delete meals");
            MealsUtil mealsUtilForUser = (MealsUtil) currentSession.getAttribute("meals");
            List<Meal> actualListMeal = getActualList(mealsUtilForUser, AddOrDel.DEL, idDel);
            mealsUtilForUser.setMeals(actualListMeal);
            mealsUtilForUser.main();
            currentSession.setAttribute("meals", mealsUtilForUser);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(nameXML + ".jsp");
        requestDispatcher.forward(req, resp);
        log.debug("Response sent");


    }

    private List<Meal> getActualList(MealsUtil mealsUtilForUser, Enum addOrDel, int id, HttpServletRequest... req) {
        List<Meal> actualListMeal = mealsUtilForUser.getMeals();
        List<MealTo> actualListMealTo = mealsUtilForUser.getMealsTo();
        MealTo chooseMealTo = actualListMealTo.get(id);
        Meal chooseMeal = null;
        for (Meal oneMeal : actualListMeal) {
            if (oneMeal.equals2(chooseMealTo)) {
                chooseMeal = oneMeal;
            }
        }
        actualListMeal.remove(chooseMeal);
        if (addOrDel.equals(AddOrDel.ADD)) {
            chooseMeal.setCalories(Integer.parseInt(req[0].getParameter("calories")));
            chooseMeal.setDescription(req[0].getParameter("description"));
            chooseMeal.setDateTime(parseTime(req[0].getParameter("datetime")));
            actualListMeal.add(chooseMeal);
            log.debug("{} was edited",chooseMeal);
        } else {
            log.debug("{} was deleted",chooseMeal);
        }
        return actualListMeal;

    }

    private void getParameters(HttpServletRequest req){
        if (req.getParameter("idEdit") != null) {
            idEdit = Integer.parseInt(req.getParameter("idEdit"));
        }
        if (req.getParameter("idDel") != null) {
            idDel = Integer.parseInt(req.getParameter("idDel"));
        }
        if (req.getParameter("save") != null) {
            save = req.getParameter("save").length();
        } else {
            save = -1;
        }
        if (req.getParameter("cancel") != null) {
            cancel = req.getParameter("cancel").length();
        } else {
            cancel = -1;
        }
    }

    private LocalDateTime parseTime(String requestTime) {

        String data = requestTime.split("T")[0];
        String time = requestTime.split("T")[1];
        int year = Integer.parseInt(data.split("-")[0]);
        int month = Integer.parseInt(data.split("-")[1]);
        int day = Integer.parseInt(data.split("-")[2]);

        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);


        return LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, minute));
    }


}
