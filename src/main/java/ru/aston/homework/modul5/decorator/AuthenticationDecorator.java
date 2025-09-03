package ru.aston.homework.modul5.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationDecorator extends AbstractRequestDecorator {
    public AuthenticationDecorator(RequestHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle(String request) {
        if (isAuthenticated(request)) {
            wrapped.handle(request);
        } else {
            log.warn("Неудачная попытка аутентификации для запроса: {}", request);
            throw new SecurityException("Неавторизованный доступ");
        }
    }

    private boolean isAuthenticated(String request) {
        return request.contains("auth_token");
    }
}
