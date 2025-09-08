package ru.aston.homework.modul5.decorator;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class CachingDecorator extends AbstractRequestDecorator {
    private final Map<String, String> cache = new ConcurrentHashMap<>();

    public CachingDecorator(RequestHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle(String request) {
        if (cache.containsKey(request)) {
            log.info("Возвращаем закэшированный ответ для запроса: {}", request);
            return;
        }

        log.info("Запрос не найден в кэше: {}", request);

        try {
            Thread.sleep(150);

            wrapped.handle(request);

            cache.put(request, "cached_response");
            log.info("Результат сохранен в кэш для запроса: {}", request);
        } catch (InterruptedException e) {
            log.error("Ошибка при работе с кэшем", e);
            Thread.currentThread().interrupt();
        }
    }
}
