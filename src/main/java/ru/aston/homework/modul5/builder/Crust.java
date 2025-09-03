package ru.aston.homework.modul5.builder;

import lombok.Getter;

@Getter
public enum Crust {
    CLASSIC("классическим"),
    HERB("травяным");

    private final String description;

    Crust(String description) {
        this.description = description;
    }
}
