package com.exchangeinformant.subscription.model;


import com.exchangeinformant.subscription.util.enums.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Класс PromoSubscription представляет собой объект промо-подписки, содержащий информацию о промо-коде и его типе.
 */
@Schema(name = "Промоподписка", description = "Сущность промоподписки")
@Data
@Entity
@Table(name = "promosubscription")
public class PromoSubscription {

    /**
     * Уникальный идентификатор этой промо-подписки.
     */
    @Id
    @Column(name = "promocode_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Конструктор PromoSubscription, создающий новый объект с заданными параметрами.
     *
     * @param isPCodeApplied Флаг, указывающий, был ли применен промо-код
     * @param pCode Промо-код, связанный с данной подпиской
     * @param tp Тип промо-подписки
     */
    public PromoSubscription(final Boolean isPCodeApplied, final String pCode, final Type tp) {
        this.isPromocodeApplied = isPCodeApplied;
        this.promocode = pCode;
        this.type = tp;
    }
    /**
     * Конструктор PromoSubscription, создающий новый пустой объект.
     */
    public PromoSubscription() {
    }

    /**
     * Флаг, указывающий, был ли применен промо-код.
     */
    @Column(name = "is_promocode_applied")
    private Boolean isPromocodeApplied;

    /**
     * Промо-код, связанный с этой подпиской.
     */
    @Column(name = "promocode")
    private String promocode;

    /**
     * Тип промо-подписки.
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

}
