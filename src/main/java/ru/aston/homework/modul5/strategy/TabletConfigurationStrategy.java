package ru.aston.homework.modul5.strategy;

import java.util.Map;

public class TabletConfigurationStrategy extends BaseConfigurationStrategy {
    @Override
    protected Map<String, String> getComponents() {
        return Map.of(
                "Процессор", "Snapdragon 888",
                "Оперативная память", "8GB",
                "Хранилище", "256GB",
                "Экран", "10.1 дюймов"
        );
    }
}
