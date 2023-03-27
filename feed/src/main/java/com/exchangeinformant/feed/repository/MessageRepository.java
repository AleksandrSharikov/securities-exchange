package com.exchangeinformant.feed.repository;

import com.exchangeinformant.feed.model.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message,Long> {

    Pageable pageable = PageRequest.of(0, 5, Sort.by("receivingTime"));

    // не нашёл способа выполнить два запроса в одном методе, по этому использовал два метода


    @Query("SELECT m FROM Message m WHERE m.userId = ?1 and m.unread= true and m.rank >= ?2")
    List<Message> messagesForUser(long id, int rank, Pageable pageable);

    @Modifying
    @Query("UPDATE Message m SET m.unread = false WHERE m.id = ?1")
    void markRead(Long id);
}
