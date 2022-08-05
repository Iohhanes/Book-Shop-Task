package com.academia.bookshop.configuration;

import com.academia.bookshop.security.CustomJwtAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static com.academia.bookshop.constants.UrlConstants.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomJwtAuthenticationConverter customJwtAuthenticationConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // @formatter:off

        httpSecurity
                    .csrf()
                        .disable()
                    .authorizeRequests()
                        .antMatchers(
                                BOOK_ENDPOINT,
                                AUTHOR_ENDPOINT,
                                TAG_ENDPOINT,
                                SWAGGER_ENDPOINT,
                                SWAGGER_API_DOCS_ENDPOINT
                        ).permitAll()
                        .anyRequest()
                            .authenticated()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .oauth2ResourceServer()
                        .jwt()
                            .jwtAuthenticationConverter(customJwtAuthenticationConverter);

        // @formatter:on

        return httpSecurity.build();
    }
}
