package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class MealsUtil {
    private List<MealTo> mealsTo = new ArrayList<>();;
    private static int startTime = 0;
    private static int endTime = 23;
    private static int caloriesPerDay = 2000;
    private List<Meal> meals = new ArrayList<>();

    public MealsUtil() {
        meals = new ArrayList<>(Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 28, 10, 0), "Завтрак", 300),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 28, 13, 0), "Обед", 1300),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 28, 20, 0), "Ужин", 200),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 29, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 29, 13, 0), "Обед", 300),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 29, 20, 0), "Ужин", 710)
        ));


//        meals.add(new Meal(LocalDateTime.of(2022, Month.JANUARY, 25, 10, 0), "Завтрак", 250));
    }

    public void main() {
        mealsTo = filteredByStreams(meals, LocalTime.of(startTime, 0), LocalTime.of(endTime, 0), caloriesPerDay);
    }


    public static List<MealTo> filteredByStreams(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(new ToIntFunction<Meal>() {
                            @Override
                            public int applyAsInt(Meal value) {
                                return value.getCalories();
                            }
                        }))
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public List<MealTo> getMealsTo() {
        return mealsTo;
    }

    public static void setStartTime(int startTime) {
        MealsUtil.startTime = startTime;
    }

    public static void setEndTime(int endTime) {
        MealsUtil.endTime = endTime;
    }

    public static void setCaloriesPerDay(int caloriesPerDay) {
        MealsUtil.caloriesPerDay = caloriesPerDay;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

}
