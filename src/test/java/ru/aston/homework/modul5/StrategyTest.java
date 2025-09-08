package ru.aston.homework.modul5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import ru.aston.homework.modul5.strategy.Device;
import ru.aston.homework.modul5.strategy.LaptopConfigurationStrategy;
import ru.aston.homework.modul5.strategy.SmartphoneConfigurationStrategy;
import ru.aston.homework.modul5.strategy.TabletConfigurationStrategy;

import java.util.Map;

class StrategyTest {
    private Device laptop;
    private Device smartphone;
    private Device tablet;

    @BeforeEach
    void setUp() {
        laptop = new Device("Ноутбук");
        smartphone = new Device("Смартфон");
        tablet = new Device("Планшет");

        laptop.setConfigurationStrategy(new LaptopConfigurationStrategy());
        smartphone.setConfigurationStrategy(new SmartphoneConfigurationStrategy());
        tablet.setConfigurationStrategy(new TabletConfigurationStrategy());
    }

    @Test
    void testLaptopConfiguration() {
        laptop.configure();
        Map<String, String> expectedComponents = Map.of(
                "Процессор", "Intel i7",
                "Видеокарта", "NVIDIA GTX",
                "Оперативная память", "16GB",
                "Жесткий диск", "SSD 512GB",
                "Экран", "15.6 дюймов"
        );
        Assertions.assertEquals(expectedComponents, laptop.getComponents());
    }

    @Test
    void testSmartphoneConfiguration() {
        smartphone.configure();
        Map<String, String> expectedComponents = Map.of(
                "Процессор", "Snapdragon 8 Gen 2",
                "Оперативная память", "12GB",
                "Хранилище", "512GB",
                "Экран", "6.7 дюймов"
        );
        Assertions.assertEquals(expectedComponents, smartphone.getComponents());
    }

    @Test
    void testTabletConfiguration() {
        tablet.configure();
        Map<String, String> expectedComponents = Map.of(
                "Процессор", "Snapdragon 888",
                "Оперативная память", "8GB",
                "Хранилище", "256GB",
                "Экран", "10.1 дюймов"
        );
        Assertions.assertEquals(expectedComponents, tablet.getComponents());
    }

    @Test
    void testAddComponent() {
        laptop.addComponent("Клавиатура", "Механическая").configure();
        Assertions.assertTrue(laptop.getComponents().containsKey("Клавиатура"));
        Assertions.assertEquals("Механическая", laptop.getComponents().get("Клавиатура"));
    }

    @Test
    void testEmptyConfiguration() {
        Device emptyDevice = new Device("Пустое устройство");
        emptyDevice.configure();
        Assertions.assertTrue(emptyDevice.getComponents().isEmpty());
    }
}
