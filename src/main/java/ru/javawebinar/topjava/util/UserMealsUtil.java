package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UserMealsUtil {
    private static int actualCalories = 0;
    private static int actualDay = -1;
    private static boolean excess = true;

    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 1100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(14, 0), 2000);
//        mealsTo.forEach(e -> System.out.println(e.toString()));

        List<UserMealWithExcess> userMealWithExcesses = UserMealsUtil.filteredByStreams(meals, LocalTime.of(12, 50), LocalTime.of(19, 50), 2500);
        System.out.println(userMealWithExcesses);


    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExcess> ListUserMealWithExcess = new ArrayList<>();
        meals.stream()
                //create Map <day, list description>
        .collect(Collectors.groupingBy(user -> user.getDateTime().getDayOfMonth())).forEach(new BiConsumer<Integer, List<UserMeal>>() {
            @Override
            public void accept(Integer integer, List<UserMeal> userMeals) {
                actualCalories = userMeals.stream().mapToInt(UserMeal::getCalories).sum();
                    userMeals.forEach(elementUser -> {
                        if (actualCalories > caloriesPerDay) {
                            ListUserMealWithExcess.add(new UserMealWithExcess(elementUser.getDateTime(), elementUser.getDescription(), elementUser.getCalories(), false));
                        } else {
                            ListUserMealWithExcess.add(new UserMealWithExcess(elementUser.getDateTime(), elementUser.getDescription(), elementUser.getCalories(), true));
                        }
                    });
            }
        });
//        Map<LocalDate, List<UserMealWithExcess>> collect = meals.stream()
//                .map(el -> new UserMealWithExcess(el.getDateTime(), el.getDescription(), el.getCalories(), false))
//                .collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate()));
//
//        List<UserMealWithExcess> all = new ArrayList<>();
//        for (Map.Entry<LocalDate, List<UserMealWithExcess>> localDateListEntry : collect.entrySet()) {
//            int sum = localDateListEntry.getValue().stream().mapToInt(el -> el.getCalories()).sum();
//            if (sum > caloriesPerDay)
//                localDateListEntry.getValue().forEach(el -> el.setExcess(true));
//            all.addAll(localDateListEntry.getValue());
//        }
//
//        return  all.stream().filter(el -> TimeUtil.isBetweenHalfOpen(el.getDateTime().toLocalTime(), startTime, endTime)).collect(Collectors.toList());

        return ListUserMealWithExcess.stream().filter(fil ->
                TimeUtil.isBetweenHalfOpen(fil.getDateTime().toLocalTime(), startTime, endTime)).collect(Collectors.toList());
    }

}
