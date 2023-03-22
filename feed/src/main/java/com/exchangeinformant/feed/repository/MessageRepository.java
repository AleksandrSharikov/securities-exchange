package com.exchangeinformant.feed.repository;

import com.exchangeinformant.feed.model.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message,Long> {

  //  @Query("SELECT m FROM Message m WHERE m.userId = ?1 and m.unread= true")
  //  List<Message> messagesForUser(long id);
}
