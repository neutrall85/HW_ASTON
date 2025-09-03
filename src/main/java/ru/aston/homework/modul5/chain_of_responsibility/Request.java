package ru.aston.homework.modul5.chain_of_responsibility;

import lombok.Getter;

@Getter
public class Request {
    private final String type;
    private final String content;

    public Request(String type, String content) {
        this.type = type;
        this.content = content;
    }
}
