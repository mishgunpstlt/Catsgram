package ru.yandex.practicum.user;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class User {
    static {
        PurchasesInformation.builder().build();
    }

    // Детализированная информация о пользователе
    @NonNull
    private Details details;
    // Информация о покупках пользователя
    private PurchasesInformation purchasesInformation;
}