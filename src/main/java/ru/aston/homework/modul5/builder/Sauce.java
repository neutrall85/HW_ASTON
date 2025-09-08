package ru.aston.homework.modul5.builder;

import lombok.Getter;

@Getter
public enum Sauce {
    MARINARA("маринара"),
    PESTO("песто"),
    ALFREDO("альфредо");

    private final String description;

    Sauce(String description) {
        this.description = description;
    }
}
