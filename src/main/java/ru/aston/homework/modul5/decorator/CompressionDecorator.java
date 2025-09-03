package ru.aston.homework.modul5.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompressionDecorator extends AbstractRequestDecorator {
    public CompressionDecorator(RequestHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle(String request) {
        log.info("Сжимаем ответ для запроса: {}", request);

        try {
            // Имитация сжатия
            Thread.sleep(200);

            // Вызываем следующий обработчик
            wrapped.handle(request);

            log.info("Ответ успешно сжат для запроса: {}", request);
        } catch (InterruptedException e) {
            log.error("Ошибка при сжатии ответа", e);
            Thread.currentThread().interrupt();
        }
    }
}
