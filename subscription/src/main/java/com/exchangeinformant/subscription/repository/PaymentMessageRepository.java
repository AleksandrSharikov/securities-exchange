package com.exchangeinformant.subscription.repository;

import com.exchangeinformant.subscription.util.rabbitMQ.PaymentProof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMessageRepository extends JpaRepository<PaymentProof, Long> {

}
