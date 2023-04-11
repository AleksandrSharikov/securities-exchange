package com.exchangeinformant.subscription.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class ResourceServerConfig {

    /**
     * Создает конфигурацию цепочки фильтров безопасности для вашего веб-приложения.
     *
     * Этот метод может безопасно переопределяться в подклассах, если вы хотите внести свои собственные изменения
     * в цепочку фильтров. Переопределяющие методы должны вызывать этот метод и выполнить любые необходимые настройки
     * после его вызова.
     *
     * @param http Объект HttpSecurity, используемый для конфигурации более глубокого уровня безопасности.
     * @return Объект SecurityFilterChain для цепочки фильтров безопасности.
     * @throws Exception Если произошла ошибка во время конфигурации безопасности.
     */
    @Bean
    protected SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()));
        return http.build();
    }

    /**
     * Создает конвертер для JWT, возвращающий список ролей из JWT.
     * @return Объект Converter для конвертирования Jwt в объект AbstractAuthenticationToken с присвоенными ролями.
     */
    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new RealmRoleConverter());
        return jwtConverter;
    }
}
