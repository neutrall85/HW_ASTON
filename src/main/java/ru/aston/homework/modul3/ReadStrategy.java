package ru.aston.homework.modul3;

import java.util.Scanner;
import java.util.logging.Logger;

public class ReadStrategy implements UserChoiceStrategy {
    final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void invoke(Scanner scanner) {
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        try {
            FileReader reader = new FileReader(filePath);
            reader.process();
        } catch (MyFileException e) {
            logger.info("Ошибка: " + e.getMessage());
        }
    }
}
