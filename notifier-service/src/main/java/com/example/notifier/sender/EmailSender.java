package com.example.notifier.sender;

import com.example.notifier.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * EmailSender использует библиотеку org.springframework.mail.javamail.JavaMailSender для отправки сообщений на емейл.
 */
@Service
@Slf4j
public class EmailSender implements Sender {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMessage(Message message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(message.getToEmail());
        simpleMailMessage.setSubject(message.getSubject());
        simpleMailMessage.setText(message.getText());
        simpleMailMessage.setFrom(from);
        mailSender.send(simpleMailMessage);
        log.info("Email from {} to {} SENT", from, message.getToEmail());
    }
}
