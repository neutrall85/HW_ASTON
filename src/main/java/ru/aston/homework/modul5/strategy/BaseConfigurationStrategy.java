package ru.aston.homework.modul5.strategy;

import java.util.Map;

abstract class BaseConfigurationStrategy implements ConfigurationStrategy {
    protected abstract Map<String, String> getComponents();

    @Override
    public void configure(Device device) {
        Map<String, String> components = getComponents();
        for (Map.Entry<String, String> entry : components.entrySet()) {
            device.addComponent(entry.getKey(), entry.getValue());
        }
    }
}
