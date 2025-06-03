package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {

    static Random random = new Random();
    HashMap<String, ArrayList<String>> availableDishes = new HashMap<>();

    boolean checkType(String type) {
        return availableDishes.containsKey(type);
    }

    boolean checkDish(String dishName, ArrayList<String> dishes) {
        return dishes.contains(dishName);
    }

    void saveNewDish(String dishType, String dishName) {
        boolean isDishTypeAlreadyExist = checkType(dishType);

        if (isDishTypeAlreadyExist) {
            ArrayList<String> dishes = availableDishes.get(dishType);
            boolean isDishAlreadyExist = checkDish(dishName, dishes);

            if (isDishAlreadyExist) {
                System.out.println("Такое блюдо уже есть в этой категории ");
            } else {
                dishes.add(dishName);
            }

        } else {
            ArrayList<String> dishes = new ArrayList<>();
            dishes.add(dishName);
            availableDishes.put(dishType, dishes);
        }
    }

    void generateComboMenu(int numberOfCombos, ArrayList<String> dishTypes) {
        for (int i = 0; i < numberOfCombos; i++) {
            System.out.println("Комбо " + (i + 1));
            for (int j = 0; j < dishTypes.size(); j++) {
                String type = dishTypes.get(j);
                ArrayList<String> dishes = availableDishes.get(type);
                int randomNumber = random.nextInt(dishes.size());
                String dish = dishes.get(randomNumber);
                System.out.print(dish);

                if (j < dishes.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
