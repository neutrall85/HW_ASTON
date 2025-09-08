package ru.aston.homework.modul5.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleRequestHandler implements RequestHandler {
    @Override
    public void handle(String request) {
        log.info("Обрабатываем запрос: {}", request);

        try {
            Thread.sleep(1000);
            log.info("Запрос обработан успешно: {}", request);
        } catch (InterruptedException e) {
            log.error("Ошибка при обработке запроса", e);
            Thread.currentThread().interrupt();
        }
    }
}
