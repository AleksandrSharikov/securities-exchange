package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Класс, отвечающий за обработку HTTP-запросов, связанных с подписками.
 */
@RestController
@RequestMapping("/api")
@RolesAllowed({"USER", "ADMIN"})
@Tag(name = "Контроллер подписок", description = "CRUD операции с подписками.")
public class AdminRestSubscriptionController {
    /**
     * Сервис, отвечающий за логику работы с подписками.
     */
    private final SubscriptionService subscriptionService;

    /**
     * Конструктор класса.
     *
     * @param subService Сервис, отвечающий за логику работы с подписками.
     */
    public AdminRestSubscriptionController(final SubscriptionService subService) {
        this.subscriptionService = subService;
    }

    /**
     * Метод для получения всех подписок, доступен только для пользователей с ролью "ADMIN".
     * Использует GET-запрос.
     * @return возвращает список подписок в виде объекта ResponseEntity<List<SubscriptionDTO>>
     */
    @GetMapping("/subscriptions")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Получить все подписки.")
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscription());
    }

    /**
     * Метод для получения подписок по определенному статусу. Доступен для пользователей с ролью "USER" или "ADMIN".
     * Использует GET-запрос и принимает параметры статуса, смещения, лимита и объекта Pageable
     * @param status
     * @param offset
     * @param limit
     * @param pageable
     * @return - возвращает список подписок в виде объекта ResponseEntity<Page<SubscriptionDTO>>
     */
    @GetMapping("/get-subscriptions-by-status")
    @RolesAllowed({"USER", "ADMIN"})
    @Operation(summary = "Получить подписки по статусу.")
    public ResponseEntity<Page<SubscriptionDTO>> getSubscriptionsByStatus(
            @RequestParam final String status,
            @RequestParam final int offset,
            @RequestParam final int limit,
            final Pageable pageable) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsWithPagination(status, offset, limit, pageable));
    }

    /**
     * Метод для получения конкретной подписки по ее идентификатору.
     * Доступен для пользователей с ролью "USER" или "ADMIN".
     * Использует GET-запрос и принимает в качестве параметра идентификатор подписки
     * @param id
     * @return возвращает подписку в виде объекта ResponseEntity<SubscriptionDTO>
     */
    @GetMapping("/subscriptions/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    @Operation(summary = "Получить подписку по идентификатору.")
    public ResponseEntity<SubscriptionDTO> getSubscription(@PathVariable final Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscription(id));
    }

    /**
     * Метод для создания новой подписки. Доступен для пользователей с ролью "USER" или "ADMIN".
     * Использует POST-запрос и принимает в качестве параметра объект SubscriptionDTO,
     * который содержит информацию о создаваемой подписке.
     * @param subscriptionDTO
     * @return Возвращает новую подписку в виде объекта ResponseEntity<?>
     */
    @PostMapping("/subscriptions")
    @RolesAllowed({"USER", "ADMIN"})
    @Operation(summary = "Создать подписку.")
    @ResponseBody
    public ResponseEntity<?> createSubscription(@Valid @RequestBody final SubscriptionDTO subscriptionDTO) {
        subscriptionService.createSubscription(subscriptionDTO);
        return new ResponseEntity<>(subscriptionDTO, HttpStatus.CREATED);
    }

    /**
     * Метод для обновления информации о существующей подписке. Доступен для пользователей с ролью "USER" или "ADMIN".
     * Использует PUT-запрос и принимает в качестве параметра объект SubscriptionDTO,
     * который содержит новую информацию о подписке.
     * @param subscriptionDTO
     * @return Возвращает обновленную подписку в виде объекта ResponseEntity<?>
     */
    @PutMapping("/subscriptions")
    @RolesAllowed({"USER", "ADMIN"})
    @Operation(summary = "Изменить подписку.")
    public ResponseEntity<?> updateSubscription(@Valid @RequestBody final SubscriptionDTO subscriptionDTO) {
        subscriptionService.updateSubscription(subscriptionDTO);
        return ResponseEntity.ok(subscriptionDTO);
    }

    /**
     * Метод для удаления существующей подписки. Доступен для пользователей с ролью "USER" или "ADMIN".
     * Использует DELETE-запрос и принимает в качестве параметра идентификатор удаляемой подписки.
     * @param id
     * @return Возвращает HTTP-ответ с кодом статуса 200 (OK) и идентификатором удаленной подписки  в случае успеха.
     * В случае ошибки, метод вернет соответствующий HTTP-код ошибки.
     */
    @DeleteMapping("/subscriptions/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    @Operation(summary = "Удалить подписку.")
    public ResponseEntity<?> deleteSubscription(@PathVariable final Long id) {
        subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
