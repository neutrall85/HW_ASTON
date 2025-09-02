package ru.aston.homework.modul5.strategy;

import java.util.Map;

public class SmartphoneConfigurationStrategy extends BaseConfigurationStrategy {
    @Override
    protected Map<String, String> getComponents() {
        return Map.of(
                "Процессор", "Snapdragon 8 Gen 2",
                "Оперативная память", "12GB",
                "Хранилище", "512GB",
                "Экран", "6.7 дюймов"
        );
    }
}
