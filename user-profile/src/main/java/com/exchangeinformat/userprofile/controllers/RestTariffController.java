package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.model.Tariff;
import com.exchangeinformat.userprofile.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tariff")
public class RestTariffController {

    private final TariffService tariffService;

    @Autowired
    public RestTariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("findOne")
    public ResponseEntity<Tariff> getTariff(Long id) {
        return ResponseEntity.ok(tariffService.getTariff(id));
    }

    @GetMapping
    public ResponseEntity<List<Tariff>> getTariffs() {
        return ResponseEntity.ok(tariffService.getAllTariff());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createTariff(Tariff tariff) {
        tariffService.createTariff(tariff);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateTariff(Tariff tariff) {
        tariffService.updateTariff(tariff);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/api/delete")
    public ResponseEntity<HttpStatus> deleteTariff(Long id) {
        tariffService.deleteTariff(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
