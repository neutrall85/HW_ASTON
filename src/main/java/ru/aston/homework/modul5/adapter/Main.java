package ru.aston.homework.modul5.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LegacySystem legacy = new LegacySystem();

        Adapter adapter = new Adapter(legacy);

        String result = adapter.handleRequest("example data");
        LOGGER.info(result);
    }
}
