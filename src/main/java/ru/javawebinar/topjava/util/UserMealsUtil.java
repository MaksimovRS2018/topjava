package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserMealsUtil {
    private static int actualCalories = 0;
    private static int actualDay = -1;
    private static boolean excess = true;

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
        List<UserMealWithExcess> ListUserMealWithExcess =
                meals.stream().filter(fil -> TimeUtil.isBetweenHalfOpen(fil.getDateTime().toLocalTime(), startTime, endTime))
                        .map(userMeal -> {
                            if (actualDay == -1) {
                                actualDay = userMeal.getDateTime().getDayOfMonth();
                            }
                            if (actualDay != userMeal.getDateTime().getDayOfMonth()) {
                                excess = true;
                                actualCalories = 0;
                            }
                            actualCalories = userMeal.getCalories() + actualCalories;
                            if (actualCalories > caloriesPerDay) excess = false;
                            return new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), excess);
                        }).collect(Collectors.toList());
        return ListUserMealWithExcess;
    }


}
