package ru.aston.homework.modul5.chain_of_responsibility;

public class Main {
    public static void main(String[] args) {

        BaseHandler handlerA = HandlerFactory.createHandler("typeA", "HandlerA");
        BaseHandler handlerB = HandlerFactory.createHandler("typeB", "HandlerB");
        BaseHandler handlerC = HandlerFactory.createHandler("typeC", "HandlerC");
        BaseHandler handlerD = HandlerFactory.createHandler("typeD", "HandlerD");
        BaseHandler handlerE = HandlerFactory.createHandler("typeE", "HandlerE");
        BaseHandler handlerF = HandlerFactory.createHandler("typeF", "HandlerF");

        handlerA.setSuccessor(handlerB);
        handlerB.setSuccessor(handlerC);
        handlerC.setSuccessor(handlerD);
        handlerD.setSuccessor(handlerE);
        handlerE.setSuccessor(handlerF);

        handlerA.handleRequest(new Request("typeA", "Запрос А"));
        handlerA.handleRequest(new Request("typeC", "Запрос С"));
        handlerA.handleRequest(new Request("typeF", "Запрос F"));
        handlerA.handleRequest(new Request("typeX", "Неизвестный тип"));
    }
}
