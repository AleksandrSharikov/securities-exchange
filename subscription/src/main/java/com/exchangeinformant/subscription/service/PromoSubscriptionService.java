package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;

import java.util.List;

/**
 * Интерфейс, представляющий сервис промо-подписок.
 */
public interface PromoSubscriptionService {

    /**
     * Возвращает все промо-подписки.
     *
     * @return Список промо-подписок.
     */
    List<PromoSubscriptionDTO> getAllPromoSubscription();
    /**
     * Создает новую промо-подписку со значениями, заданными в параметре.
     *
     * @param promoSubscriptionDTO Промо-подписка, которую нужно создать.
     */
    void createPromoSubscription(PromoSubscriptionDTO promoSubscriptionDTO);

    /**
     * Возвращает промо-подписку с заданным идентификатором или null, если промо-подписка не существует.
     *
     * @param id Идентификатор промо-подписки.
     * @return Промо-подписка с заданным идентификатором.
     */
    PromoSubscriptionDTO getPromoSubscription(Long id);

    /**
     * Обновляет значения промо-подписки с заданным идентификатором.
     *
     * @param promoSubscriptionDTO Промо-подписка, которую нужно обновить.
     */
    void updatePromoSubscription(PromoSubscriptionDTO promoSubscriptionDTO);

    /**
     * Удаляет промо-подписку с заданным идентификатором.
     *
     * @param id Идентификатор промо-подписки, которую нужно удалить.
     */
    void deletePromoSubscription(Long id);

}
