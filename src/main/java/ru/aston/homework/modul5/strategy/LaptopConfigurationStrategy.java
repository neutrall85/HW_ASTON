package ru.aston.homework.modul5.strategy;

import java.util.Map;

public class LaptopConfigurationStrategy extends BaseConfigurationStrategy {
    @Override
    protected Map<String, String> getComponents() {
        return Map.of(
                "Процессор", "Intel i7",
                "Видеокарта", "NVIDIA GTX",
                "Оперативная память", "16GB",
                "Жесткий диск", "SSD 512GB",
                "Экран", "15.6 дюймов"
        );
    }
}
