package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;
import com.exchangeinformant.subscription.repository.PromoSubscriptionRepository;
import com.exchangeinformant.subscription.service.PromoSubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RolesAllowed({"ADMIN", "USER"})
@Tag(name = "Контроллер промоподписок", description = "CRUD операции с промоподписками.")
public class RestPromoSubscriptionController {
    private final PromoSubscriptionService promoSubscriptionService;

    @Autowired
    public RestPromoSubscriptionController(PromoSubscriptionService promoSubscriptionService) {
        this.promoSubscriptionService = promoSubscriptionService;
    }

    @GetMapping("/promosubscription/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    @Operation(summary = "Получить промоподписку по идентификатору.")
    public ResponseEntity<PromoSubscriptionDTO> getPromoSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(promoSubscriptionService.getPromoSubscription(id));
    }

    @GetMapping("/promosubscription")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Получить все промоподписки.")
    public ResponseEntity<List<PromoSubscriptionDTO>> getPromoSubscription() {
        return ResponseEntity.ok(promoSubscriptionService.getAllPromoSubscription());
    }

    @PostMapping("/promosubscription")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Создать промоподписку.")
    public ResponseEntity<?> createPromoSubscription(@RequestBody PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.createPromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    @PutMapping("/promosubscription")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Изменить промоподписку.")
    public ResponseEntity<?> updatePromoSubscription(@RequestBody PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.updatePromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    @DeleteMapping("/promosubscription/{id}")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Удалить промоподписку.")
    public ResponseEntity<?> deletePromoSubscription(@PathVariable Long id) {
        promoSubscriptionService.deletePromoSubscription(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
