package ru.aston.homework.modul3;

import java.util.Scanner;

public class ReadStrategy implements UserChoiceStrategy {
    @Override
    public void invoke(Scanner scanner) {
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        try {
            FileReader reader = new FileReader(filePath);
            reader.process();
        } catch (MyFileException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
