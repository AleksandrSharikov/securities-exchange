package com.exchangeinformant.feed.repository;

import com.exchangeinformant.feed.model.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message,Long> {
}
