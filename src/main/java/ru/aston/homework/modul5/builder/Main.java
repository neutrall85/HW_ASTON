package ru.aston.homework.modul5.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // Создаем пиццу пошагово
        Pizza pizza = Pizza.builder()
                .base(Base.THIN)
                .cheese(Cheese.MOZZARELLA)
                .sauce(Sauce.PESTO)
                .crust(Crust.CLASSIC)
                .size(Size.MEDIUM)
                .toppings(List.of(Toppings.PEPPERONI, Toppings.MUSHROOMS, Toppings.TOMATOES))
                .build();

        LOGGER.info("{}", pizza);
    }
}
