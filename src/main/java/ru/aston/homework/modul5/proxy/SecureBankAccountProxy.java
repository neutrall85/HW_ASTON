package ru.aston.homework.modul5.proxy;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class SecureBankAccountProxy extends BaseBankAccountProxy {
    private final String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(SecureBankAccountProxy.class);

    @Override
    protected void checkAccess() {
        if (password.equals("secret")) {
            LOGGER.info("Аутентификация успешна");
        } else {
            LOGGER.error("Ошибка аутентификации. Доступ запрещен");
            throw new SecurityException("Ошибка аутентификации. Доступ запрещен");
        }
    }
}
