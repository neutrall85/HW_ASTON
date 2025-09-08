package ru.aston.homework.modul5.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Adapter implements ModernSystem {

    private final LegacySystem legacySystem;

    @Override
    public String handleRequest(String request) {
        log.debug("Handling request: {}", request);
        return legacySystem.processData(request);
    }
}
