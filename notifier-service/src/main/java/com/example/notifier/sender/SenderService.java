package com.example.notifier.sender;

import com.example.notifier.enums.SendingStatus;
import com.example.notifier.model.Message;
import com.example.notifier.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Consumer;

/**
 * SenderService класс-распределитель между способами отправления сообщения.
 * @Если сервис отправления отрабатывает без ошибок, то сообщению присваивается статус SENT/ОТПРАВЛЕНО
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SenderService implements Consumer<Message> {
    private final MessageService messageService;
    private final EmailSender emailSender;

    @Override
    public void accept(Message message) {
        Date now = new Date();
        message.setDateLastTrySend(now);
        messageService.save(message);

        switch (message.getServiceSender()) {
            case "Email":
                emailSender.sendMessage(message);
                break;
            case "Other":
                // Здесь добавляем другой сендер-сервис способный отправлять сообщения
                break;
        }

        message.setNumberTryToSend((byte) (message.getNumberTryToSend() + 1));
        message.sendingStatus = SendingStatus.SENT;
        message.setDateSend(now);
        messageService.save(message);
    }
}
