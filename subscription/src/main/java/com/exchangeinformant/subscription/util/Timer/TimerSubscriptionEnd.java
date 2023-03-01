package com.exchangeinformant.subscription.util.Timer;

import java.time.LocalDateTime;

public class TimerSubscriptionEnd {

    public LocalDateTime methodOfExpiresSub(int price) {
        int days;
        int months;
        int years;

        switch (price) {
            case 100: {
                days = 14;
                return LocalDateTime.now().plusYears(0).plusMonths(0).plusDays(days);
            }
            case 190: {
                months = 1;
                return LocalDateTime.now().plusYears(0).plusMonths(months).plusDays(0);
            }
            case 400: {
                months = 3;
                return LocalDateTime.now().plusYears(0).plusMonths(months).plusDays(0);
            }
            case 1200: {
                years = 1;
                return LocalDateTime.now().plusYears(years).plusMonths(0).plusDays(0);
            }
        }
        return null;
    }
}
