
package com.exchangeinformant.subscription.util.rabbitMQ;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PaymentProofService {
    @Id
    private long subscription_id;
    private String pay_amount;
    private String ext_id;
}
