package com.socials.Gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JWTValidation {

    private static final String SECRET="43d7640272c961817cbe57f9811a776dfde782048b35644ac1732778ea958806";

    public void validateToken(final String token){
        Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
    }
    public SecretKey getSigningKey(){
        byte[] key= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(key);
    }
}
