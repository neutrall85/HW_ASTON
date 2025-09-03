package ru.aston.homework.modul5.builder;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@Builder
public class Pizza {

    @NotNull
    private Base base;

    @NotNull
    private Cheese cheese;

    @NotNull
    private Sauce sauce;

    @NotNull
    private Crust crust;

    @NotNull
    private Size size;

    private List<Toppings> toppings;

    @Override
    public String toString() {
        return "Пицца на " + base.getDescription() + " тесте, "
                + "с сыром " + cheese.getDescription()
                + " и соусом " + sauce.getDescription() + ", "
                + crust.getDescription() + " бортиком, "
                + size.getDescription() + " размера, "
                + "с " + String.join(", ",
                toppings.stream()
                .map(Toppings::getDescription)
                .toArray(String[]::new));
    }
}
