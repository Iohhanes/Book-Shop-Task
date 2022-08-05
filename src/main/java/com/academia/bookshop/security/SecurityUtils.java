package com.academia.bookshop.security;

import com.academia.bookshop.model.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUtils {

    public static Optional<User> getCurrentUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(principal -> (JwtUser) principal)
                .map(JwtUser::getUser);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getClaim(Map<String, Object> claims, JwtPrincipalInfoClaimKey key) {
        return  (T) claims.get(key.getValue());
    }
}
