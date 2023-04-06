package com.exchangeinformant.subscription.exception;

/**
 * Исключение, которое выбрасывается, когда запрашиваемая сущность не может быть обработана.
 */
public class UnprocessableEntityException extends RuntimeException {
    /**
     * Конструктор класса UnprocessableEntityException.
     *
     * @param message Описание ошибки.
     */
    public UnprocessableEntityException(final String message) {
        super(message);
    }
}
