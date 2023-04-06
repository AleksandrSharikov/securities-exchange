package com.exchangeinformant.subscription.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * Класс, представляющий тариф.
 */
@Schema(name = "Тариф", description = "Сущность тарифа")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tariff")
public class Tariff {

    /**
     * Идентификатор тарифа.
     */
    @Id
    @Column(name = "tariff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Цена тарифа.
     */
    @Column(name = "tariff_cost")
    private int tariffCost;

    /**
     * Название тарифа.
     */
    @Column(name = "title")
    private String title;

    /**
     * Описание тарифа.
     */
    @Column(name = "description")
    private String description;

    /**
     * Тип тарифа.
     */
    @Column(name = "type")
    private String type;

    /**
     * Флаг активности тарифа.
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Проверяет, равен ли объект текущему тарифу.
     * @param o Объект для сравнения.
     * @return true, если объект равен текущему тарифу, иначе - false.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Tariff tariff = (Tariff) o;
        return id != null && Objects.equals(id, tariff.id);
    }

    /**
     * Возвращает хеш-код объекта.
     * @return Хеш-код объекта.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * Конструктор для создания объекта класса Tariff.
     */
    public Tariff() {
    }
}
