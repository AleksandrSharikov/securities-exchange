package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Интерфейс SubscriptionService определяет функции для работы со всеми подписками.
 */
public interface SubscriptionService {

    /**
     * Функция создает новую подписку на информационные рассылки.
     * @param subscription - дата-трансфер-объект, содержащий информацию о подписке.
     */
    void createSubscription(SubscriptionDTO subscription);

    /**
     * Функция возвращает подписку на информационные рассылки по указанному идентификатору.
     * @param id - идентификатор подписки.
     * @return - дата-трансфер-объект, содержащий информацию о подписке.
     */
    SubscriptionDTO getSubscription(Long id);

    /**
     * Функция возвращает список всех сохраненных подписок на информационные рассылки.
     * @return - список дата-трансфер-объектов, содержащих информацию о подписке.
     */
    List<SubscriptionDTO> getAllSubscription();

    /**
     * Функция возвращает страницу подписок с учетом параметров для пагинации.
     * @param status - статус подписки (активная/незактивная).
     * @param offset - смещение начала выборки.
     * @param limit - максимальное количество элементов, которое должно содержаться на странице.
     * @param pageable - объект Pageable, представляющий параметры для пагинации.
     * @return - страница с дата-трансфер-объектами, содержащими информацию о подписке.
     */
    Page<SubscriptionDTO> getSubscriptionsWithPagination(String status, int offset, int limit, Pageable pageable);

    /**
     * Функция обновляет данные о сохраненной подписке на информационные рассылки.
     * @param subscriptionDTO - дата-трансфер-объект, содержащий информацию о подписке, которую нужно обновить.
     */
    void updateSubscription(SubscriptionDTO subscriptionDTO);

    /**
     * Функция удаляет подписку на информационные рассылки по указанному идентификатору.
     * @param id - идентификатор подписки.
     */
    void deleteSubscription(Long id);

}
