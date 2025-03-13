package ru.yandex.practicum.user;

import lombok.Builder;

import lombok.Value;

import java.util.Date;

// Информация о покупках пользователя
@Value
@Builder
public class PurchasesInformation {
    // дата последней покупки
    Date lastPurchase;
    // Общее количество покупок
    long purchaseCounts = 0;
}