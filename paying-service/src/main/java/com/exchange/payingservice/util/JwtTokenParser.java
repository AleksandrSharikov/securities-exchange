package com.exchange.payingservice.util;


import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Map;

/**
 * Класс UserInfo представляет собой утилиту для работы с информацией о пользователе.
 * Обеспечивает получение идентификатора пользователя из JWT токена.
 */
@Component
public class JwtTokenParser {

    /**
     * Получение идентификатора пользователя из JWT токена.
     * @param principal объект, содержащий информацию о пользователе
     * @return идентификатор пользовател
     * @throws NullPointerException если переданный параметр principal имеет значение null
     * @throws ClassCastException если объект, переданный в параметре principal,
     * не является типом JwtAuthenticationToken
     * @throws NullPointerException если токен не содержит информацию о пользователе
     * или не содержит идентификатора пользователя
     */
    public static String getExtID(final Principal principal) {
        JwtAuthenticationToken kp = (JwtAuthenticationToken) principal;
        Jwt token = kp.getToken();
        Map<String, Object> userInfo = token.getClaims();
        return userInfo.get("sub").toString();
    }
}
