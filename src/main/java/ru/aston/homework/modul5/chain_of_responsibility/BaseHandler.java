package ru.aston.homework.modul5.chain_of_responsibility;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class BaseHandler {

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
            log.info("Никто не обработал запрос: {}", request.getContent());
        }
    }

    protected void processRequest(Request request) {
        log.info("{} обработал запрос: {}", handlerName, request.getContent());
    }
}
