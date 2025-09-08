package ru.aston.homework.modul5.proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SecureBankAccountProxy extends BaseBankAccountProxy {
    private final String password;

    @Override
    protected void checkAccess() {
        if (password.equals("secret")) {
            log.info("Аутентификация успешна");
        } else {
            log.error("Ошибка аутентификации. Доступ запрещен");
            throw new SecurityException("Ошибка аутентификации. Доступ запрещен");
        }
    }
}
