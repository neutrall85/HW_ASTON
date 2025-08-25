package ru.aston.homework.modul3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<UserChoice, UserChoiceStrategy> choiceToStrategy = new HashMap<>();

    static {
        choiceToStrategy.put(UserChoice.WRITE, new WriteStrategy());
        choiceToStrategy.put(UserChoice.READ, new ReadStrategy());
        choiceToStrategy.put(UserChoice.DELETE_DATA, new DeleteStrategy());
        choiceToStrategy.put(UserChoice.EXIT, new ExitStrategy());
    }
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            boolean showMenu = true;

            while (running) {
                try {
                    if (showMenu) {
                        System.out.println("\nВыберите действие:");
                        System.out.println("1. Записать данные в файл (WRITE)");
                        System.out.println("2. Прочитать данные из файла (READ)");
                        System.out.println("3. Удалить файл (DELETE_DATA)");
                        System.out.println("4. Выйти (EXIT)");
                    }

                    System.out.print("Введите команду: ");
                    String input = scanner.nextLine();

                    UserChoice choice;
                    try {
                        choice = UserChoice.valueOf(input.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверный выбор. Попробуйте еще раз.");
                        continue;
                    }

                    UserChoiceStrategy strategy = choiceToStrategy.get(choice);

                    if (strategy == null) {
                        System.out.println("Неизвестная команда. Попробуйте еще раз.");
                        continue;
                    }

                    try {
                        strategy.invoke(scanner);
                        showMenu = true;
                    } catch (MyFileException e) {
                        System.out.println();
                        System.err.println("Ошибка: " + e.getMessage());
                        showMenu = false;
                    }
                } catch (Exception e) {
                    System.out.println();
                    System.err.println("Произошла ошибка: " + e.getMessage());
                    showMenu = false;
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
        } finally {
            System.out.println("Программа завершена");
        }
    }
}
