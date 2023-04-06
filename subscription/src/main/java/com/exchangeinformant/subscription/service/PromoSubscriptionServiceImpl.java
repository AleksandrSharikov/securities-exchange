package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;
import com.exchangeinformant.subscription.exception.ResourceNotFoundException;
import com.exchangeinformant.subscription.mappers.PromoSubscriptionMapper;
import com.exchangeinformant.subscription.repository.PromoSubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс PromoSubscriptionServiceImpl реализует интерфейс PromoSubscriptionService и предоставляет возможности работы
 * с промоподписками.
 */
@Service
public class PromoSubscriptionServiceImpl implements PromoSubscriptionService {

    /**
     * Поле promoSubscriptionRepository - репозиторий для работы с промоподписками.
     */
    private final PromoSubscriptionRepository promoSubscriptionRepository;

    /**
     * Конструктор PromoSubscriptionServiceImpl. Внедряет зависимость PromoSubscriptionRepository в поле
     * promoSubRepository.
     * @param promoSubRepository - репозиторий для работы с промоподписками.
     */
    public PromoSubscriptionServiceImpl(final PromoSubscriptionRepository promoSubRepository) {
        this.promoSubscriptionRepository = promoSubRepository;
    }

    /**
     * Функция возвращает список всех доступных промоподписок.
     * @return - список промоподписок.
     */
    @Override
    public List<PromoSubscriptionDTO> getAllPromoSubscription() {
        return promoSubscriptionRepository.findAll()
                .stream()
                .map(PromoSubscriptionMapper.INSTANCE::promoSubscriptionToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Функция создает новую промоподписку.
     * @param promoSubscriptionDTO - дата-трансфер-объект промоподписки.
     */
    @Override
    @Transactional
    public void createPromoSubscription(final PromoSubscriptionDTO promoSubscriptionDTO) {
        promoSubscriptionRepository.save(PromoSubscriptionMapper
                .INSTANCE.promoSubscriptionDTOToModel(promoSubscriptionDTO));
    }

    /**
     * Функция возвращает промоподписку по идентификатору.
     * @param id - идентификатор промоподписки.
     * @return - дата-трансфер-объект промоподписки.
     * @throws ResourceNotFoundException - если промоподписка с указанным идентификатором не найдена.
     */
    @Override
    public PromoSubscriptionDTO getPromoSubscription(final Long id) {
        return PromoSubscriptionMapper
                .INSTANCE
                .promoSubscriptionToDTO(promoSubscriptionRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Промоподписка с id '" + id + "' не найдена")));
    }

    /**
     * Функция обновляет промоподписку.
     * @param promoSubscriptionDTO - дата-трансфер-объект промоподписки.
     */
    @Override
    @Transactional
    public void updatePromoSubscription(final PromoSubscriptionDTO promoSubscriptionDTO) {
        promoSubscriptionRepository.save(PromoSubscriptionMapper
                .INSTANCE
                .promoSubscriptionDTOToModel(promoSubscriptionDTO));
    }

    /**
     * Функция удаляет промоподписку по идентификатору.
     * @param id - идентификатор промоподписки.
     * @throws ResourceNotFoundException - если промоподписка с указанным идентификатором не найдена.
     */
    @Override
    @Transactional
    public void deletePromoSubscription(final Long id) {
        try {
            promoSubscriptionRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException(
                    "Не возможно удалить Промоподписку с id '" + id + "': " + e.getMessage());
        }
    }
}
