package ru.aston.homework.modul5.strategy;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class Device {

    private String name;
    private ConfigurationStrategy configurationStrategy;
    private Map<String, String> components = new HashMap<>();

    public Device(String name) {
        this.name = name;
    }

    public Device configure() {
        if (configurationStrategy != null) {
            configurationStrategy.configure(this);
        }
        return this;
    }

    public Device addComponent(String componentName, String componentValue) {
        components.put(componentName, componentValue);
        return this;
    }

    public void showComponents() {
        log.info("Компоненты {}:", name);
        for (Map.Entry<String, String> entry : components.entrySet()) {
            log.info("- {}: {}", entry.getKey(), entry.getValue());
        }
    }
}
