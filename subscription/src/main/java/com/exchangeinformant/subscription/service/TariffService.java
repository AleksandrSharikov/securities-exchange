package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.model.Tariff;

import java.util.List;

/**
 * Интерфейс для работы с тарифами.
 */
public interface TariffService {

    /**
     * Метод для получения списка всех тарифов.
     * @return Список всех тарифов.
     */
    List<Tariff> getAllTariff();
    /**
     * Метод для создания нового тарифа.
     * @param tariff Новый тариф.
     */
    void createTariff(Tariff tariff);

    /**
     * Метод для получения тарифа по его идентификатору.
     * @param id Идентификатор тарифа.
     * @return Тариф с указанным идентификатором.
     */
    Tariff getTariff(Long id);

    /**
     * Метод для обновления информации о тарифе.
     * @param tariff Тариф, который необходимо обновить.
     */
    void updateTariff(Tariff tariff);

    /**
     * Метод для удаления тарифа по его идентификатору.
     * @param id Идентификатор тарифа, который необходимо удалить.
     */
    void deleteTariff(Long id);
}
