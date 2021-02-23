package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMealsUtil {
    private static int actualCalories = 0;
    private static int actualDay = 0;
    private static boolean excess = false;

    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 2100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(14, 0), 2000);
        mealsTo.forEach(System.out::println);


    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> ListUserMealWithExcess = new ArrayList<>();
        meals.stream().filter(fil -> TimeUtil.isBetweenHalfOpen(fil.getDateTime().toLocalTime(), startTime, endTime)).forEach(e -> {

            if (actualDay != e.getDateTime().getDayOfMonth()) {
                actualDay = e.getDateTime().getDayOfMonth();
                excess = false;
                actualCalories = 0;
            }
            actualCalories = actualCalories + e.getCalories();
            if (actualCalories >= caloriesPerDay) excess = true;
            UserMealWithExcess newUser = new UserMealWithExcess(e.getDateTime(), e.getDescription(), e.getCalories(), excess);
            ListUserMealWithExcess.add(newUser);
        });
        return ListUserMealWithExcess;
    }


}
