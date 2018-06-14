package com.aleknik.cdss.cdssservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * Utility class for working with JSON Web Token.
 */
@Component
public class TokenUtils {

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expiration}")
    private Long expiration;

    private static final String AUTHORITIES_KEY = "auth";

    /**
     * Generates JSON Web Token from username.
     *
     * @param userDetails user details
     * @return String with JSON Web Token
     */
    public String generateToken(UserDetails userDetails) {
        final String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(generateExpirationDate())
                .setIssuedAt(generateCurrentDate())
                .compact();
    }

    /**
     * Decodes JSON Web Token and returns username.
     *
     * @param token JSON Web Token
     * @return String with username
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                username = null;
            } else {
                username = claims.getSubject();
            }
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * Gets claims form JSON Web Token.
     *
     * @param token JSON Web Token
     * @return Claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Gets expiration date of JSON Web Token.
     *
     * @param token JSON Web Token
     * @return token expiration date
     */
    private Date getExpirationDateFromToken(String token) {
        Date expirationDate;
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                expirationDate = null;
            } else {
                expirationDate = claims.getExpiration();
            }
        } catch (Exception e) {
            expirationDate = null;
        }
        return expirationDate;
    }

    private Date getIssuedDate(String token) {
        Date issuedDated;
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                issuedDated = null;
            } else {
                issuedDated = claims.getIssuedAt();
            }
        } catch (Exception e) {
            issuedDated = null;
        }
        return issuedDated;
    }

    /**
     * Checks if JSON Web Token is expired.
     *
     * @param token JSON Web Token
     * @return true if token is expired
     */
    private boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate == null || expirationDate.before(generateCurrentDate());
    }

    /**
     * Generates current date.
     *
     * @return current date
     */
    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * Generates expiration date.
     *
     * @return expiration date
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }
}

