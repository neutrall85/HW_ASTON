package ru.aston.homework.modul5.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingDecorator extends AbstractRequestDecorator {
    public LoggingDecorator(RequestHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle(String request) {
        long startTime = System.currentTimeMillis();

        log.info("Начало обработки запроса: {}", request);

        try {
            wrapped.handle(request);

            long duration = System.currentTimeMillis() - startTime;
            log.info("Завершение обработки запроса: {}. Время обработки: {} мс", request, duration);
        } catch (Exception e) {
            log.error("Ошибка при обработке запроса: {}", request, e);
        }
    }
}
