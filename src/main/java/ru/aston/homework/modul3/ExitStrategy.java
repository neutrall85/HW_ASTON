package ru.aston.homework.modul3;

import java.util.Scanner;
import java.util.logging.Logger;

class ExitStrategy implements UserChoiceStrategy {
    final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void invoke(Scanner scanner) {
        logger.info("Программа завершена");
        System.exit(0);
    }
}
