package ru.aston.homework.modul5.builder;

import lombok.Getter;

@Getter
public enum Size {
    SMALL("малого"),
    MEDIUM("среднего"),
    LARGE("большого");

    private final String description;

    Size(String description) {
        this.description = description;
    }
}
