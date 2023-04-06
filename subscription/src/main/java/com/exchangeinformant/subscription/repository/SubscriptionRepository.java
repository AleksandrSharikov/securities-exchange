package com.exchangeinformant.subscription.repository;

import com.exchangeinformant.subscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    /**
     * Метод для поиска всех подписок пользователя по идентификатору.
     *
     * @param userId идентификатор пользователя
     * @return список всех подписок пользователя
     */
    List<Subscription> findAllByUserId(Long userId);
}
