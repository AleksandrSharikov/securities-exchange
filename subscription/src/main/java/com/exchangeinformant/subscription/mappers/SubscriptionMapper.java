package com.exchangeinformant.subscription.mappers;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс SubscriptionMapper используется для преобразования моделей Subscription в DTO
 * и наоборот.
 */
@Mapper
public interface SubscriptionMapper {
    /**
     * Экземпляр интерфейса SubscriptionMapper для его использования в приложении.
     */
    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);
    /**
     * Преобразует модель Subscription в DTO SubscriptionDTO.
     *
     * @param subscription модель Subscription.
     * @return экземпляр DTO SubscriptionDTO.
     */
    SubscriptionDTO subscriptionToDTO(Subscription subscription);
    /**
     * Преобразует DTO SubscriptionDTO в модель Subscription.
     *
     * @param subscriptionDTO DTO SubscriptionDTO.
     * @return экземпляр модели Subscription.
     */
    Subscription subscriptionDTOToModel(SubscriptionDTO subscriptionDTO);

}
