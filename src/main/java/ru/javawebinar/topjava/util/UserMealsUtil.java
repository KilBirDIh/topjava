package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000));
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        Map<LocalDate, Integer> mapOfCalories = new HashMap<>();
        for(UserMeal user: mealList)
        {
            if(!mapOfCalories.containsKey(user.getDateTime().toLocalDate()))mapOfCalories.put(user.getDateTime().toLocalDate(), user.getCalories());
            else mapOfCalories.put(user.getDateTime().toLocalDate(), mapOfCalories.get(user.getDateTime().toLocalDate()) + user.getCalories());
        }
        System.out.println(mapOfCalories);
        List<UserMealWithExceed> finalList = new ArrayList<>();
        for(UserMeal user: mealList)
        {
            boolean exceed;
            if(mapOfCalories.get(user.getDateTime().toLocalDate()) <= caloriesPerDay) exceed = true;
            else exceed = false;
            if(TimeUtil.isBetween(user.getDateTime().toLocalTime(), startTime, endTime))
            finalList.add(new UserMealWithExceed(user.getDateTime(), user.getDescription(), user.getCalories(), exceed));
        }
        return finalList;
    }
}
