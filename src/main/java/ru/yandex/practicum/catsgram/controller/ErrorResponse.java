package ru.yandex.practicum.catsgram.controller;

import lombok.Getter;

// добавьте сюда класс ErrorResponse
@Getter
class ErrorResponse {
    // геттеры необходимы, чтобы Spring Boot мог получить значения полей
    // название ошибки
    String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

}
