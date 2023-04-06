package com.exchangeinformant.subscription.exception;

/**
 * Исключение, которое выбрасывается, когда на серверной стороне возникает ошибка.
 */
public class ServerErrorException extends RuntimeException {
    /**
     * Конструктор класса ServerErrorException.
     *
     * @param message описание ошибки
     */
    public ServerErrorException(final String message) {
        super(message);
    }
}
