package com.exchangeinformant.feed.repository;

import com.exchangeinformant.feed.model.Patterns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Pattern repository
 * @see Patterns
 */
@Repository
public interface PatternRepository extends JpaRepository<Patterns,Integer> {
}
