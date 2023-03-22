package com.exchangeinformant.feed.repository;

import com.exchangeinformant.feed.model.MessageSources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository extends JpaRepository<MessageSources, Integer> {
}
