package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMealWithExcess {
    private final LocalDateTime dateTime;

    private  final String description;

    private  final int calories;

    private  final boolean excess;

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    @Override
    public String toString() {
        return "UserMealWithExcess{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }

//    public void setDateTime(LocalDateTime dateTime) {
//        this.dateTime = dateTime;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setCalories(int calories) {
//        this.calories = calories;
//    }
//
//    public void setExcess(boolean excess) {
//        this.excess = excess;
//    }
//
    public LocalDateTime getDateTime() {
        return dateTime;
    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public int getCalories() {
//        return calories;
//    }
//
//    public boolean isExcess() {
//        return excess;
//    }
}
