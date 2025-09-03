package ru.aston.homework.modul5.chain_of_responsibility;

public class HandlerFactory {

    private HandlerFactory() {
        throw new UnsupportedOperationException("Cannot instantiate utility class");
    }

    public static BaseHandler createHandler(String type, String name) {
        return new BaseHandler(type, name);
    }
}
