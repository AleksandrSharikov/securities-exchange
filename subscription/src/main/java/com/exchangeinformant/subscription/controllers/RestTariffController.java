package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.service.TariffService;
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
 * REST-контроллер для работы с тарифами в API.
 */
@RestController
@RequestMapping("/api")
@RolesAllowed({"ADMIN"})
@Tag(name = "Контроллер тарифов", description = "CRUD операции с тарифами на подписки.")
public class RestTariffController {

    /**
     * Сервис для работы с тарифами.
     */
    private final TariffService tariffService;

    /**
     * Конструктор класса, инициализирующий сервис для работы с тарифами.
     * @param tService сервис для работы с тарифами
     */
    public RestTariffController(final TariffService tService) {
        this.tariffService = tService;
    }

    /**
     * Метод получения тарифа по идентификатору.
     * @param id идентификатор тарифа
     * @return Тариф на подписку
     */
    @GetMapping("/tariff/{id}")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Получить тариф по идентификатору.")
    public ResponseEntity<Tariff> getTariff(@PathVariable final Long id) {
        return ResponseEntity.ok(tariffService.getTariff(id));
    }

    /**
     * Метод получения всех тарифов.
     * @return Список всех тарифов на подписку
     */
    @GetMapping("/tariff")
    @RolesAllowed({"USER", "ADMIN"})
    @Operation(summary = "Получить все тарифы.")
    public ResponseEntity<List<Tariff>> getTariffs() {
        return ResponseEntity.ok(tariffService.getAllTariff());
    }

    /**
     * Метод создания нового тарифа.
     * @param tariff тариф на подписку
     * @return Ответ с кодом состояния HTTP OK
     */
    @PostMapping("/tariff")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Создать тариф.")
    public ResponseEntity<HttpStatus> createTariff(@RequestBody final Tariff tariff) {
        tariffService.createTariff(tariff);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Метод изменения данных тарифа.
     * @param tariff тариф на подписку
     * @return Ответ с кодом состояния HTTP OK
     */
    @PutMapping("/tariff")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Обновить тариф.")
    public ResponseEntity<HttpStatus> updateTariff(@RequestBody final Tariff tariff) {
        tariffService.updateTariff(tariff);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Метод удаления тарифа по идентификатору.
     * @param id идентификатор тарифа
     * @return Ответ с кодом состояния HTTP OK
     */
    @DeleteMapping("/tariff/{id}")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Удалить тариф.")
    public ResponseEntity<HttpStatus> deleteTariff(@PathVariable final Long id) {
        tariffService.deleteTariff(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

