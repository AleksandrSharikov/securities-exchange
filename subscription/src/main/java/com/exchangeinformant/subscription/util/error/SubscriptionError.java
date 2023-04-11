package com.exchangeinformant.subscription.util.error;

/**
 * Класс, представляющий объект ошибки подписки.
 */
public class SubscriptionError {
    /**
     * Заголовок ошибки.
     */
    private String title;
    /**
     * Код состояния HTTP.
     */
    private int statusCode;
    /**
     * Сообщение об ошибке.
     */
    private String message;
    /**
     * Идентификатор запроса.
     */
    private String requestId;

    /**
     * Создает новый объект ошибки подписки.
     */
    public SubscriptionError() {
    }

    /**
     * Создает новый объект ошибки подписки.
     * @param tlt заголовок ошибки
     * @param msg сообщение об ошибке
     * @param req идентификатор запроса
     */
    public SubscriptionError(final String tlt, final String msg, final String req) {
        this.message = msg;
        this.title = tlt;
        this.requestId = req;
    }

    /**
     * Создает новый объект ошибки подписки.
     * @param tlt заголовок ошибки
     * @param stCode код состояния HTTP
     * @param msg сообщение об ошибке
     * @param req идентификатор запроса
     */
    public SubscriptionError(final String tlt, final int stCode, final String msg, final String req) {
        this.statusCode = stCode;
        this.message = msg;
        this.title = tlt;
        this.requestId = req;
    }

    /**
     * Получает код состояния HTTP.
     * @return код состояния HTTP
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Получает сообщение об ошибке.
     * @return сообщение об ошибке
     */
    public String getMessage() {
        return message;
    }

    /**
     * Получает заголовок ошибки.
     * @return заголовок ошибки
     */
    public String getTitle() {
        return title;
    }

    /**
     * Получает идентификатор запроса.
     * @return идентификатор запроса
     */
    public String getRequestId() {
        return requestId;
    }

}
