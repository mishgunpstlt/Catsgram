package ru.yandex.practicum.user;

import lombok.*;

@Data
@Builder
public class User {
    // Детализированная информация о пользователе
    @NonNull
    private Details details;
    // Информация о покупках пользователя
    private PurchasesInformation purchasesInformation;

    static {
        PurchasesInformation.builder().build();
    }
}