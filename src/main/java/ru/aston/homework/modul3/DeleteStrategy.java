package ru.aston.homework.modul3;

import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.logging.Logger;

class DeleteStrategy implements UserChoiceStrategy {
    final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void invoke(Scanner scanner) {
        System.out.println("Введите путь к файлу для удаления: ");
        String filePath = scanner.nextLine();

        try {
            Files.delete(Paths.get(filePath));
            logger.info("Файл успешно удален");
        } catch (IOException e) {
            logger.info("Ошибка при удалении файла: " + e.getMessage());
        }
    }
}
