package ru.yandex.practicum.user;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

// Детализированная информация о пользователе
@Data
@Builder
public class Details {
    // Электронная почта
    @NonNull
    private String email;
    // Имя
    @NonNull
    private String firstName;
    // Фамилия
    @NonNull
    private String lastName;
    // Дополнительная информация
    private String information;
    // Дата рождения
    private Date dayOfBirthday;
    // Пол
    @Builder.Default
    private Gender gender = Gender.UNKNOWN;
}