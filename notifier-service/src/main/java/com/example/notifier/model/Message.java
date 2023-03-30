package com.example.notifier.model;

import com.example.notifier.enums.SendingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Message - модель готового сообщения для отправки пользователю.
 */
@Entity(name = "notifier_messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * Сервис на который отправляется сообщение
     */
    @Column(name = "service_sender")
    private String serviceSender;

    /**
     * Емейл получателя
     */
    @Column(name = "to_email")
    private String toEmail;

    /**
     * Телефон получателя
     */
    @Column(name = "to_phone")
    private String toPhone;

    /**
     * Тема сообщения
     */
    @Column
    private String subject;

    /**
     * Текст сообщения
     */
    @Column
    private String text;

    /**
     * Статус сообщения -> смотреть enum SendingStatus
     */
    @Column(name = "sending_status")
    @Enumerated(EnumType.STRING)
    public SendingStatus sendingStatus;

    /**
     * Дата создания сообщения
     */
    @Column(name = "date_create") //
    private Date dateCreate;

    /**
     * Дата отправки сообщения. If value = null -> сообщение не отправлено
     */
    @Column(name = "date_send")
    private Date dateSend;

    /**
     * Дата последней попытки отправления сообщения
     */
    @Column(name = "last_try_send_date")
    private Date dateLastTrySend;

    /**
     * Общее кол-во попыток отправлений сообщения
     */
    @Column(name = "number_try_to_send")
    private byte numberTryToSend;
}
