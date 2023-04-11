package com.example.notifier.repository;

import com.example.notifier.enums.SendingStatus;
import com.example.notifier.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<LinkedList<Message>> getAllBySendingStatus(SendingStatus sendingStatus);
}
