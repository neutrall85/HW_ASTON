package ru.aston.homework.modul3;

import java.util.Scanner;

class ExitStrategy implements UserChoiceStrategy {
    @Override
    public void invoke(Scanner scanner) {
        System.out.println("Программа завершена");
        System.exit(0);
    }
}
