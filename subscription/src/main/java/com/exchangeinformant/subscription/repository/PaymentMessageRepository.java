package com.exchangeinformant.subscription.repository;

import com.exchangeinformant.subscription.util.rabbitMQ.PaymentProofService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMessageRepository extends JpaRepository<PaymentProofService, Long> {

}
