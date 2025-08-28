package ru.aston.homework.modul3;

import java.util.Scanner;
import java.util.logging.Logger;

public class WriteStrategy implements UserChoiceStrategy {
    final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void invoke(Scanner scanner) {
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();
        System.out.print("Введите текст для записи: ");
        String text = scanner.nextLine();

        try {
            FileWriter writer = new FileWriter(filePath, text);
            writer.process();
        } catch (MyFileException e) {
            logger.info("Ошибка: " + e.getMessage());
        }
    }
}
