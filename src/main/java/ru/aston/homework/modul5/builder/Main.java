package ru.aston.homework.modul5.builder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Pizza pizza = new Pizza.Builder(
                Base.THIN,
                Cheese.MOZZARELLA,
                Sauce.PESTO,
                Crust.CLASSIC,
                Size.MEDIUM)
                .addToppings(Toppings.PEPPERONI, Toppings.MUSHROOMS, Toppings.TOMATOES)
                .build();

        log.info("{}", pizza);
    }
}
