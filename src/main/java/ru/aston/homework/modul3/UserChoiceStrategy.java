package ru.aston.homework.modul3;

import java.util.Scanner;

public interface UserChoiceStrategy {
    void invoke(Scanner scanner) throws MyFileException;;
}
