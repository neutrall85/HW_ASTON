package ru.aston.homework.modul5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.aston.homework.modul5.builder.Base;
import ru.aston.homework.modul5.builder.Cheese;
import ru.aston.homework.modul5.builder.Crust;
import ru.aston.homework.modul5.builder.Pizza;
import ru.aston.homework.modul5.builder.Sauce;
import ru.aston.homework.modul5.builder.Size;
import ru.aston.homework.modul5.builder.Toppings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuilderTest {
    private Base base;
    private Cheese cheese;
    private Sauce sauce;
    private Crust crust;
    private Size size;

    @BeforeEach
    void setUp() {
        base = Base.THIN;
        cheese = Cheese.MOZZARELLA;
        sauce = Sauce.MARINARA;
        crust = Crust.CLASSIC;
        size = Size.SMALL;
    }

    @Test
    void testBuilderBasic() {
        Pizza pizza = new Pizza.Builder(base, cheese, sauce, crust, size)
                .build();

        assertEquals(base, pizza.getBase());
        assertEquals(cheese, pizza.getCheese());
        assertEquals(sauce, pizza.getSauce());
        assertEquals(crust, pizza.getCrust());
        assertEquals(size, pizza.getSize());
        assertTrue(pizza.getToppings().isEmpty());
    }

    @Test
    void testBuilderWithToppings() {
        Pizza pizza = new Pizza.Builder(base, cheese, sauce, crust, size)
                .addToppings(Toppings.PEPPERONI, Toppings.MUSHROOMS)
                .build();

        assertEquals(2, pizza.getToppings().size());
        assertTrue(pizza.getToppings().contains(Toppings.PEPPERONI));
        assertTrue(pizza.getToppings().contains(Toppings.MUSHROOMS));
    }

    @Test
    void testBuilderMultipleToppings() {
        Pizza pizza = new Pizza.Builder(base, cheese, sauce, crust, size)
                .addToppings(Toppings.PEPPERONI, Toppings.MUSHROOMS, Toppings.OLIVES)
                .addToppings(Toppings.TOMATOES, Toppings.ONIONS)
                .build();

        assertEquals(5, pizza.getToppings().size());
    }

    @Test
    void testBuilderRequiredFields() {
        Executable[] builders = {
                () -> new Pizza.Builder(null, cheese, sauce, crust, size).build(),
                () -> new Pizza.Builder(base, null, sauce, crust, size).build(),
                () -> new Pizza.Builder(base, cheese, null, crust, size).build(),
                () -> new Pizza.Builder(base, cheese, sauce, null, size).build(),
                () -> new Pizza.Builder(base, cheese, sauce, crust, null).build()
        };

        for (Executable builder : builders) {
            assertThrows(NullPointerException.class, builder);
        }
    }

    @Test
    void testBuilderToppingsValidation() {
        Pizza.Builder builder = new Pizza.Builder(base, cheese, sauce, crust, size);
        assertThrows(IllegalArgumentException.class, () -> builder.addToppings((Toppings) null));
    }

    @Test
    void testToString() {
        Pizza pizza = new Pizza.Builder(base, cheese, sauce, crust, size)
                .addToppings(Toppings.PEPPERONI, Toppings.MUSHROOMS)
                .build();

        String expected = "Пицца на тонком тесте, с сыром Моцарелла и соусом маринара, классическим бортиком, "
                + "малого размера, с пепперони, грибами";

        assertEquals(expected, pizza.toString());
    }
}
