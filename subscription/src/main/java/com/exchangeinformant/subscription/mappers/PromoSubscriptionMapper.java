package com.exchangeinformant.subscription.mappers;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;
import com.exchangeinformant.subscription.model.PromoSubscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс PromoSubscriptionMapper используется для преобразования моделей PromoSubscription в DTO
 * и наоборот.
 */
@Mapper
public interface PromoSubscriptionMapper {
    /**
     * Экземпляр интерфейса PromoSubscriptionMapper для его использования в приложении.
     */
    PromoSubscriptionMapper INSTANCE = Mappers.getMapper(PromoSubscriptionMapper.class);
    /**
     * Преобразует модель PromoSubscription в DTO PromoSubscriptionDTO.
     *
     * @param promoSubscription модель PromoSubscription.
     * @return экземпляр DTO PromoSubscriptionDTO.
     */
    PromoSubscriptionDTO promoSubscriptionToDTO(PromoSubscription promoSubscription);
    /**
     * Преобразует DTO PromoSubscriptionDTO в модель PromoSubscription.
     *
     * @param promoSubscriptionDTO DTO PromoSubscriptionDTO.
     * @return экземпляр модели PromoSubscription.
     */
    PromoSubscription promoSubscriptionDTOToModel(PromoSubscriptionDTO promoSubscriptionDTO);
}
