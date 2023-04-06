package com.exchangeinformant.subscription.util.enums;

/**
 * Перечисление для представления статусов.
 */
public enum Status {
    /**
     * Ожидание транзакции.
     */
    AWAITING_TRANSACTION,
    /**
     * Активен.
     */
    ACTIVE,
    /**
     * Неактивен.
     */
    INACTIVE,
    /**
     * Ошибка платежа.
     */
    PAYMENT_ERROR,
    /**
     * Ожидание деактивации.
     */
    AWAITING_DEACTIVATION,
    /**
     * Ошибка деактивации.
     */
    DEACTIVATION_ERROR,
    /**
     * Ожидание даты начала.
     */
    AWAITING_START_DATE,
    /**
     * Архивирован.
     */
    ARCHIVED
}
