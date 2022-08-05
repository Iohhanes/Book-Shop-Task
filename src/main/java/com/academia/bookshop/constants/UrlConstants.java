package com.academia.bookshop.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlConstants {
    public static final String API = "/api";
    public static final String FORWARD_SLASH = "/";

    public static final String SWAGGER_ENDPOINT = "/swagger-ui/**";
    public static final String SWAGGER_API_DOCS_ENDPOINT = "/v3/api-docs/**";
    public static final String BOOK_ENDPOINT = "/api/books/**";
    public static final String AUTHOR_ENDPOINT = "/api/authors/**";
    public static final String TAG_ENDPOINT = "/api/tags/**";
}
