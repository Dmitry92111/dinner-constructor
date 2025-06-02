package ru.practicum.dinner;
import java.util.ArrayList;
import java.util.HashMap;

public class DinnerConstructor {

    HashMap <String, ArrayList<String>> availableDishes = new HashMap<>();

    boolean checkType(String type) {
        return availableDishes.containsKey(type);
    }


}
