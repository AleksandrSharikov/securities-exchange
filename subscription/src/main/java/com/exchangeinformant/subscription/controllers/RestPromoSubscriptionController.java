package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;
import com.exchangeinformant.subscription.service.PromoSubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
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

/**
 * Класс является контроллером для обработки запросов, связанных с промоподписками.
 * Он содержит несколько методов для выполнения операций CRUD (создание, чтение, обновление, удаление) с данными
 * промоподписок.
 */
@RestController
@RequestMapping("/api")
@RolesAllowed({"ADMIN", "USER"})
@Tag(name = "Контроллер промоподписок", description = "CRUD операции с промоподписками.")
public class RestPromoSubscriptionController {

    /**
     * Сервис для выполнения операций с промоподписками.
     */
    private final PromoSubscriptionService promoSubscriptionService;

    /**
     * Конструктор контроллера.
     * @param promoSubsService Сервис для выполнения операций с промоподписками.
     */
    public RestPromoSubscriptionController(final PromoSubscriptionService promoSubsService) {
        this.promoSubscriptionService = promoSubsService;
    }

    /**
     * Получить промоподписку по идентификатору.
     * @param id Идентификатор промоподписки.
     * @return ResponseEntity с объектом PromoSubscriptionDTO.
     */
    @GetMapping("/promosubscription/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    @Operation(summary = "Получить промоподписку по идентификатору.")
    public ResponseEntity<PromoSubscriptionDTO> getPromoSubscription(@PathVariable final Long id) {
        return ResponseEntity.ok(promoSubscriptionService.getPromoSubscription(id));
    }

    /**
     * Получить все промоподписки.
     * @return ResponseEntity со списком объектов PromoSubscriptionDTO.
     */
    @GetMapping("/promosubscription")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Получить все промоподписки.")
    public ResponseEntity<List<PromoSubscriptionDTO>> getPromoSubscription() {
        return ResponseEntity.ok(promoSubscriptionService.getAllPromoSubscription());
    }

    /**
     * Создать промоподписку.
     * @param promosubscriptionDTO Объект PromoSubscriptionDTO для создания промоподписки.
     * @return ResponseEntity со статусом выполнения операции и объектом PromoSubscriptionDTO.
     */
    @PostMapping("/promosubscription")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Создать промоподписку.")
    public ResponseEntity<?> createPromoSubscription(@RequestBody final PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.createPromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    /**
     * Изменить промоподписку.
     * @param promosubscriptionDTO Объект PromoSubscriptionDTO для изменения промоподписки.
     * @return ResponseEntity со статусом выполнения операции и объектом PromoSubscriptionDTO.
     */
    @PutMapping("/promosubscription")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Изменить промоподписку.")
    public ResponseEntity<?> updatePromoSubscription(@RequestBody final PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.updatePromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    /**
     * Удалить промоподписку.
     * @param id Идентификатор промоподписки для удаления.
     * @return ResponseEntity со статусом выполнения операции и объектом PromoSubscriptionDTO.
     */
    @DeleteMapping("/promosubscription/{id}")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Удалить промоподписку.")
    public ResponseEntity<?> deletePromoSubscription(@PathVariable final Long id) {
        promoSubscriptionService.deletePromoSubscription(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
