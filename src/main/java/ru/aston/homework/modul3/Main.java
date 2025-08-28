package ru.aston.homework.modul3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static Map<UserChoice, UserChoiceStrategy> choiceToStrategy = new HashMap<>();
    private static final Logger logger = Logger.getLogger(Main.class.getName());

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
                        System.out.println("1. Записать данные в файл (WRITE(1))");
                        System.out.println("2. Прочитать данные из файла (READ(2))");
                        System.out.println("3. Удалить файл (DELETE_DATA(3))");
                        System.out.println("4. Выйти (EXIT(4))");
                    }

                    System.out.print("Введите команду: ");
                    String input = scanner.nextLine();

                    UserChoice choice;
                    try {
                        int inputValue = Integer.parseInt(input);
                        choice = UserChoice.fromValue(inputValue);
                    } catch (NumberFormatException e) {
                        logger.info("Неверный выбор. Попробуйте еще раз.");
                        continue;
                    }

                    UserChoiceStrategy strategy = choiceToStrategy.get(choice);

                    if (strategy == null) {
                        logger.info("Неизвестная команда. Попробуйте еще раз.");
                        continue;
                    }

                    try {
                        strategy.invoke(scanner);
                        showMenu = true;
                    } catch (MyFileException e) {
                        System.out.println();
                        logger.info("Ошибка: " + e.getMessage());
                        showMenu = false;
                    }
                } catch (Exception e) {
                    System.out.println();
                    logger.info("Произошла ошибка: " + e.getMessage());
                    showMenu = false;
                }
            }
        } catch (Exception e) {
            logger.info("Ошибка ввода: " + e.getMessage());
        } finally {
            logger.info("Программа завершена");
        }
    }
}
