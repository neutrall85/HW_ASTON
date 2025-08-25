package ru.aston.homework.modul3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Выберите действие:");
            System.out.println("1. Записать данные в файл");
            System.out.println("2. Прочитать данные из файла");
            System.out.println("3. Выход");

            System.out.print("Ваш выбор: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Введите путь к файлу: ");
                    String filePath = scanner.nextLine();
                    System.out.print("Введите текст для записи: ");
                    String text = scanner.nextLine();
                    FileProcessor writer = new FileWriter(filePath, text);
                    writer.process();
                    break;

                case 2:
                    System.out.print("Введите путь к файлу: ");
                    filePath = scanner.nextLine();
                    FileProcessor reader = new FileReader(filePath);
                    reader.process();
                    break;

                case 3:
                    System.out.println("Программа завершена");
                    System.exit(0);
                    break;

                default:
                    System.err.println("Неверный выбор. Попробуйте еще раз.");
            }

        } catch (MyFileException e) {
            System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: введите число!");
        } finally {
            scanner.close();
        }
    }
}
