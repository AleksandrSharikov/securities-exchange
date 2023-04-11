
package com.exchangeinformant.subscription.util.rabbitMQ;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий объект подтверждения платежа.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PaymentProofService {
    /**
     * Идентификатор подписки.
     */
    @Id
    private long subscription_id;
    /**
     * Сумма оплаты.
     */
    private String pay_amount;
    /**
     * Внешний идентификатор.
     */
    private String ext_id;
}
