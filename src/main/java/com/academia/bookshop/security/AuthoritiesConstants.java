package com.academia.bookshop.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthoritiesConstants {
    public static final String AUTHORITY_ALL = "ALL";
    public static final String AUTHORITY_CREATE = "CREATE";
    public static final String AUTHORITY_UPDATE = "UPDATE";
    public static final String AUTHORITY_DELETE = "DELETE";
}
