package com.authentication.app.security;


public class SecurityConstants {
    public static final String SECRET_JWT = "f9c6eeeac376c52f89e63b233e1945bec5435d64e5786b4dc8a4ce798877107d";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String CONTENT_TYPE = "application/json";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 900_000;
    public static final long EXPIRATION_TIME_REFRESH = 10080000;
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String[] VALID_IP_HEADER_CANDIDATES = { "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };
}
