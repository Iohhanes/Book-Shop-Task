package com.academia.bookshop.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum JwtPrincipalInfoClaimKey {
    EMAIL("email"),
    ROLES("roles"),
    AUTHORITIES("authorities");

    @Getter
    private String value;

    @Override
    public String toString() {
        return value;
    }
}
