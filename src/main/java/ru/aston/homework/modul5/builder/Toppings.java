package ru.aston.homework.modul5.builder;

import lombok.Getter;

@Getter
public enum Toppings {
    PEPPERONI("пепперони"),
    MUSHROOMS("грибами"),
    OLIVES("оливками"),
    TOMATOES("помидорами"),
    ONIONS("луком");

    private final String description;

    Toppings(String description) {
        this.description = description;
    }
}
