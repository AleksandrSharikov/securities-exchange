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

    Pageable pageable = PageRequest.of(0, 5, Sort.by("receivingTime").descending());

    // не нашёл способа выполнить два запроса в одном методе, по этому использовал два метода

    // Получение сообщений для пользователя id важностью не меньше rank
    @Query("SELECT m FROM Message m WHERE m.userId = ?1 and m.unread= true and m.rank >= ?2")
    List<Message> messagesForUser(long id, int rank, Pageable pageable);

    // Помечает сообщения прочитанными
    @Modifying
    @Query("UPDATE Message m SET m.unread = false WHERE m.id = ?1")
    void markRead(Long id);

    // В целях тестирования помечает все сообщения непрочитанными кроме специально помеченных прочитанными
    @Modifying
    @Query("UPDATE Message m SET m.unread = true WHERE m.data NOT LIKE '%read%'")
    void makeAllUnread();
}
