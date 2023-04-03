package com.exchangeinformant.feed.repository;

import com.exchangeinformant.feed.model.MessageSources;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для источников сообщений
 */
@Repository
@Transactional
public interface SourceRepository extends JpaRepository<MessageSources, Integer> {
}
