package com.exchangeinformant.subscription.util.Timer;

import java.time.LocalDateTime;

/**
 * Класс, представляющий собой таймер, который отслеживает окончание подписки.
 */
public class TimerSubscriptionEnd {

    /**
     * Стоимость подписки на 100 рублей.
     */
    private static final int PRICE_100 = 100;
    /**
     * Стоимость подписки на 190 рублей.
     */
    private static final int PRICE_190 = 190;
    /**
     * Стоимость подписки на 400 рублей.
     */
    private static final int PRICE_400 = 400;
    /**
     * Стоимость подписки на 1200 рублей.
     */
    private static final int PRICE_1200 = 1200;
    /**
     * Стоимость подписки на 1 месяц.
     */
    private static final int MON_1 = 1;
    /**
     * Стоимость подписки на 1 год.
     */
    private static final int YER_1 = 1;
    /**
     * Стоимость подписки на 3 месяца.
     */
    private static final int MON_3 = 3;
    /**
     * Стоимость подписки на 14 дней.
     */
    private static final int DAY_14 = 14;

    /**
     * Метод, который возвращает дату и время окончания подписки в зависимости от ее цены.
     *
     * @param price цена подписки
     * @return дата и время окончания подписки
     */
    public LocalDateTime methodOfExpiresSub(final int price) {
        if (price == PRICE_100) {
            return LocalDateTime.now().plusYears(0).plusMonths(0).plusDays(DAY_14);
        } else if (price ==  PRICE_190) {
            return LocalDateTime.now().plusYears(0).plusMonths(MON_1).plusDays(0);
        } else if (price ==  PRICE_400) {
            return LocalDateTime.now().plusYears(0).plusMonths(MON_3).plusDays(0);
        } else if (price ==  PRICE_1200) {
            return LocalDateTime.now().plusYears(YER_1).plusMonths(0).plusDays(0);
        } else {
            return null;
        }

    }
}
