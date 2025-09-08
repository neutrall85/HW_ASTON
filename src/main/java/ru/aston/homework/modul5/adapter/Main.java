package ru.aston.homework.modul5.adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        LegacySystem legacy = new LegacySystem();

        Adapter adapter = new Adapter(legacy);

        String result = adapter.handleRequest("example data");
        log.info(result);
    }
}
