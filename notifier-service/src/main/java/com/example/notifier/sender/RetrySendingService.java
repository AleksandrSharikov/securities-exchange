package com.example.notifier.sender;

import com.example.notifier.enums.SendingStatus;
import com.example.notifier.model.Message;
import com.example.notifier.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Класс-планировщик.
 * @Задача - через заданное время, проверять неотправленные сообщения и пытаться отправить их снова.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RetrySendingService {
    private final SenderService senderService;
    private final MessageService messageService;

    /**
     * Кол-во повторений отправки сообщения. Превышение лимита приведет к смене статуса сообщения в DEPRECATED.
     * @Статус DEPRECATED останавливает последующую обработку сообщения.
     */
    @Value("${limit_retry_for_sender_service}")
    private int retryLimit;

    /**
     * Метод-планировщик. Срабатывает каждый раз с интервалом указанным в параметре fixedRate.
     *
     * @Value 1000 = 1 секунда
     */
    @Scheduled(fixedRate = 5000) // 15минут = 900_000
    public void retrySendingMessageToUser() {
        messageService.getMessagesByNotSend()
                .ifPresent(messageLinkedList ->
                        Flux.fromIterable(messageLinkedList)
                                .publishOn(Schedulers.boundedElastic())
                                .filter(this::filterMessageDeprecated)
                                .doOnNext(senderService)
                                .doOnError(error -> log.error(error.getMessage()))
                                .subscribe()
                );
    }

    private boolean filterMessageDeprecated(Message message) {
        if (message.getNumberTryToSend() == retryLimit) {
            message.sendingStatus = SendingStatus.DEPRECATED;
            messageService.save(message);
            log.info("Message ID {} has been moved to the '{}' group", message.getId(), SendingStatus.DEPRECATED);
            return false;
        }
        return true;
    }
}
