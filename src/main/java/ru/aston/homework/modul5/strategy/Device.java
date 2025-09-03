package ru.aston.homework.modul5.strategy;

import java.util.HashMap;
import java.util.Map;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Setter
public class Device {
    private static final Logger LOGGER = LoggerFactory.getLogger(Device.class);

    private String name;
    private ConfigurationStrategy configurationStrategy;
    private Map<String, String> components = new HashMap<>();

    public Device(String name) {
        this.name = name;
    }

    public void configure() {
        if (configurationStrategy != null) {
            configurationStrategy.configure(this);
        }
    }

    public void addComponent(String componentName, String componentValue) {
        components.put(componentName, componentValue);
    }

    public void showComponents() {
        LOGGER.info("Компоненты {}:", name);
        for (Map.Entry<String, String> entry : components.entrySet()) {
            LOGGER.info("- {}: {}", entry.getKey(), entry.getValue());
        }
    }
}
