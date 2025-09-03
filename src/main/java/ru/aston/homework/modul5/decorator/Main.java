package ru.aston.homework.modul5.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            // Создаем базовый обработчик запросов
            RequestHandler handler = new SimpleRequestHandler();

            // Последовательно оборачиваем в декораторы
            handler = new LoggingDecorator(handler);
            handler = new AuthenticationDecorator(handler);
            handler = new CompressionDecorator(handler);
            handler = new CachingDecorator(handler);

            // Тестируем обработку различных запросов
            testRequest(handler, "GET /home/stand auth_token=1123");
            testRequest(handler, "GET /home/stand");
            testRequest(handler, "GET /home/stand/auth_token=1123");
            testRequest(handler, "GET /home/step_five auth_token=123");
        } catch (Exception e) {
            log.error("Произошла ошибка при обработке запросов", e);
        }
    }

    private static void testRequest(RequestHandler handler, String request) {
        log.info("\n--- Начинаем обработку нового запроса ---");
        try {
            handler.handle(request);
        } catch (SecurityException e) {
            log.error("Ошибка безопасности при обработке запроса: {}", request, e);
        } catch (Exception e) {
            log.error("Ошибка при обработке запроса: {}", request, e);
        }
        log.info("--- Завершение обработки запроса ---\n");
    }
}
