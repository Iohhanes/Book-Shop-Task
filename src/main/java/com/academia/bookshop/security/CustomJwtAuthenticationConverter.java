package com.academia.bookshop.security;

import com.academia.bookshop.model.entity.*;
import com.academia.bookshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final UserRepository userRepository;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        String userEmail = jwt.getClaimAsString(JwtPrincipalInfoClaimKey.EMAIL.getValue());
        if (userEmail == null) {
            throw new InvalidBearerTokenException("Invalid JWT: email is absent");
        }

        Map<String, Object> attributes = jwt.getClaims();
        JwtUser jwtUser = createPrincipal(userEmail, attributes);

        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, jwt.getTokenValue(),
                jwt.getIssuedAt(), jwt.getExpiresAt());
        return new BearerTokenAuthentication(jwtUser, accessToken, jwtUser.getAuthorities());
    }

    private JwtUser createPrincipal(String userEmail, Map<String, Object> attributes) {
        User user;
        Optional<User> memoriesUserOptional = userRepository.findByEmail(userEmail);
        if (memoriesUserOptional.isEmpty()) {
            user = userRepository.save(
                    User.builder()
                            .email(userEmail)
                            .build()
            );

        } else {
            user = memoriesUserOptional.get();
        }

        return new JwtUser(user.getId(), userEmail, attributes);
    }
}
