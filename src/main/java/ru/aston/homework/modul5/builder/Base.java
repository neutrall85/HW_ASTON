package ru.aston.homework.modul5.builder;

import lombok.Getter;

@Getter
public enum Base {
    THIN("тонком"),
    THICK("толстом");

    private final String description;

    Base(String description) {
        this.description = description;
    }
}
