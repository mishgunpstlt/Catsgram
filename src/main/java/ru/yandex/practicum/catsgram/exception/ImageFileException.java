package ru.yandex.practicum.catsgram.exception;

import java.io.IOException;

public class ImageFileException extends IOException {

    public ImageFileException(String message) {
        super(message);
    }

    public ImageFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
