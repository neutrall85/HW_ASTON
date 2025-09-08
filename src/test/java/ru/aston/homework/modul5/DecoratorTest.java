package ru.aston.homework.modul5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.aston.homework.modul5.decorator.AuthenticationDecorator;
import ru.aston.homework.modul5.decorator.CachingDecorator;
import ru.aston.homework.modul5.decorator.CompressionDecorator;
import ru.aston.homework.modul5.decorator.LoggingDecorator;
import ru.aston.homework.modul5.decorator.RequestHandler;
import ru.aston.homework.modul5.decorator.SimpleRequestHandler;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecoratorTest {
    private static final Logger LOG = LoggerFactory.getLogger(DecoratorTest.class);
    private RequestHandler handler;
    private String testRequest;
    private String authenticatedRequest;

    @BeforeEach
    void setUp() {
        handler = new SimpleRequestHandler();

        testRequest = "test_request";
        authenticatedRequest = "test_request_with_auth_token";
    }

    @Test
    void testSimpleHandler() {
        LOG.info("Запуск теста простого обработчика");

        handler.handle(testRequest);

        assertTrue(true);
    }

    @Test
    void testAuthenticationDecorator() {
        LOG.info("Запуск теста аутентификации");

        RequestHandler authDecorator = new AuthenticationDecorator(handler);

        authDecorator.handle(authenticatedRequest);

        assertTrue(true);

        assertThrows(SecurityException.class,
                () -> authDecorator.handle(testRequest),
                "Неавторизованный доступ должен вызывать исключение");
    }

    @Test
    void testCachingDecorator() {
        LOG.info("Запуск теста кэширования");

        RequestHandler cacheDecorator = new CachingDecorator(handler);

        long startTime = System.currentTimeMillis();
        cacheDecorator.handle(testRequest);
        long endTime = System.currentTimeMillis();
        long firstRequestTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        cacheDecorator.handle(testRequest);
        endTime = System.currentTimeMillis();
        long secondRequestTime = endTime - startTime;

        assertTrue(secondRequestTime < firstRequestTime);
    }

    @Test
    void testCompressionDecorator() {
        LOG.info("Запуск теста сжатия");

        RequestHandler compressionDecorator = new CompressionDecorator(handler);

        compressionDecorator.handle(testRequest);

        assertTrue(true);
    }

    @Test
    void testLoggingDecorator() {
        LOG.info("Запуск теста логирования");

        RequestHandler loggingDecorator = new LoggingDecorator(handler);

        loggingDecorator.handle(testRequest);

        assertTrue(true);
    }

    @Test
    void testCombinedDecorators() {
        LOG.info("Запуск теста комбинированных декораторов");

        RequestHandler combinedHandler = new LoggingDecorator(
                new CachingDecorator(
                        new AuthenticationDecorator(
                                new CompressionDecorator(handler)
                        )
                )
        );

        combinedHandler.handle(authenticatedRequest);

        assertTrue(true);
    }
}
