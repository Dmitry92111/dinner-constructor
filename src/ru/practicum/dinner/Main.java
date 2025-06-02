package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;
    static Random random;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);
        random = new Random();

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        boolean isDishTypeAlreadyExist = dc.checkType(dishType);
        if (isDishTypeAlreadyExist) {
            ArrayList<String> dishes = dc.availableDishes.get(dishType);
            if (dishes.contains(dishName)) {
                System.out.println("Такое блюдо уже есть в этой категории ");
            } else {
                dishes.add(dishName);
            }
        } else {
            ArrayList<String> dishes = new ArrayList<>();
            dishes.add(dishName);
            dc.availableDishes.put(dishType, dishes);
        }
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        if (dc.availableDishes.isEmpty()) {
            System.out.println("Ой, похоже что вы еще не добавили ни одного блюда, пожалуйста, вернитесь к пункту 1 ");
            return;
        }

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();
        ArrayList<String> dishTypes = new ArrayList<>();

        //реализуйте ввод типов блюд
        while (!nextItem.isEmpty()) {
            boolean isDishTypeExist = dc.checkType(nextItem);
            if (isDishTypeExist) {
                dishTypes.add(nextItem);
            } else {
                System.out.println("Такого типа блюда не существует, попробуйте снова: ");
            }
            nextItem = scanner.nextLine();
        }

        // сгенерируйте комбинации блюд и выведите на экран
        for (int i = 0; i < numberOfCombos; i++) {
            System.out.println("Комбо " + (i + 1));
            for (String type : dishTypes) {
                ArrayList<String> dishes = dc.availableDishes.get(type);
                int randomNumber = random.nextInt(dishes.size());
                String dish = dishes.get(randomNumber);
                System.out.print(dish + ", ");
            }
            System.out.println();
        }
    }
}
