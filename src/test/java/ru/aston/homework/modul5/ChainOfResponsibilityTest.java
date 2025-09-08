package ru.aston.homework.modul5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.homework.modul5.chain_of_responsibility.BaseHandler;
import ru.aston.homework.modul5.chain_of_responsibility.HandlerFactory;
import ru.aston.homework.modul5.chain_of_responsibility.Request;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChainOfResponsibilityTest {

    private BaseHandler handler1;
    private BaseHandler handler2;
    private BaseHandler handler3;

    @BeforeEach
    void setUp() {
        handler1 = HandlerFactory.createHandler("type1", "Handler 1");
        handler2 = HandlerFactory.createHandler("type2", "Handler 2");
        handler3 = HandlerFactory.createHandler("type3", "Handler 3");

        handler1.setSuccessor(handler2);
        handler2.setSuccessor(handler3);
    }

    @Test
    void testRequestHandling() {
        Request request1 = new Request("type1", "Содержимое запроса 1");
        Request request2 = new Request("type2", "Содержимое запроса 2");
        Request request3 = new Request("type3", "Содержимое запроса 3");
        Request request4 = new Request("unknown", "Содержимое запроса 4");

        handler1.handleRequest(request1);
        assertEquals("type1", handler1.getHandledType());

        handler1.handleRequest(request2);
        assertEquals("type2", handler2.getHandledType());

        handler1.handleRequest(request3);
        assertEquals("type3", handler3.getHandledType());

        handler1.handleRequest(request4);
        assertEquals("unknown", request4.getType());
    }
}
