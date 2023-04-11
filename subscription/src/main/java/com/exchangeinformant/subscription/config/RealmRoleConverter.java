package com.exchangeinformant.subscription.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Конвертер для преобразования ролей Realm в Spring Security GrantedAuthority.
 * Используется для конвертации токена JWT в Collection<GrantedAuthority>.
 * Преобразует роли из ключа "realm_access" токена в роли Spring Security с префиксом "ROLE_".
 */
public class RealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    /**
     * Преобразует роли Realm в роли Spring Security с префиксом "ROLE_".
     *
     * @param jwt токен JWT, который необходимо преобразовать.
     * @return коллекцию ролей Spring Security, полученных из токена.
     */
    @Override
    public Collection<GrantedAuthority> convert(final Jwt jwt) {
        final Map<String, List<String>> realmAccess = (Map<String, List<String>>) jwt.getClaims().get("realm_access");
        return realmAccess.get("roles")
                .stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
