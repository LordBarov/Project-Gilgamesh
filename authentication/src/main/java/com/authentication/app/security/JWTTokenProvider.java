package com.authentication.app.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.authentication.app.security.SecurityConstants.VALID_IP_HEADER_CANDIDATES;

@Component
@Slf4j
public class JWTTokenProvider {
    public String generateToken(CustomUserDetails userDetails, String clientIp) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET_JWT.getBytes(StandardCharsets.UTF_8));
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .withClaim("client ip", clientIp)
                .sign(algorithm);
    }

    public String refreshToken(CustomUserDetails userDetails, String clientIp) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET_JWT.getBytes(StandardCharsets.UTF_8));
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME_REFRESH);
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .withClaim("client ip", clientIp)
                .sign(algorithm);
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(username, null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }


    public String getSubject(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    public boolean isTokenValid(String username, String token, String clientIp) {
        JWTVerifier jwtVerifier = getJWTVerifier();
        Date expiration = jwtVerifier.verify(token).getExpiresAt();
        if (username != null && !expiration.before(new Date()) && jwtVerifier.verify(token)
                .getClaim("client ip").asString().equals(clientIp)) {
            return true;
        } else {
            throw new TokenExpiredException("Token is not valid",null);
        }
    }

    public String getIpFromClient(HttpServletRequest request) {
        for (String header : VALID_IP_HEADER_CANDIDATES) {
            String ipAddress = request.getHeader(header);
            if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
                return ipAddress;
            }
        }
        return request.getRemoteAddr();
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier jwtVerifier;

        try {
            Algorithm algorithm = HMAC256(SecurityConstants.SECRET_JWT);
            jwtVerifier = JWT.require(algorithm).build();
        }catch (JWTVerificationException e){
            throw new JWTVerificationException("Token can not be verified");
        }
        return jwtVerifier;
    }
}
