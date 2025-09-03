package ru.aston.homework.modul5.builder;

import lombok.Getter;

@Getter
public enum Cheese {
    MOZZARELLA("Моцарелла"),
    PARMESAN("Пармезан");

    private final String description;

    Cheese(String description) {
        this.description = description;
    }
}
