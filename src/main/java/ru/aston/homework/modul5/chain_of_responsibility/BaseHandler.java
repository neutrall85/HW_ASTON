package ru.aston.homework.modul5.chain_of_responsibility;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Setter
public class BaseHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseHandler.class);

    protected BaseHandler successor;
    protected String handledType;
    protected String handlerName;

    public BaseHandler(String handledType, String handlerName) {
        this.handledType = handledType;
        this.handlerName = handlerName;
    }

    public void handleRequest(Request request) {
        if (request.getType().equals(handledType)) {
            processRequest(request);
        } else if (successor != null) {
            successor.handleRequest(request);
        } else {
            LOGGER.info("Никто не обработал запрос: {}", request.getContent());
        }
    }

    protected void processRequest(Request request) {
        LOGGER.info("{} обработал запрос: {}", handlerName, request.getContent());
    }
}
