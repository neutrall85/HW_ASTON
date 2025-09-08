package ru.aston.homework.modul5.builder;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public final class Pizza {

    private final Base base;
    private final Cheese cheese;
    private final Sauce sauce;
    private final Crust crust;
    private final Size size;
    private final List<Toppings> toppings;

    private Pizza(Builder builder) {
        validate(builder);
        this.base = builder.base;
        this.cheese = builder.cheese;
        this.sauce = builder.sauce;
        this.crust = builder.crust;
        this.size = builder.size;
        this.toppings = new ArrayList<>(builder.toppings);
    }

    private void validate(Builder builder) {
        validateNotNull(builder.base, "Base");
        validateNotNull(builder.cheese, "Cheese");
        validateNotNull(builder.sauce, "Sauce");
        validateNotNull(builder.crust, "Crust");
        validateNotNull(builder.size, "Size");
    }

    private static void validateNotNull(Object value, String name) {
        if (value == null) {
            throw new IllegalArgumentException(name + " cannot be null");
        }
    }

    public static class Builder {
        private final Base base;
        private final Cheese cheese;
        private final Sauce sauce;
        private final Crust crust;
        private final Size size;
        private final List<Toppings> toppings = new ArrayList<>();

        public Builder(Base base, Cheese cheese, Sauce sauce, Crust crust, Size size) {
            this.base = Objects.requireNonNull(base);
            this.cheese = Objects.requireNonNull(cheese);
            this.sauce = Objects.requireNonNull(sauce);
            this.crust = Objects.requireNonNull(crust);
            this.size = Objects.requireNonNull(size);
        }

        public Builder addToppings(Toppings... toppingsList) {
            for (Toppings topping : toppingsList) {
                validateNotNull(topping, "Topping");
            }
            this.toppings.addAll(List.of(toppingsList));
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Пицца на %s тесте, с сыром %s и соусом %s, %s бортиком, %s размера, с %s",
                base.getDescription(),
                cheese.getDescription(),
                sauce.getDescription(),
                crust.getDescription(),
                size.getDescription(),
                String.join(", ", toppings.stream()
                        .map(Toppings::getDescription)
                        .toArray(String[]::new))
        );
    }
}
