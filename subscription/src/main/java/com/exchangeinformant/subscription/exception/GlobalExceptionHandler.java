package com.exchangeinformant.subscription.exception;

import com.exchangeinformant.subscription.util.error.SubscriptionError;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс-обработчик исключений на уровне контроллера.
 * Используется для обработки исключительных ситуаций, возникающих в ходе выполнения запросов.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * Обрабатывает исключение MethodArgumentNotValidException.
     * @param ex Исключение MethodArgumentNotValidException
     * @return Map с описанием ошибки
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(final MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    /**
     * Обрабатывает исключение ResourceNotFoundException.
     * @param e Исключение ResourceNotFoundException.
     * @param request Объект запроса.
     * @return Объект ResponseEntity с описанием ошибки.
     */
    @ExceptionHandler()
    public ResponseEntity<SubscriptionError> catchResourceNotFoundException(
            final ResourceNotFoundException e,
            final WebRequest request) {
        SubscriptionError message = new SubscriptionError(
                "Подписка не найдена",
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    /**
     * Обрабатывает исключение EmptyResultDataAccessException.
     * @param e Исключение EmptyResultDataAccessException.
     * @param request Объект запроса.
     * @return Объект ResponseEntity с описанием ошибки.
     */
    @ExceptionHandler()
    public ResponseEntity<SubscriptionError> catchResourceNotFoundException(
            final EmptyResultDataAccessException e, final WebRequest request) {
        SubscriptionError message = new SubscriptionError(
                        "Невозможно удалить подписку",
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    /**
     * Обрабатывает исключение IndexOutOfBoundsException.
     * @param e Исключение IndexOutOfBoundsException.
     * @param request Объект запроса.
     * @return Объект ResponseEntity с описанием ошибки.
     */
    @ExceptionHandler()
    public ResponseEntity<SubscriptionError> catchResourceNotFoundException(
            final IndexOutOfBoundsException e,
            final WebRequest request) {
        SubscriptionError message = new SubscriptionError(
                "Неверное значение параметра 'limit'",
                "Попытка получить такое кол-во элементов, которое превышает размер страницы: " + e.getMessage(),
                request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обрабатывает исключение ServerErrorException.
     * @param e Исключение ServerErrorException.
     * @param request Объект запроса.
     * @return Объект ResponseEntity с описанием ошибки.
     */
    @ExceptionHandler()
    public ResponseEntity<SubscriptionError> catchServerErrorException(
            final ServerErrorException e,
            final WebRequest request) {
        SubscriptionError message = new SubscriptionError(
                "Непредвиденная ошибка сервера",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Серверу не удалось корректно обработать запрос: " + e.getMessage(),
                request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Обрабатывает исключение UnprocessableEntityException.
     * @param e Исключение UnprocessableEntityException.
     * @param request Объект запроса.
     * @return Объект ResponseEntity с описанием ошибки.
     */
    @ExceptionHandler()
    public ResponseEntity<SubscriptionError> catchUnprocessableEntityException(
            final UnprocessableEntityException e,
            final WebRequest request) {
        SubscriptionError message = new SubscriptionError(
                "Невозможно обработать объект",
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Подробнее: " + e.getMessage(),
                request.getSessionId());
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
