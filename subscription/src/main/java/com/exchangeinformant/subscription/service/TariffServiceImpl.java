package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.repository.TariffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Класс, реализующий интерфейс для работы с тарифами.
 */
@Service
public class TariffServiceImpl implements TariffService {

    /**
     * Репозиторий для работы с тарифами.
     */
    private final TariffRepository tariffRepository;

    /**
     * Конструктор класса TariffServiceImpl.
     * @param tRepository Репозиторий для работы с тарифами.
     */
    public TariffServiceImpl(final TariffRepository tRepository) {
        this.tariffRepository = tRepository;
    }

    /**
     * Метод для получения списка всех тарифов.
     * @return Список всех тарифов.
     */
    @Override
    public List<Tariff> getAllTariff() {
        return tariffRepository.findAll();
    }

    /**
     * Метод для создания нового тарифа.
     * @param tariff Новый тариф.
     */
    @Override
    @Transactional
    public void createTariff(final Tariff tariff) {
        tariffRepository.save(tariff);
    }

    /**
     * Метод для получения тарифа по его идентификатору.
     * @param id Идентификатор тарифа.
     * @return Тариф с указанным идентификатором или null, если такого тарифа не существует.
     */
    @Override
    public Tariff getTariff(final Long id) {
        return tariffRepository.findById(id).orElse(null);
    }

    /**
     * Метод для обновления информации о тарифе.
     * @param tariff Тариф, который необходимо обновить.
     */
    @Override
    @Transactional
    public void updateTariff(final Tariff tariff) {
        tariffRepository.save(tariff);
    }

    /**
     * Метод для удаления тарифа по его идентификатору.
     * @param id Идентификатор тарифа, который необходимо удалить.
     */
    @Override
    @Transactional
    public void deleteTariff(final Long id) {
        tariffRepository.deleteById(id);
    }
}
