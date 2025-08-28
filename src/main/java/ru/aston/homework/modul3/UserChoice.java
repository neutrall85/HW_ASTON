package ru.aston.homework.modul3;

public enum UserChoice {
    WRITE(1),
    READ(2),
    DELETE_DATA(3),
    EXIT(4);

    private final int val;

    UserChoice(int val) {
        this.val = val;
    }

    public int getValue() {
        return val;
    }

    public static UserChoice fromValue(int value) {
        for (UserChoice choice : UserChoice.values()) {
            if (choice.val == value) {
                return choice;
            }
        }
        throw new IllegalArgumentException("Неверное значение: " + value);
    }
}
