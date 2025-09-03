package ru.aston.homework.modul5.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class AbstractRequestDecorator implements RequestHandler {
    protected final RequestHandler wrapped;

    protected AbstractRequestDecorator(RequestHandler wrapped) {
        this.wrapped = wrapped;
    }
}
