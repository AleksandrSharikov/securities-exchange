package com.exchangeinformant.subscription.exception;

/**
 * Исключение, которое выбрасывается, когда запрашиваемый ресурс не найден.
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Конструктор класса ResourceNotFoundException.
     *
     * @param message описание ошибки
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
