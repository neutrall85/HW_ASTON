package ru.aston.homework.modul3;

import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

class DeleteStrategy implements UserChoiceStrategy {
    @Override
    public void invoke(Scanner scanner) {
        System.out.print("Введите путь к файлу для удаления: ");
        String filePath = scanner.nextLine();

        try {
            Files.delete(Paths.get(filePath));
            System.out.println("Файл успешно удален");
        } catch (IOException e) {
            System.err.println("Ошибка при удалении файла: " + e.getMessage());
        }
    }
}
