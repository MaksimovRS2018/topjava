package ru.javawebinar.topjava.util;


import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

class UserMealsUtilTest {

    final List<UserMeal> meals = Arrays.asList(
            new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 1100),
            new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    @Test
    public void test1(){
        List<UserMealWithExcess> userMealWithExcesses = UserMealsUtil.filteredByStreams(meals, LocalTime.of(12, 50), LocalTime.of(19, 50), 2500);
        Assert.assertTrue(userMealWithExcesses.size() == 2);
//        Assert.assertTrue(userMealWithExcesses.get(0).isExcess());
//        Assert.assertTrue(!userMealWithExcesses.get(1).isExcess());
    }

}